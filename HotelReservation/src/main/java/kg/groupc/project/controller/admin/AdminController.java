package kg.groupc.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.dto.hotel.HotelAddFormDto;

@Controller
public class AdminController extends BaseController{
	@GetMapping("/admin")
	public String admin() {
		return "/admin/admin";
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
	
	@GetMapping("/denied")
	public String denied() {
		return "/alert/denied";
	}
}
