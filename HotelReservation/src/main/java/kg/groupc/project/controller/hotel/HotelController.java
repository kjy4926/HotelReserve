package kg.groupc.project.controller.hotel;

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

import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.service.hotel.HotelService;
import kg.groupc.project.util.HotelPageUtil;

@Controller
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	HotelPageUtil hotelPageRequest;
	
	@GetMapping("/hotel")
	public String hotel(HttpServletRequest request, Model model,
			@PageableDefault(size = 10)Pageable pageable) {//호텔 리스트 화면
		//@PageableDefault에 size로 한 페이지당 출력할 데이터 개수를 지정가능
		String keyword = request.getParameter("keyword");
		int maxPage = hotelPageRequest.pageRequest(keyword, pageable);
		
		List<HotelMainFormDto> hotelMainFormDtoList = //데이터 전체 개수 반환
				hotelService.getHotelList(keyword, pageable);
//		if(hotelMainFormDtoList.size() == 0) {//혹시모를 null값 출력시 에러페이지 방지용
//			hotelService.searchPageSimple("", pageable);
//		}
		model.addAttribute("keyword", keyword);//페이지 이동 후에도 keyword를 유지시키기 위함
		model.addAttribute("hotelMainFormDtoList", hotelMainFormDtoList);//호텔 목록 전달
		model.addAttribute("maxPage", maxPage);
		//페이지에 maxPage만큼 이동버튼이 생김, 마지막 페이지
		//마지막 페이지, 첫 페이지가 0부터 시작한다는걸 감안하고 계산
		model.addAttribute("page", pageable.getPageNumber());//현재 페이지값 유지용
		model.addAttribute("firstPage", hotelPageRequest.getFirstPage());//제일 첫번째 페이지
		model.addAttribute("prevPage", hotelPageRequest.getPrevPage());//이전 10개 페이지 중 마지막 페이지
		model.addAttribute("nextPage", hotelPageRequest.getNextPage());//다음 10개 페이지 중 첫번째 페이지
		model.addAttribute("startPage", hotelPageRequest.getStartPage());//페이지 이동버튼 출력범위, forEach문 시작값
		model.addAttribute("lastPage", hotelPageRequest.getLastPage());//페이지 이동버튼 출력범위, forEach문 끝값
		
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
	
	
	
	
	@GetMapping("/hotel/detail")
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
