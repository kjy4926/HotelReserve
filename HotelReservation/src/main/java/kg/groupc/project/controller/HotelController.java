package kg.groupc.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kg.groupc.project.dto.HotelMainFormDto;
import kg.groupc.project.service.HotelService;

@Controller
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@GetMapping("/hotel")
	public String hotel(HttpServletRequest request, Model model) {//호텔 리스트 화면
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		if(keyword == null |keyword == "") {//
			List<HotelMainFormDto> hotelMainFormDtoList = 
					hotelService.getHotelListDefault();
//			if(hotelMainFormDtoList == null) {//DB에서 넘겨받은 데이터가 없을 때 에러페이지 방지용
//				hotelMainFormDtoList = new ArrayList<HotelMainFormDto>();
//			}
			model.addAttribute("hotelMainFormDtoList", hotelMainFormDtoList);
		}else {
			List<HotelMainFormDto> hotelMainFormDtoList = hotelService.SearchHotels(keyword);
			model.addAttribute("hotelMainFormDtoList", hotelMainFormDtoList);
		}
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
