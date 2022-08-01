package kg.groupc.project.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.dto.hotel.HotelAddFormDto;
import kg.groupc.project.dto.hotel.HotelDto;
import kg.groupc.project.dto.hotel.RoomAddFormDto;
import kg.groupc.project.dto.hotel.RoomDto;
import kg.groupc.project.dto.restaurant.RestaurantAddFormDto;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.entity.restaurant.Menu;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.service.restaurant.MenuService;
import kg.groupc.project.service.restaurant.RestaurantService;

@Controller
public class AdminController extends BaseController{
	
	@Autowired
	private RestaurantRepository<Restaurant, Long> restaurantRepository;
	
	@Autowired
	private RestaurantService<Restaurant, Long> restaurantService;
	
	@Autowired
	private MenuService<Menu, Long> menuService;
	
	@GetMapping("/admin")
	public String admin() {
		return "/admin/admin";
	}
	
	@GetMapping("/admin/hotel")
	public String hotel(Model model, @RequestParam(value="search", required = false) String search,
				@RequestParam(required=false) String searchType) {
		Page<Hotel> hotelPage;
		if(search==null) {
			hotelPage = hotelService.getHotelPage(0);
		}else {
			hotelPage = hotelService.getSearchHotelPage(0, searchType, search);
		}
		List<HotelDto> hotelListDto = hotelService.getHotelList(hotelPage);
		model.addAttribute("maxP", hotelPage.getTotalPages());
		model.addAttribute("p", 1);
		model.addAttribute("hotelListDto", hotelListDto);
		return "/admin/hotel";
	}
	
	@PostMapping("/admin/hotel/search")
	public String searchHotel(RedirectAttributes redirect, @RequestParam(required = false) String search,
				@RequestParam(required=false) String searchType) {
		if(search == null || search.trim().length()==0) {
			return "redirect:/admin/hotel";
		}
		redirect.addAttribute("search", search);
		redirect.addAttribute("searchType", searchType);
		return "redirect:/admin/hotel";
	}
	
	@GetMapping("/admin/hotel/{p}")
	public String hotel(@PathVariable int p, Model model, @RequestParam(required = false) String search,
				@RequestParam(required=false) String searchType) {
		Page<Hotel> hotelPage;
		if(search==null) {
			hotelPage = hotelService.getHotelPage(p-1);
		}else {
			hotelPage = hotelService.getSearchHotelPage(p-1, searchType, search);
		}
		List<HotelDto> hotelListDto = hotelService.getHotelList(hotelPage);
		model.addAttribute("maxP", hotelPage.getTotalPages());
		model.addAttribute("p", p);
		model.addAttribute("hotelListDto", hotelListDto);
		return "/admin/hotel";
	}
	
	@GetMapping("/admin/hotel/new")
	public String hotelAdd() {
		return "/admin/hotelAdd";
	}
	
