package kg.groupc.project.controller.retaurant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.dto.restaurant.RestaurantAddFormDto;
import kg.groupc.project.entity.restaurant.Menu;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.service.restaurant.MenuService;
import kg.groupc.project.service.restaurant.RestaurantService;

@Controller
public class RestaurantController extends BaseController{
	
	@Autowired
	private RestaurantRepository<Restaurant, Long> restaurantRepository;
	
	@Autowired
	private RestaurantService<Restaurant, Long> restaurantService;
	
	@Autowired
	private MenuService<Menu, Long> menuService;
	
	// 맛집 리스트 + 검색 + 페이징
	@RequestMapping(value="/restaurant")
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
		
		return "/restaurant/restaurant";
	}
	
	// 맛집 상세(사용자)
	@GetMapping("/restaurant/{seq}")
	public String restaurantDetailPage(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		
		List<Menu> menuList = menuService.menuList(seq);
		model.addAttribute("menuList", menuList);
		return "/restaurant/restaurantDetail";
	}
			
	// 맛집 등록
	@RequestMapping(value="/admin/restaurant/new", method=RequestMethod.GET)
	public String newRestaurantPage() {
		return "/restaurant/restaurantAdd";
	}
	
	/*
	// 맛집 등록
	@RequestMapping(value="/admin/restaurant/new", method=RequestMethod.POST)
	public String createRestaurant(RestaurantAddFormDto restaurantAddFormDto, 
			@RequestParam("uploadFile") MultipartFile img) throws Exception {		
		restaurantService.create(restaurantAddFormDto, img);
		
		return "redirect:/restaurant";
	}
	*/
	
	// 맛집 등록
	@RequestMapping(value="/admin/restaurant/new", method=RequestMethod.POST)
	public String createRestaurant(RestaurantAddFormDto restaurantAddFormDto, 
			@RequestParam("uploadFile") MultipartFile img, HttpServletRequest req) throws Exception {		
		String path = req.getServletContext().getRealPath("/resources/img/restaurantImg/");
		restaurantService.create(restaurantAddFormDto, img, path);
		
		return "redirect:/restaurant";
	}
		
	// 맛집 수정
	@GetMapping("/admin/restaurant/update/{seq}")
	public String updateRestaurantPage(@PathVariable("seq") Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		return "/restaurant/restaurantUpdate";
	}
	
	@PostMapping("/admin/restaurant/update")
	public String editRestaurant(Long seq, RestaurantAddFormDto restaurantAddFormDto) {
		restaurantService.edit(seq, restaurantAddFormDto);
		
		return "redirect:/restaurant/" + restaurantAddFormDto.getSeq();	
	}
	
	// 맛집 삭제
	@GetMapping("/admin/restaurant/delete/{seq}")
	public String deleteRestaurantPage(@PathVariable Long seq) {
		Restaurant restaurantDelete = restaurantService.delete(seq);
		return "redirect:/restaurant";
	}
}