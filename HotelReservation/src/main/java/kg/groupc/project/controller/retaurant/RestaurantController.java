package kg.groupc.project.controller.retaurant;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.restaurant.Menu;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.service.restaurant.MenuService;
import kg.groupc.project.service.restaurant.RestaurantService;
import kg.groupc.project.service.restaurant.StarsService;

@Controller
public class RestaurantController extends BaseController{
	
	@Autowired
	private RestaurantRepository<Restaurant, Long> restaurantRepository;
	
	@Autowired
	private RestaurantService<Restaurant, Long> restaurantService;
	
	@Autowired
	private MenuService<Menu, Long> menuService;
	
	@Autowired
	private StarsService<Stars, Long> starsService;
	
	@Autowired
	private AccountRepository<Account, Long> accountRepository;
	
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
	
	/*
	// 맛집 상세(사용자) : 수정 전
	@GetMapping("/restaurant/{seq}")
	public String restaurantDetailPage(@PathVariable Long seq, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		
		List<Menu> menuList = menuService.menuList(seq);
		model.addAttribute("menuList", menuList);
		return "/restaurant/restaurantDetail";
	}
	*/
	
	// 맛집 상세 및 찜하기(사용자) : 수정 후
	@GetMapping("/restaurant/{seq}")
	public String restaurantDetailPage(@PathVariable Long seq, @AuthenticationPrincipal User user, String userId, Model model) {
		Restaurant restaurant = restaurantService.findRestaurant(seq);
		model.addAttribute("restaurant", restaurant);
		
		List<Menu> menuList = menuService.menuList(seq);
		model.addAttribute("menuList", menuList);
		
		model.addAttribute("userId", user.getUsername());
		return "/restaurant/restaurantDetail";
	}
	
	// 맛집 상세 및 찜하기(사용자)
	@PostMapping("/restaurant/{seq}/choice")
	public String choiceRestaurant(@PathVariable Long seq, @RequestParam Long restaurant, @RequestParam String userId) {
		starsService.saveStars(restaurant, userId);
		return "redirect:/restaurant/"+seq;
	}
	
	// 이미 찜하기 한 맛집일 경우 알림 출력
	@PostMapping("/restaurant/{seq}/choiceCheck")
	@ResponseBody
	public boolean choiceRestaurantCheck(@RequestParam Long restaurant, @RequestParam String userId) {
		return starsService.starsDupCheck(restaurant, userId);
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
}