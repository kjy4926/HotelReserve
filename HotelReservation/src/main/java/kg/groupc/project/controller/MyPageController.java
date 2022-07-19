package kg.groupc.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kg.groupc.project.entity.Account;
import kg.groupc.project.service.AccountService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	private final AccountService accountService;
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		List<Account> list = accountService.getThreeAccounts();
		model.addAttribute("accounts", list);
		return "/mypage/mypage";
	}
}
