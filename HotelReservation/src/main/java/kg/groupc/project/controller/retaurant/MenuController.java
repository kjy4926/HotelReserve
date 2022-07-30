package kg.groupc.project.controller.retaurant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		return "/admin/menuAdd";
	}
	
	@RequestMapping(value="/admin/{seq}/new", method=RequestMethod.POST)
	public String createMenu(MenuAddFormDto menuAddFormDto, @RequestParam("uploadFile") 
					MultipartFile img, HttpServletRequest req) throws Exception {
		String path = req.getServletContext().getRealPath("/resources/img/menuImg/");
		menuService.create(menuAddFormDto, img, path);
		
		return "redirect:/admin/restaurant/{seq}";
	}
	
	// 메뉴 상세
	@GetMapping("/admin/restaurant/menu/{seq}")
	public String menuDetailPage(@PathVariable Long seq, Model model) {
		Menu menu = menuService.findMenu(seq);
		model.addAttribute("menu", menu);
		
		return "/admin/menuDetail";
	}
	
	// 메뉴 삭제
	@GetMapping("/admin/restaurant/menu/delete/{seq}")
	public String deleteMenuPage(@PathVariable Long seq) {
		Menu menuDelete = menuService.delete(seq);
		return "redirect:/admin/restaurant";
	}
	
	// 메뉴 수정
	@GetMapping("/admin/restaurant/menu/update/{seq}")
	public String updateMenuPage(@PathVariable("seq") Long seq, Model model) {
		Menu menu = menuService.findMenu(seq);
		model.addAttribute("menu", menu);
		return "/admin/menuUpdate";
	}
	
	@PostMapping("/admin/restaurant/menu/update")
	public String editMenu(Long seq, MenuAddFormDto menuAddFormDto, Menu menu) {
		menuService.edit(seq, menuAddFormDto);
		
		return "redirect:/admin/restaurant/menu/" + menu.getSeq();
	}
}
