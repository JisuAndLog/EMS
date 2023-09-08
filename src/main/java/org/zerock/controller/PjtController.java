package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.domain.UsrPjtDTO;
import org.zerock.domain.VO.CodeDTLVO;
import org.zerock.domain.VO.PjtVO;
import org.zerock.service.CodeService;
import org.zerock.service.PjtService;
import org.zerock.service.UsrPjtService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/pjt")
public class PjtController {

	@Autowired
	private PjtService service;

	@Autowired
	private CodeService cservice;

	@Autowired
	private UsrPjtService upservice;

	@GetMapping("/pjt_list")
	public String getPjtList(@RequestParam(value = "pjtNm", defaultValue = "", required = false) String pjtNm,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "cmpyNm", defaultValue = "0001", required = false) String cmpyNm,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
			@RequestParam(value = "amount", defaultValue = "10", required = false) int amount,

			Criteria cri, Model model) {

		// 사용할 날짜 형식 지정
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		// 현재 날짜 가져오기
		String currentDate = dateFormat.format(new Date());

		// 'from' 및 'to' 파라미터가 비어 있다면 기본 날짜로 설정
		if (from == null || from.isEmpty()) {
			from = currentDate;
		}
		if (to == null || to.isEmpty()) {
			to = currentDate;
		}

		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("cmpyNm", cmpyNm);

		int total = service.getTotalCount(from, to, cmpyNm, pjtNm, pageNum, amount);
		model.addAttribute("list", service.searchWithTypes(from, to, cmpyNm, pjtNm, pageNum, amount));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		return "/pjt/pjt_list";
	}

	@PostMapping("/pjt_list")
	public String postUsrList(@RequestParam(value = "pjtNm", required = false) String pjtNm,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "cmpyNm", required = false) String cmpyNm,
			@RequestParam(value = "pageNum", required = false) int pageNum,
			@RequestParam(value = "amount", required = false) int amount, Model model, Criteria cri) {

		int total = service.getTotalCount(from, to, cmpyNm, pjtNm, pageNum, amount);
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("cmpyNm", cmpyNm);

		//List<CodeDTLVO> decode = cservice.getCmpyNmCode();

		//model.addAttribute("decode", decode);
		model.addAttribute("list", service.searchWithTypes(from, to, cmpyNm, pjtNm, pageNum, amount));
		model.addAttribute("pageMaker", new PageDTO(cri, total));

		return "pjt/pjt_list";
	}

	@GetMapping("/enroll_pjt")
	public String showEnrollPage(Model model) {
		//List<CodeDTLVO> decode = cservice.getCmpyNmCode();

		//model.addAttribute("decode", decode);
		return "/pjt/enroll_pjt";
	}
	
	@PostMapping("/enroll_pjt")
	public String enrollPjt(@ModelAttribute PjtVO pjt) {
		service.enrollPjt(pjt);
		return "redirect:/pjt/pjt_list";
	}
	
	@GetMapping("/pjt_detail")
	public String getPjtByPjtNo(@RequestParam("pjtNo") String pjtNo, Model model) {
//		PjtVO pjt = service.getPjtDetail(pjtNo);
		model.addAttribute("up", upservice.getUsrPjtListByPjtNo(pjtNo));
//		model.addAttribute("pjt", pjt);
		return "/pjt/pjt_detail";
	}
	
//	@GetMapping("/w_pjt_detail")
//	@ResponseBody
//	public PjtVO getPjtByPjtNoInWindow(@RequestParam("pjtNo") String pjtNo, Model model) {
//		PjtVO pjt = service.getPjtDetail(pjtNo);
//		return pjt;
//	}
	
	@GetMapping("/w_pjt_members")
	@ResponseBody
	public List<UsrPjtDTO> getPjtMembersByPjtNoInWindow(@RequestParam("pjtNo") String pjtNo, Model model) {
		
		List<UsrPjtDTO> upList = upservice.getUsrPjtListByPjtNo(pjtNo);
		return upList;
	}
	
	@PostMapping("/delete_pjt")
	@ResponseBody
	 public ResponseEntity<Map<String, String>> deletePjt(@RequestBody List<String> pjtId) {

		for(String id : pjtId) {
			log.info(id+"번 프로젝트 삭제됨");
			service.deletePjt(id);
		}
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "프로젝트가 삭제되었습니다.");

	    return ResponseEntity.ok(response);
	  }

}
