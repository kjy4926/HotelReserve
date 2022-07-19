package kg.groupc.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.dto.RestaurantAddFormDto;
import kg.groupc.project.entity.Restaurant;
import kg.groupc.project.repository.RestaurantRepository;
import kg.groupc.project.service.RestaurantService;

@Controller
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	/* 수정전
	// Restaurant 전체 view 페이지
	@GetMapping("/restaurant")
	public String allRestaurantPage(Model model) {
		List<Restaurant> restaurantList = restaurantService.findAll();
		model.addAttribute("restaurantList", restaurantList);
		
		return "/restaurant/restaurant";
	}
	*/

	/*
	@GetMapping("/restaurant/searchKeyword={searchKeyword}")
	public String searchRestaurant(String searchKeyword, Model model, @PageableDefault(sort = "seq", direction = Sort.Direction.DESC)Pageable pageable) {
		List<Restaurant> searchRestaurantList = restaurantService.search(searchKeyword);
		model.addAttribute("searchRestaurantList", searchRestaurantList);
		
		return "/restaurant/restaurant";
	}
	*/
	
	// RestaurantDetail view 페이지
	@GetMapping("/restaurant/restaurantDetail/{seq}")
	public String detailRestaurant(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		
		return "/restaurant/restaurantDetail";
	}
	
	// Restaurant 등록 view 페이지
	@RequestMapping(value="/restaurant/admin/new", method=RequestMethod.GET)
	public String newRestaurantPage() {
		return "/restaurant/restaurantAdd";
	}
	// Restaurant 등록 post
	@RequestMapping(value="/restaurant/admin/new", method=RequestMethod.POST)
	public String createRestaurant(RestaurantAddFormDto restaurantAddFormDto, @RequestParam("uploadFile") MultipartFile img) throws Exception {
		Restaurant restaurant = restaurantService.create(restaurantAddFormDto, img);
		
		return "redirect:/restaurant";
	}
		
	/*
	// Restaurant 등록 view 페이지
	@GetMapping("/restaurant/admin/new")
	public String newRestaurantPage() {
		return "/restaurant/restaurantAdd";
	}
	// Restaurant 등록 post
	@PostMapping("/restaurant/admin/create")
	public String createRestaurant(RestaurantAddFormDto restaurantAddFormDto, @RequestParam("uploadFile") MultipartFile img) throws Exception {
		Restaurant restaurant = restaurantService.create(restaurantAddFormDto, img);
		
		return "redirect:/restaurant/restaurant";
		//return "redirect:/restaurant/"+restaurant.getSeq();
	}
	*/
	// Restaurant 수정 view 페이지
	@GetMapping("/restaurant/admin/update/{seq}")
	public String updateRestaurantPage(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		return "/restaurant/restaurantUpdate";
	}
	
	// Restaurant 수정 patch
	@PatchMapping("/restaurant/admin/edit")
	public String editRestaurant(@PathVariable Long seq, RestaurantAddFormDto restaurantAddFormDto) {
		Restaurant restaurant = restaurantService.edit(seq, restaurantAddFormDto);
		
		return "redirect:/restaurantDetail";
	}
	
	public String deleteRestaurant(@PathVariable Long seq) {
		Restaurant restaurant = restaurantService.delete(seq);
		
		return "redirect:/restaurantDetail";
	}
		
}