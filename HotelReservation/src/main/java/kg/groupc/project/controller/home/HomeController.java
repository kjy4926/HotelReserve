package kg.groupc.project.controller.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.entity.hotel.Room;

@Controller
public class HomeController extends BaseController{
	@GetMapping("/")
	public String home() {
		hotelService.getBySeq(2L);
		return "home";
	}
}
