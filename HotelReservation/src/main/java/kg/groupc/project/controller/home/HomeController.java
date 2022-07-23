package kg.groupc.project.controller.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kg.groupc.project.controller.BaseController;

@Controller
public class HomeController extends BaseController{
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
