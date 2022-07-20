package kg.groupc.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kg.groupc.project.dto.HotelMainFormDto;
import kg.groupc.project.service.HotelService;
import kg.groupc.project.util.HotelPageRequest;

@Controller
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	HotelPageRequest hotelPageRequest;
	
	@GetMapping("/hotel")
	public String hotel(HttpServletRequest request, Model model,
			@PageableDefault(size = 10)Pageable pageable) {//호텔 리스트 화면
		String keyword = request.getParameter("keyword");
		
		int maxPage = hotelPageRequest.pageRequest(keyword, pageable);//마지막 페이지, 첫 페이지가 0부터 시작한다는걸 감안하고 계산
		List<HotelMainFormDto> hotelMainFormDtoList = //데이터 전체 개수 반환
				hotelService.searchPageSimple(keyword, pageable.getPageNumber());
		model.addAttribute("keyword", keyword);//페이지 이동 후에도 keyword를 유지시키기 위함
		model.addAttribute("hotelMainFormDtoList", hotelMainFormDtoList);
		model.addAttribute("maxPage", maxPage);//페이지에 maxPage만큼 이동버튼이 생김
		
		System.out.println("pageable.getPageSize()(한 화면에 보여줄 데이터 개수):" + pageable.getPageSize());
		System.out.println("pageable.getPageNumber()(현재 페이지, 첫페이지는 0페이지부터 시작) :" + pageable.getPageNumber());
		System.out.println("pageable.getOffset()(몇번째 데이터부터 조회할 것인지, page*size를 반환) : " + pageable.getOffset());
		System.out.println("maxPage(최대 페이지) : " + maxPage);
		System.out.println("keyword(검색 키워드) : " + keyword);

		return "/hotel/hotel";
	}
	
	
	
	
	@GetMapping("/hotelDetail")
	public String hotelDetail() {//호텔 상세보기/객실리스트 화면
		return "/hotel/hotelDetail";
	}
	
	@RequestMapping(value="/roomReservation", method=RequestMethod.GET)
	public String roomReservation(Model model) {//객실 상세보기/예약하기 화면
		return "/hotel/roomReservation";
	}
	
	
//	@RequestMapping(value="/roomReservation", method=RequestMethod.POST)
//	public String roomReservation() {
//		
//		return "/hotel/roomReservation";
//	}
	
}
