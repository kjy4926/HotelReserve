package kg.groupc.project.controller.retaurant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.dto.restaurant.RestaurantAddFormDto;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.service.restaurant.RestaurantService;

@Controller
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantService restaurantService;
		
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
	
	// 맛집 상세 페이지
	@GetMapping("/restaurant/restaurantDetail/{seq}")
	public String detailRestaurant(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		
		return "/restaurant/restaurantDetail";
	}
	
	// 맛집 등록 GET(관리자)
	@RequestMapping(value="/restaurant/admin/new", method=RequestMethod.GET)
	public String newRestaurantPage() {
		return "/restaurant/restaurantAdd";
	}
	
	// 맛집 등록 POST(관리자)
	@RequestMapping(value="/restaurant/admin/new", method=RequestMethod.POST)
	public String createRestaurant(RestaurantAddFormDto restaurantAddFormDto, @RequestParam("uploadFile") MultipartFile img) throws Exception {
		Restaurant restaurant = restaurantService.create(restaurantAddFormDto, img);
		
		return "redirect:/restaurant";
	}
		
	// 맛집 수정 페이지(관리자)
	@GetMapping("/restaurant/admin/update/{seq}")
	public String updateRestaurantPage(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		return "/restaurant/restaurantUpdate";
	}
	
	// 맛집 수정 patch(관리자)
	@PatchMapping("/restaurant/admin/edit")
	public String editRestaurant(@PathVariable Long seq, RestaurantAddFormDto restaurantAddFormDto) {
		Restaurant restaurant = restaurantService.edit(seq, restaurantAddFormDto);
		
		return "redirect:/restaurantDetail";
	}
	
	// 맛집 삭제 (관리자)
	public String deleteRestaurant(@PathVariable Long seq) {
		Restaurant restaurant = restaurantService.delete(seq);
		
		return "redirect:/restaurantDetail";
	}
}