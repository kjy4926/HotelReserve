package kg.groupc.project.controller.navigation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kg.groupc.project.controller.BaseController;

@Controller
public class NavigationController extends BaseController{
	@RequestMapping("/nav")
	public String nav() {
		return "/nav/nav";
	}
}
