package kg.groupc.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "/login/loginForm";
	}
	@PostMapping("/login")
	public String loginError(String errorMsg, Model model) {
		System.out.println("login post access");
		System.out.println(errorMsg);
		model.addAttribute("errorMsg", errorMsg);
		return "/login/loginForm";
	}
}