	@PostMapping("/admin/hotel/new")
	public String postHotelAdd(HotelAddFormDto hotelAddFormDto) {
		hotelService.saveHotel(hotelAddFormDto);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/hotel/change/{seq}")
	public String hotelChange(@PathVariable Long seq, Model model) {
		Hotel hotel = hotelService.getHotelBySeq(seq);
		HotelDto hotelDto = hotelService.hotelToHotelDto(hotel);
		model.addAttribute("hotel", hotelDto);		
		return "/admin/hotelChange";
	}
	
	@PostMapping("/admin/hotel/change/{seq}")
	public String postHotelChange(@PathVariable Long seq, Model model, HotelAddFormDto hotelAddFormDto) {
		hotelService.updateHotel(seq, hotelAddFormDto);
		return "redirect:/admin/hotel";
	}
	
	@PostMapping("/admin/hotel/delete")
	@ResponseBody
	public boolean hotelDelete(Long seq) {
		return hotelService.deleteHotel(seq);
	}
	
	@GetMapping("/admin/room/{seq}")
	public String room(@PathVariable Long seq, Model model) {
		HotelDto hotelDto = hotelService.hotelToHotelDto(hotelService.getHotelBySeq(seq));
		List<RoomDto> roomDtoList = hotelService.getHotelRoomList(seq);
		model.addAttribute("hotel", hotelDto);
		model.addAttribute("roomDtoList", roomDtoList);
		return "/admin/room";
	}
	
	@GetMapping("/admin/hotel/room/new/{seq}")
	public String roomAdd(@PathVariable Long seq, Model model) {
		model.addAttribute("hotelSeq", seq);
		return "/admin/roomAdd";
	}
	
	@PostMapping("/admin/hotel/room/new/{seq}")
	public String postRoomAdd(RoomAddFormDto roomAddFormDto) {
		roomService.saveRoom(roomAddFormDto);
		return "redirect:/admin/hotel";
	}
	
	@GetMapping("/admin/room/change/{hseq}/{rseq}")
	public String roomChange(@PathVariable Long hseq, @PathVariable Long rseq, Model model) {
		Room room = roomService.getRoomBySeq(rseq);
		RoomDto roomDto = roomService.RoomToRoomDto(room);
		model.addAttribute("room", roomDto);		
		model.addAttribute("hotelSeq", hseq);
		return "/admin/roomChange";
	}
	
	@PostMapping("/admin/room/change/{hseq}/{rseq}")
	public String postRoomChange(@PathVariable Long hseq, @PathVariable Long rseq, Model model, RoomAddFormDto roomAddFormDto) {
		roomService.updateRoom(rseq, roomAddFormDto);
		return "redirect:/admin/room/" + hseq;
	}
	
	@PostMapping("/admin/room/delete")
	@ResponseBody
	public boolean roomDelete(Long seq) {
		return roomService.deleteRoom(seq);
	}
	
	@GetMapping("/denied")
	public String denied() {
		return "/alert/denied";
	}
	
	// 맛집 리스트 + 검색 + 페이징
	@RequestMapping(value="/admin/restaurant")
	public String allRestaurantPage(Model model,
			@PageableDefault(size = 5, sort = "seq", direction = Sort.Direction.DESC)Pageable pageable, 
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String searchKeyword) {
		
		Page<Restaurant> pageList = restaurantRepository.findAll(pageable);		
		if(field.equals("name")) {
			pageList = restaurantService.search1(searchKeyword, pageable);
		} else if(field.equals("address")) {
			pageList = restaurantService.search2(searchKeyword, pageable);
		}
		
		int pageNumber = pageList.getPageable().getPageNumber();
		int totalPages = pageList.getTotalPages();
		int pageBlock = 5;
		int startBlockPage = ((pageNumber)/pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;
		
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("pageList", pageList);
	
		return "/admin/restaurant";
		}
	
	// 맛집 상세(사용자)
	@GetMapping("/admin/restaurant/{seq}")
	public String restaurantDetailPage(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		
		List<Menu> menuList = menuService.menuList(seq);
		model.addAttribute("menuList", menuList);
		return "/admin/restaurantDetail";
	}
	
	// 맛집 등록
	@RequestMapping(value="/admin/restaurant/new", method=RequestMethod.GET)
	public String newRestaurantPage(@AuthenticationPrincipal User user, Model model) {
		String userName = user.getUsername();
		model.addAttribute("userName", userName);
		
		return "/admin/restaurantAdd";
	}
	
	// 맛집 등록
	@RequestMapping(value="/admin/restaurant/new", method=RequestMethod.POST)
	public String createRestaurant(RestaurantAddFormDto restaurantAddFormDto, 
			@RequestParam("uploadFile") MultipartFile img, HttpServletRequest req) throws Exception {		
		String path = req.getServletContext().getRealPath("/resources/img/restaurantImg/");
		restaurantService.create(restaurantAddFormDto, img, path);
		
		return "redirect:/admin/restaurant";
	}
	
	// 맛집 수정
	@GetMapping("/admin/restaurant/update/{seq}")
	public String updateRestaurantPage(@PathVariable("seq") Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		return "/admin/restaurantUpdate";
	}
	
	@PostMapping("/admin/restaurant/update")
	public String editRestaurant(Long seq, RestaurantAddFormDto restaurantAddFormDto) {
		restaurantService.edit(seq, restaurantAddFormDto);
		
		return "redirect:/admin/restaurant/" + restaurantAddFormDto.getSeq();	
	}
	
	// 맛집 삭제
	@GetMapping("/admin/restaurant/delete/{seq}")
	public String deleteRestaurantPage(@PathVariable Long seq) {
		restaurantService.delete(seq);
		return "redirect:/admin/restaurant";
	}
}
