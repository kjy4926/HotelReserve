package kg.groupc.project.controller.inquire;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kg.groupc.project.controller.BaseController;
import kg.groupc.project.dto.inquire.InquireWriteForm;
import kg.groupc.project.entity.inquire.Inquire;
import kg.groupc.project.repository.inquire.InquireRepository;
import kg.groupc.project.service.account.AccountService;
import kg.groupc.project.service.inquire.InquireService;

@SuppressWarnings("unused")
@Controller
public class InquireController extends BaseController{
	@Autowired
	private InquireService inquireService;
	
	@Autowired
	private InquireRepository inquireRepository;	
	
	@Autowired
	private AccountService accountService;
	
	// 문의글 목록
	@SuppressWarnings("unchecked")
	@GetMapping(value="/inquire")
	public String list(Model model, 
			@PageableDefault(size = 5, sort = "seq", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String searchKeyword) {
			
		Page<Inquire> list = inquireRepository.findAll(pageable);
		if(field.equals("writer")) {
			list = inquireService.search1(searchKeyword, pageable);
		} else if(field.equals("title")) {
			list = inquireService.search2(searchKeyword, pageable);
		}
		
		model.addAttribute("posts", list);
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
		model.addAttribute("hasNext", list.hasNext());
		model.addAttribute("hasPrev", list.hasPrevious());
		
		return "/inquire/inquire";
	}
	
	// 문의글 검색
	@GetMapping("/inquire/search")
	public String search(String keyword, Model model, @PageableDefault(sort= "id", 
		direction = Sort.Direction.DESC) Pageable pageable) {
		List<Inquire> searchList = inquireService.search(keyword);
		model.addAttribute("searchList", searchList);
		return "inquire-search";
	}
	
	// 문의 상세 페이지
	@GetMapping("/inquire/inquireDetail/{seq}")
	public String inquireDetail(@PathVariable Long seq, Model model) {
		Inquire inquire = inquireService.readInquire(seq);
		model.addAttribute("inquire", inquire);
		
		return "/inquire/inquire";
	}
	
	// 문의글 작성 페이지
	@GetMapping(value="/inquire/write")
	public String write(Model model) {
		model.addAttribute("inquireWriteForm", new InquireWriteForm());
		return "/inquire/inquireWriteForm";
	}
	
	// 게시글 등록버튼 눌렀을 때
	@PostMapping(value="/inquire/write")
	public String write(@Valid InquireWriteForm idto, BindingResult br, Model model) {
		if(br.hasErrors()) {
			// 입력 데이터 값 유지
			model.addAttribute("inquireWriteForm", inquireService);			
			return "/inquire/inquireWriteForm";
		}
		inquireService.saveInquire(idto);
		return "redirect:/inquire/inquire";
	}
	
	// 문의 수정 페이지
	@GetMapping("/inquire/edit")
	public String editInquire(@PathVariable Long seq, Model model) {
		Inquire inquire = inquireService.readInquire(seq);
		model.addAttribute("inquire", inquire);
		return "/inquire/edit/{seq}";
	}

	// 수정하기 눌렀을 때
	
	// 문의글 삭제
	public String deleteInquire(@PathVariable Long seq) {
		Inquire inquire = inquireService.delete(seq);
		
		return "redirect:/inquireDetail";
	}
	
/*	
	// 문의글 수정 페이지
	@GetMapping(value="/inquire/edit/{seq}")
	public String edit(@PathVariable long seq, Model model) {
		InquireWriteForm dto = inquireService.read(seq);
		model.addAttribute("inquireWriteForm", inquireService);
		return"/inquire/edit";
	}
	
	//문의글 수정 눌렀을 때
	@PostMapping(value="inquire/edit/{seq}")
	public String edit(@ModelAttribute InquireWriteForm inquireWriteForm, BindingResult result,
			SessionStatus sessionStatus, Model model) {
		if(result.hasErrors()) {
			return "inquire/edit";
		}
		else {
			if(inquireWriteForm.getWriter() == Account.getUserId()) {
				inquireService.edit(inquireWriteForm);
				
			}
		}
		return "/inquire/edit";
	}
	
	
	// 문의글 열람 페이지
	@GetMapping(value="/inquire/read/{seq}")
	public String read(Model model, @PathVariable Long seq) {
		model.addAttribute("inquireWriteForm", inquireService.read(seq));
		return "/inquire/read";
	}
	
	// 문의글 삭제 페이지
	@GetMapping(value="inquire/delete/{seq}")
	public String delete(@PathVariable long seq, Model model) {
		model.addAttribute("seq", seq);
		return "inquire/delete";
	}
	
	// 삭제기능 실행
	@PostMapping(value="inquire/delete/{seq}")
	public String delete(long seq, Account writer, Model model) {
		int rowCount;
		InquireWriteForm inquire = new InquireWriteForm();
		inquire.setSeq(seq);
		inquire.setWriter(writer);
		
		rowCount = InquireService.delete(inquire);
		
		if(rowCount == 0) {
			model.addAttribute("seq", seq);
			model.addAttribute("msg", "");
			return "/inquire/delete";
		}
		else {
			return "redirect:/inquire/inquire";
		}
	}
*/
}
