package kg.groupc.project.controller.home;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kg.groupc.project.entity.account.Account;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(@AuthenticationPrincipal User user) {
		System.out.println(user.getUsername());
		return "home";
	}
}
