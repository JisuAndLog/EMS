package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.domain.VO.CodeDTLVO;
import org.zerock.service.CodeService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/code")
public class CodeController {
	
	@Autowired
	private CodeService cservice;
	
	@GetMapping("/codeVal")
	@ResponseBody
	public List<CodeDTLVO> getCodeVal(@RequestParam String codeVal){
		log.info("ajax codeVal 호출됨");
		log.info(codeVal);
		List<CodeDTLVO> codes = cservice.getCodeVal(codeVal);
		return codes;
	}
	
	@GetMapping("/code_list")
	public String getCodeList(Model model, Criteria cri){
		model.addAttribute("list", cservice.getCodeList());
		model.addAttribute("pageMaker",new PageDTO(cri,123));

		return "/code/code_list";
	}
	
	@GetMapping("/enroll_code")
	public String getEnrollPage(Model model) {
		model.addAttribute("list", cservice.getCode());
		return "/code/enroll_code";
	}
	
	@PostMapping("/enroll_code")
	public String postEnroll(@ModelAttribute CodeDTLVO vo) {
		cservice.insertDCode(vo);
		
		return "redirect:/code/code_list";
	}

}
