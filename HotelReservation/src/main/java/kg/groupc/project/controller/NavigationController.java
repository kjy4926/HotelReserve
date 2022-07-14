package kg.groupc.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
	@GetMapping("/nav")
	public String nav() {
		return "/nav/nav";
	}
}
