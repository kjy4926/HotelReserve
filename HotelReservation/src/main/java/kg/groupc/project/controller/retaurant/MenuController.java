package kg.groupc.project.controller.retaurant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.dto.restaurant.MenuAddFormDto;
import kg.groupc.project.entity.restaurant.Menu;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.service.restaurant.MenuService;
import kg.groupc.project.service.restaurant.RestaurantService;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService<Menu, Long> menuService;
	
	@Autowired
	private RestaurantService<Restaurant, Long> restaurantService;
	
	// 메뉴 등록
	@RequestMapping(value="/admin/{seq}/new", method=RequestMethod.GET)
	public String newMenuPage(@PathVariable("seq") Long restaurant, Model model) {
		model.addAttribute("restaurant", restaurantService.findRestaurant(restaurant));
		return "/restaurant/menuAdd";
	}
	
	@RequestMapping(value="/admin/{seq}/new", method=RequestMethod.POST)
	public String createMenu(MenuAddFormDto menuAddFormDto, @RequestParam("uploadFile") 
					MultipartFile img, HttpServletRequest req) throws Exception {
		String path = req.getServletContext().getRealPath("/resources/img/menuImg/");
		menuService.create(menuAddFormDto, img, path);
		
		return "redirect:/restaurant/{seq}";
	}
	
	// 메뉴 상세
	@GetMapping("/restaurant/menu/{seq}")
	public String menuDetailPage(@PathVariable Long seq, Model model) {
		Menu menu = menuService.findMenu(seq);
		model.addAttribute("menu", menu);
		
		return "/restaurant/menuDetail";
	}
	
	// 메뉴 삭제
	@GetMapping("/restaurant/menu/delete/{seq}")
	public String deleteMenuPage(@PathVariable Long seq) {
		Menu menuDelete = menuService.delete(seq);
		return "redirect:/restaurant";
	}
	
	
	/*
	@GetMapping("/restaurant/{seq}")
	public String menuPage(@PathVariable Long restaurant, Model model) {
		List<Menu> menuList = menuService.findAll();
		model.addAttribute("menuList", menuList);
		return "/restaurant/restaurantDetail";
	}
	
	/*
	// 메뉴 등록
	@RequestMapping(value="/admin/{seq}/new", method=RequestMethod.GET)
	public String newMenuPage(@PathVariable("seq") Long restaurant, Model model) {
		model.addAttribute("menuAddFormDto", new MenuAddFormDto());
		return "/restaurant/menuAdd";
	}
	
	@RequestMapping(value="/admin/{seq}/new", method=RequestMethod.POST)
	public String createMenu(Long restaurant,
			MenuAddFormDto menuAddFormDto, @RequestParam("uploadFile") 
					MultipartFile img) throws Exception {
		menuService.create(restaurant, menuAddFormDto, img);
		
		return "redirect:/restaurant/{seq}";
	}
	
 	레스토랑 상세페이지에 같이
	// 메뉴 리스트
	@GetMapping("/restaurant/{seq}")
	public String MenuPage(Model model) {
		List<Menu> menuList = menuService.findAll();
		model.addAttribute("menuList", menuList);
		
		return "/restaurant/restaurantDetail";
	}
	
	
	@GetMapping("/admin/menu/new")
	// 메뉴 보기
	public ResponseEntity<List<MenuAddFormDto>> menus(@PathVariable Long restaurant) {
		List<MenuAddFormDto> dtos = menuService.menus(restaurant);
		
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
	@PostMapping("/admin/menu/new")
	public ResponseEntity<MenuAddFormDto> create(@PathVariable Long restaurant,
			@RequestBody MenuAddFormDto dto, @RequestParam("uploadFile")
					MultipartFile img) throws Exception {
		MenuAddFormDto createDto = menuService.create(restaurant, dto, img);
		
		return ResponseEntity.status(HttpStatus.OK).body(createDto);
	}
	*/
	
	
	
	
	
	
	/*
	// 메뉴 등록
	@RequestMapping(value="/admin/{seq}/new", method=RequestMethod.GET)
	public String newMenuPage() {
		return "/restaurant/menuAdd";
	}
	
	@RequestMapping(value="/admin/{seq}/new", method=RequestMethod.POST)
	public String createMenu(MenuAddFormDto menuAddFormDto, @RequestParam("uploadFile") MultipartFile img) throws Exception {
		menuService.create(menuAddFormDto, img);
		
		return "redirect:/restaurant";
	}
	*/

}
