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

@Controller
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@GetMapping("/hotel")
	public String hotel(HttpServletRequest request, Model model,
			@PageableDefault(size = 10)Pageable pageable) {//호텔 리스트 화면
		String keyword = request.getParameter("keyword");
		Long maxPage = hotelService.getHotelList(keyword);
		
		List<HotelMainFormDto> hotelMainFormDtoList = 
//		if(hotelMainFormDtoList == null) {//DB에서 넘겨받은 데이터가 없을 때 에러페이지 방지용
//		hotelMainFormDtoList = new ArrayList<HotelMainFormDto>();
//		}
		hotelService.searchPageSimple(keyword, pageable);
		
		System.out.println("maxPage" + maxPage);
		model.addAttribute("hotelMainFormDtoList", hotelMainFormDtoList);
		System.out.println(pageable.getOffset());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getPageSize());
		model.addAttribute("maxPage", maxPage);

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
