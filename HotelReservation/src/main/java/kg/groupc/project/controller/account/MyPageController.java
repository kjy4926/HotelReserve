package kg.groupc.project.controller.account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.dto.account.BookingDto;
import kg.groupc.project.dto.account.HotelScoreDto;
import kg.groupc.project.dto.account.InfoChangeFormDto;
import kg.groupc.project.dto.account.PwdChangeFormDto;
import kg.groupc.project.dto.account.RestaurantScoreDto;
import kg.groupc.project.dto.account.StarsDto;
import kg.groupc.project.dto.inquire.InquireDto;
import kg.groupc.project.dto.review.ReviewFormDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.restaurant.Restaurant;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController extends BaseController{
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal User user, Model model) {
		String userId = user.getUsername();
		List<ArrayList<BookingDto>> bookingList = accountService.getBookingList(userId);
		List<StarsDto> starsList = accountService.getStarsList(userId);
		List<HotelScoreDto> hotelScoreList = accountService.getHotelScoreList(userId);
		List<RestaurantScoreDto> restaurantScoreList = accountService.getRestaurantScoreList(userId);
		List<InquireDto> inquireList = inquireService.getMypageInquire(userId);
		
		bookingList.get(0).sort((Comparator.comparing(BookingDto::getReserveDate))); // 예약 현황은 빠른일 기준 먼저 출력
		bookingList.get(1).sort((Comparator.comparing(BookingDto::getReserveDate).reversed())); // 이용 내역은 최신 내역 먼저 출력
		
		model.addAttribute("reserveBookingList", bookingList.get(0));
		model.addAttribute("progressedBookingList", bookingList.get(1));
		model.addAttribute("starsDtoList", starsList);
		model.addAttribute("hotelScoreList", hotelScoreList);
		model.addAttribute("restaurantScoreList", restaurantScoreList);
		model.addAttribute("inquireList", inquireList);

		return "/mypage/mypage";
	}
	
	@GetMapping("/mypage/pwdcheck")
	public String pwdCheck() {
		return "/login/pwdCheckForm";
	}
	
	@PostMapping("/mypage/pwdcheck")
	public String postPwdCheck(@RequestParam String menu, @RequestParam String password,
				@AuthenticationPrincipal User user,
				Model model) {
		Account account = accountService.getAccountById(user.getUsername());
		model.addAttribute("menu", menu);
		if(passwordEncoder.matches(password, account.getPassword())) {
			model.addAttribute("pwdck", "1");
			if(menu.equals("1")) {
				InfoChangeFormDto infoChangeFormDto = new InfoChangeFormDto();
				infoChangeFormDto.setUsername(account.getName());
				infoChangeFormDto.setUserId(account.getUserId());
				infoChangeFormDto.setEmail(account.getEmail());
				infoChangeFormDto.setPhone(account.getPhone());
				model.addAttribute("infoChangeFormDto", infoChangeFormDto);
				return "/mypage/account/infoChangeForm";
			}else if(menu.equals("2")) {
				return "/mypage/account/pwdChangeForm";
			}else if(menu.equals("3")) {
				return "/mypage/account/resign";
			}else {
				return "/";
			}
		}
		model.addAttribute("errorMsg", "비밀번호가 잘못되었습니다.");
		return "/login/pwdCheckForm";
	}
	
	@GetMapping("/mypage/infoChange")
	public String infoChange() {
		return "/login/pwdCheckForm";
	}
	
	@PostMapping("/mypage/infoChange")
	public String postInfoChange(@Valid InfoChangeFormDto infoChangeFormDto, Errors errors, Model model,
				@AuthenticationPrincipal User user) {
		if(errors.hasErrors()) {
			model.addAttribute("infoChangeFormDto", infoChangeFormDto);
			// 입력값 유효성 검사
			Map<String, String> validatorResult = accountService.validateHandling(errors);
			for(String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "/mypage/account/infoChangeForm";
		}
		accountService.changeAccountInfo(infoChangeFormDto);
		model.addAttribute("msg", "개인 정보 변경이 완료되었습니다.\n개인 정보가 노출되지 않도록 조심해주세요.");
		model.addAttribute("type", "change");
		return "/alert/success";
	}
	
	@GetMapping("/mypage/pwdChange")
	public String pwdChange() {
		return "/login/pwdCheckForm";
	}
	
	@PostMapping("/mypage/pwdChange")
	public String postPwdChange(@AuthenticationPrincipal User user, Model model, 
			@Valid PwdChangeFormDto pwdChangeFormDto, Errors errors) {
		if(errors.hasErrors()) {
			model.addAttribute("pwdChangeFormDto", pwdChangeFormDto);
			// 입력값 유효성 검사
			Map<String, String> validatorResult = accountService.validateHandling(errors);
			for(String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "/mypage/account/pwdChangeForm";
		}
		accountService.changeAccountPasswordChange(user.getUsername(), pwdChangeFormDto);
		model.addAttribute("msg", "비밀번호 변경이 완료되었습니다.\n비밀번호가 노출되지 않도록 조심해주세요.");
		model.addAttribute("type", "pwdchange");
		return "/alert/success";
	}
	
	@PostMapping("/mypage/resign")
	@ResponseBody
	public boolean resign(@AuthenticationPrincipal User user) {
		return accountService.resignAccount(user.getUsername());
	}
	
	@GetMapping("/mypage/review/hotel/write/{seq}")
	public String hotelReview(@PathVariable Long seq, Model model) {
		Hotel hotel = hotelService.getHotelBySeq(seq);
		model.addAttribute("seq", seq);
		model.addAttribute("name", hotel.getName());
		model.addAttribute("type", "hotel");
		model.addAttribute("img", hotel.getImg());
		return "/mypage/review/reviewForm";
	}
	
	@PostMapping("/mypage/review/hotel/write/{seq}")
	public String postHotelReview(@PathVariable Long seq, Model model, ReviewFormDto reviewFormDto,
				@AuthenticationPrincipal User user) {
		hotelScoreService.saveHotelScore(reviewFormDto, seq, user.getUsername());
		return "redirect:/mypage";
	}
	
	@GetMapping("/mypage/review/restaurant/write/{seq}")
	public String restaurantReview(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.getRestaurantBySeq(seq);
		model.addAttribute("seq", seq);
		model.addAttribute("name", restaurant.getName());
		model.addAttribute("type", "restaurant");
		model.addAttribute("img", seq+".jpg");
		return "/mypage/review/reviewForm";
	}
	
	@PostMapping("/mypage/review/restaurant/write/{seq}")
	public String postRestaurantReview(@PathVariable Long seq, Model model, ReviewFormDto reviewFormDto,
				@AuthenticationPrincipal User user) {
		restaurantScoreService.saveRestaurantScore(reviewFormDto, seq, user.getUsername());
		return "redirect:/mypage";
	}
	
	@GetMapping("/mypage/history")
	public String history(@AuthenticationPrincipal User user, Model model) {
		String userId = user.getUsername();
		List<ArrayList<BookingDto>> bookingList = accountService.getBookingList(userId);
		
		bookingList.get(0).sort((Comparator.comparing(BookingDto::getReserveDate))); // 예약 현황은 빠른일 기준 먼저 출력
		bookingList.get(1).sort((Comparator.comparing(BookingDto::getReserveDate).reversed())); // 이용 내역은 최신 내역 먼저 출력
		
		model.addAttribute("reserveBookingList", bookingList.get(0));
		model.addAttribute("progressedBookingList", bookingList.get(1));
		
		return "/mypage/list/historyList";
	}
	
	@GetMapping("/mypage/review")
	public String review(Model model, @AuthenticationPrincipal User user) {
		String userId = user.getUsername();
		List<HotelScoreDto> hotelScoreList = accountService.getHotelScoreList(userId);
		List<RestaurantScoreDto> restaurantScoreList = accountService.getRestaurantScoreList(userId);
		
		model.addAttribute("hotelScoreList", hotelScoreList);
		model.addAttribute("restaurantScoreList", restaurantScoreList);
		
		return "/mypage/list/reviewList";
	}
	
	@GetMapping("/mypage/inquire")
	public String inquire(Model model, @AuthenticationPrincipal User user) {
		String userId = user.getUsername();
		List<InquireDto> inquireList = inquireService.getMypageInquire(userId);
		
		model.addAttribute("inquireList", inquireList);
		
		return "/mypage/list/inquireList";
	}
	
	@GetMapping("/mypage/stars")
	public String stars(Model model, @AuthenticationPrincipal User user) {
		String userId = user.getUsername();
		List<StarsDto> starsList = accountService.getStarsList(userId);
		model.addAttribute("starsDtoList", starsList);
		
		return "/mypage/list/starsList";
	}
	
	@ResponseBody
	@PostMapping("/mypage/review/delete")
	public boolean reviewDelete(Long type, Long seq) {
		if(type==1L) {
			hotelScoreService.deleteHotelScore(seq);
			return true;
		}else if(type==2L) {
			restaurantScoreService.deleteRestaurantScore(seq);
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@PostMapping("/mypage/reservation/delete")
	public boolean reserveDelete(Long seq) {
		bookingService.deleteBooking(seq);
		return true;
	}
	
	@ResponseBody
	@PostMapping("/mypage/stars/delete")
	public boolean starsDelete(Long seq) {
		starsService.deleteStars(seq);
		return true;
	}
}
