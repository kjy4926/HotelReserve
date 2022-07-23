package kg.groupc.project.controller.account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.service.account.AccountService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController extends BaseController{

	@GetMapping("/login")
	public String login() {
		return "/login/loginForm";
	}
	@PostMapping("/login")
	public String loginError(Model model, HttpServletRequest request) {
		String errorMsg = request.getAttribute("errorMsg").toString();
		System.out.println("login post access");
		model.addAttribute("errorMsg", errorMsg);
		return "/login/loginForm";
	}
}
