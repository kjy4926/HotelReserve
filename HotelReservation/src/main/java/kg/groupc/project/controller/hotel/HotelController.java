package kg.groupc.project.controller.hotel;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.dto.account.BookingDto;
import kg.groupc.project.dto.hotel.BookingFormDto;
import kg.groupc.project.dto.hotel.HotelDetailFormDto;
import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.service.hotel.HotelService;
import kg.groupc.project.util.HotelPageUtil;

@Controller
public class HotelController extends BaseController{
	
	@Autowired
	HotelPageUtil hotelPageUtil;
	
	@GetMapping("/hotel")
	public String hotel(HttpServletRequest request, Model model,
			@PageableDefault(size = 10)Pageable pageable) {
//		@PageableDefault에 size로 한 페이지당 출력할 데이터 개수를 지정가능
		int page;
		String keyword = request.getParameter("keyword");
		
		int type;
		if(request.getParameter("type")== null) {//검색 키워드 조건
			type = 0;
		}else {
			type = Integer.parseInt(request.getParameter("type"));
		}

		if(request.getParameter("page") == null) {//페이지
			page = 1;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		pageable = PageRequest.of(page-1, 10);
		
		List<HotelMainFormDto> hotelMainFormDtoList = //데이터 전체 반환
				hotelService.getHotelList(keyword, type, pageable);

		if(hotelMainFormDtoList.size() == 0) {//데이터 없을 때 에러페이지 방지용
			return "redirect:/hotel";
		}
		
		
		int maxPage = hotelPageUtil.pageButtonInitialize((int) hotelMainFormDtoList.get(1).getDataCount(), pageable);
		
		model.addAttribute("type", type);//검색 조건 유지
		model.addAttribute("keyword", keyword);//페이지 이동 후에도 keyword를 유지시키기 위함
		model.addAttribute("hotelMainFormDtoList", hotelMainFormDtoList);//호텔 목록 전달
		model.addAttribute("maxPage", maxPage);
//		페이지에 maxPage만큼 이동버튼이 생김, 마지막 페이지
//		마지막 페이지, 첫 페이지가 0부터 시작한다는걸 감안하고 계산
		model.addAttribute("page", pageable.getPageNumber());//현재 페이지값 유지용
		model.addAttribute("firstPage", hotelPageUtil.getFirstPage());//제일 첫번째 페이지
		model.addAttribute("prevPage", hotelPageUtil.getPrevPage());//이전 10개 페이지 중 마지막 페이지
		model.addAttribute("nextPage", hotelPageUtil.getNextPage());//다음 10개 페이지 중 첫번째 페이지
		model.addAttribute("startPage", hotelPageUtil.getStartPage());//페이지 이동버튼 출력범위, forEach문 시작값
		model.addAttribute("lastPage", hotelPageUtil.getLastPage());//페이지 이동버튼 출력범위, forEach문 끝값

		return "/hotel/hotel";
	}
	
	@GetMapping("/hotel/detail/{seq}")
	public String hotelDetail(@PathVariable int seq, Model model) {//호텔 상세보기/객실리스트 화면
		
		HotelDetailFormDto hotelDetailFormDto = hotelService.getHotelDetail(seq);
		if(hotelDetailFormDto == null) {//에러방지용
			return "redirect:/";
		}
		
		model.addAttribute("hotelDetailFormDto", hotelDetailFormDto);
		
		return "/hotel/hotelDetail";
	}
	
	@GetMapping("/hotel/reservation/{seq}")
	public String reservation(Model model, @PathVariable Long seq) {
		Room room = roomService.getRoomBySeq(seq);
		String price = new DecimalFormat("#,###").format(room.getPrice());
		model.addAttribute("seq", room.getSeq());
		model.addAttribute("img", room.getImg());
		model.addAttribute("roomName", room.getName());
		model.addAttribute("roomPeople", room.getPeople());
		model.addAttribute("basePrice", room.getPrice());
		model.addAttribute("roomPrice", price);
		model.addAttribute("roomDesc", room.getDescription());
		model.addAttribute("now", LocalDate.now());
		model.addAttribute("next", LocalDate.now().plusDays(1));
		
		return "/hotel/reservation";
	}
	
	@PostMapping("/hotel/reservation/{seq}")
	public String postReservation(Model model, @PathVariable Long seq, BookingFormDto bookingFormDto,
				@AuthenticationPrincipal User user) {
		bookingService.saveBooking(bookingFormDto, user.getUsername());
		return "redirect:/mypage";
	}
	
	
	@ResponseBody
	@PostMapping("/hotel/reservation/datecheck")
	public boolean dateCheck(Long roomSeq, String checkin, String checkout,
			@AuthenticationPrincipal User user) {
		if(bookingService.reserveDateValidCheck(roomSeq, checkin, checkout, user.getUsername())) {
			return true;
		}
		return false;
	}
	
	
//	@GetMapping("/hotel/samplescore")
//	public String sampleScore() {
//	//샘플 스코어 생성용도, 
//	//사용할 경우 hotelService에 있는 addSampleScore의 주석 해제 및 yangchi97아이디 추가
//		
//		 hotelService.addSampleScore();
//		
//		
//		return "redirect:/";
//	}
}
