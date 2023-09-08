package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.domain.VO.UsrPjtVO;
import org.zerock.service.PjtService;
import org.zerock.service.UsrPjtService;
import org.zerock.service.UsrService;

@Controller
@RequestMapping("/up")
public class UsrPjtController {

	@Autowired
	private UsrPjtService service;

	@Autowired
	private UsrService uservice;

	@Autowired
	private PjtService pservice;

	@GetMapping("/up_list")
	public String getUsrPjtList(Model model, Criteria cri) {

		model.addAttribute("list", service.getUsrPjtList());
		model.addAttribute("pageMaker", new PageDTO(cri, 123));
		return "/up/up_list";
	}

	@PostMapping("/up_list")
	public String postUsrPjtList(Model model, 
			@RequestParam(value = "nm", required = false) String nm,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "pjtRole", required = false) String pjtRole, Criteria cri) {

		model.addAttribute("list", service.getUsrPjtListByTypes(nm, from, to, pjtRole));
		model.addAttribute("pageMaker", new PageDTO(cri, 123));

		return "up/up_list";
	}
	
	@GetMapping("/enroll_up")
	public String enrollPjtMembers(Model model, Criteria cri) {
		
		model.addAttribute("project", pservice.getPjtList(cri)); // cri 제거한 코드로 수정 요망
		model.addAttribute("usr", uservice.getUsrNoNmIsNull());
		model.addAttribute("pageMaker", new PageDTO(cri,123));
		
		return "/up/enroll_up";
	}
	
	@PostMapping("/enroll_pjt")
	public String enrollPjtMembers(@ModelAttribute UsrPjtVO upvo, RedirectAttributes redirectAttributes) {
		try {
			service.enrollUPMembers(upvo);
		}catch(DuplicateKeyException de){
			   redirectAttributes.addFlashAttribute("error", "duplicate");
		        return "redirect:/up/enroll_up";
		}
		
		return "redirect:/up/up_list";
	}

}
