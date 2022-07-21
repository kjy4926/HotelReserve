package kg.groupc.project.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kg.groupc.project.dto.InfoChangeFormDto;
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
	@GetMapping("/mypage/infoChange")
	public String infoChange(@AuthenticationPrincipal User user, Model model) {
		Account account = accountService.getAccountById(user.getUsername());
		InfoChangeFormDto infoChangeFormDto = new InfoChangeFormDto();
		infoChangeFormDto.setUsername(account.getName());
		infoChangeFormDto.setUserId(account.getUserId());
		infoChangeFormDto.setEmail(account.getEmail());
		infoChangeFormDto.setPhone(account.getPhone());
		model.addAttribute("infoChangeFormDto", infoChangeFormDto);
		return "/mypage/account/infoChangeForm";
	}
	@PostMapping("/mypage/infoChange")
	public String postInfoChange(@Valid InfoChangeFormDto infoChangeFormDto, Errors errors, Model model,
				@AuthenticationPrincipal User user) {
		if(errors.hasErrors()) {
			model.addAttribute("infoChangeFormDto", infoChangeFormDto);
			// 입력값 유효성 검사
			Map<String, String> validatorResult = accountService.validateHandling(errors);
			for(String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
		}
		accountService.changeAccountInfo(infoChangeFormDto);
		model.addAttribute("msg", "개인 정보 변경이 완료되었습니다.\n개인 정보가 노출되지 않도록 조심해주세요.");
		model.addAttribute("type", "change");
		return "/alert/success";
	}
}
