package kg.groupc.project.controller.hotel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.service.hotel.HotelService;
import kg.groupc.project.util.HotelPageUtil;
import lombok.RequiredArgsConstructor;

@Controller
public class HotelController extends BaseController{
	
	@Autowired
	private HotelService<Hotel, Long> hotelService;
	
	@Autowired
	HotelPageUtil HotelPageUtil;
	
	@GetMapping("/hotel")
	public String hotel(HttpServletRequest request, Model model,
			@PageableDefault(size = 10)Pageable pageable) {
//		@PageableDefault에 size로 한 페이지당 출력할 데이터 개수를 지정가능
		int page;
		String keyword = request.getParameter("keyword");
		
		int num;
		if(request.getParameter("num")== null) {//검색 키워드 조건
			num = 0;
		}else {
			num = Integer.parseInt(request.getParameter("num"));
		}

		if(request.getParameter("page") == null) {//페이지
			page = 1;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		pageable = PageRequest.of(page-1, 10);
		
		List<HotelMainFormDto> hotelMainFormDtoList = //데이터 전체 반환
				hotelService.getHotelList(keyword, num, pageable);
		int maxPage = HotelPageUtil.pageButtonInitialize(Math.toIntExact(hotelService.getDataCount()), pageable);
		
		model.addAttribute("num", num);//검색 조건 유지
		model.addAttribute("keyword", keyword);//페이지 이동 후에도 keyword를 유지시키기 위함
		model.addAttribute("hotelMainFormDtoList", hotelMainFormDtoList);//호텔 목록 전달
		model.addAttribute("maxPage", maxPage);
//		페이지에 maxPage만큼 이동버튼이 생김, 마지막 페이지
//		마지막 페이지, 첫 페이지가 0부터 시작한다는걸 감안하고 계산
		model.addAttribute("page", pageable.getPageNumber());//현재 페이지값 유지용
		model.addAttribute("firstPage", HotelPageUtil.getFirstPage());//제일 첫번째 페이지
		model.addAttribute("prevPage", HotelPageUtil.getPrevPage());//이전 10개 페이지 중 마지막 페이지
		model.addAttribute("nextPage", HotelPageUtil.getNextPage());//다음 10개 페이지 중 첫번째 페이지
		model.addAttribute("startPage", HotelPageUtil.getStartPage());//페이지 이동버튼 출력범위, forEach문 시작값
		model.addAttribute("lastPage", HotelPageUtil.getLastPage());//페이지 이동버튼 출력범위, forEach문 끝값
		
//		System.out.println("pageable.getPageSize()(한 화면에 보여줄 데이터 개수):" + pageable.getPageSize());
//		System.out.println("pageable.getPageNumber()(현재 페이지, 첫페이지는 0페이지부터 시작) :" + pageable.getPageNumber());
//		System.out.println("pageable.getOffset()(몇번째 데이터부터 조회할 것인지, page*size를 반환) : " + pageable.getOffset());
//		System.out.println("firstPage : " + hotelPageRequest.getFirstPage());
//		System.out.println("prevPage : " + hotelPageRequest.getPrevPage());
//		System.out.println("nextPage : " + hotelPageRequest.getNextPage());
//		System.out.println("lastPage : " + hotelPageRequest.getLastPage());
//		System.out.println("maxPage(최대 페이지) : " + maxPage);
//		System.out.println("keyword(검색 키워드) : " + keyword);

		return "/hotel/hotel";
	}
	
	@GetMapping("/hotel/detail/{seq}")
	public String hotelDetail(@PathVariable int seq) {//호텔 상세보기/객실리스트 화면
		
		
		
		
		return "/hotel/hotelDetail";
	}
	//	/hotel/reserve/{seq}
	@RequestMapping(value="/roomReservation", method=RequestMethod.GET)
	public String roomReservation(Model model) {//객실 상세보기/예약하기 화면
		return "/hotel/roomReservation";
	}
	
	
//	@RequestMapping(value="/roomReservation", method=RequestMethod.POST)
//	public String roomReservation() {
//		
//		return "/hotel/roomReservation";
//	}
	
//	@GetMapping("/test/hotelscore1") //샘플 리뷰
//	public String createTestHS(@AuthenticationPrincipal User user) {
//		Account account = accountService.getAccountById(user.getUsername());
//		Hotel hotel = hotelService.getHotelBySeq(16L);
//		HotelScore hs = new HotelScore();
//		
//		hs.setHotel(hotel);
//		hs.setWriter(account);
//		hs.setScore(5L);
//		hs.setDescription("너무너무 좋았어요!");
//		hs.setDay(Date.valueOf(LocalDate.now()));
//		
//		hotelScoreService.saveHotelScore(hs);
//		
//		return "redirect:/";
//		
//	}
	
//	@GetMapping("/test/avgtest")//평균 테스트
//	public String createAvg(){
//		List<HotelMainFormDto> hotelMainFormDtoList =
//				hotelService.getHotelList("", 1, PageRequest.of(0, 10));
//		for(HotelMainFormDto hotelMainFormDto : hotelMainFormDtoList) {
//			System.out.println(hotelMainFormDto.getName());
//			System.out.println(hotelMainFormDto.getAvg());
//		}
//		
//		return "redirect:/";
//	}
	
	
}
