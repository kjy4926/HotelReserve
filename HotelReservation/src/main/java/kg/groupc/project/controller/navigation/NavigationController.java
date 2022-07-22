package kg.groupc.project.controller.navigation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {
	@RequestMapping("/nav")
	public String nav() {
		return "/nav/nav";
	}
}
