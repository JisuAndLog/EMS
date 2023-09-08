package org.zerock.controller;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.domain.VO.UsrPjtVO;
import org.zerock.domain.VO.UsrVO;
import org.zerock.service.PjtService;
import org.zerock.service.UsrPjtService;
import org.zerock.service.UsrService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/usr")
public class UsrController {	

	@Autowired
	private UsrService service;

	@Autowired
	private PjtService pservice;

	@Autowired
	private UsrPjtService upservice;

	// 전체 조회
	@GetMapping("/usrList")
	public String getList(
			@RequestParam(value = "nm", defaultValue = "", required = false) String nm,
			@RequestParam(value = "jobSkill", defaultValue = "04", required = false) String jobSkill,
			@RequestParam(value = "inoffi_sts", defaultValue = "01", required = false) String inoffi_sts,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
			@RequestParam(value = "amount", defaultValue = "10", required = false) int amount,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to, Criteria cri, Model model) {

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
		model.addAttribute("jobSkill", jobSkill);
		model.addAttribute("inoffi_sts", inoffi_sts);

		List<UsrVO> list = service.searchWithTypes(nm, jobSkill, inoffi_sts, pageNum, amount, from, to);
		int total = service.countSearchWithTypes(nm, jobSkill, inoffi_sts, pageNum, amount, from, to);

		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		

		return "/usr/usrList";
	}

	@PostMapping("/usrList")
	public String postCurrentMembers(
			@RequestParam(value = "nm", defaultValue = "", required = false) String nm,
			@RequestParam(value = "jobSkill", defaultValue = "04", required = false) String jobSkill,
			@RequestParam(value = "inoffi_sts", defaultValue = "01", required = false) String inoffi_sts,
			@RequestParam(value = "pageNum", required = false) int pageNum,
			@RequestParam(value = "amount", required = false) int amount,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to, Model model, Criteria cri, HttpSession session) {

		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("jobSkill", jobSkill);
		model.addAttribute("inoffi_sts", inoffi_sts);
		
		log.info("검색됨");
		log.info("1. nm: "+nm);
		log.info("2. jobSkill : " +jobSkill);
		log.info("3. inoffi_sts : "+inoffi_sts);
		log.info("입사일 from : "+from);
		log.info("입사일 to: " +to);

		List<UsrVO> list = service.searchWithTypes(nm, jobSkill, inoffi_sts, pageNum, amount, from, to);
		int total = service.countSearchWithTypes(nm, jobSkill, inoffi_sts, pageNum, amount, from, to);
		model.addAttribute("list", list);
		
		log.info("list: "+list);
		log.info("total: "+total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));

		return "/usr/usrList";
	}
	
	//등록 관련
	@GetMapping("/enroll_usr")
	public String showEnrollPage(Model model) {
		
		return "/usr/enroll_usr";
	}
		
		
	@PostMapping("/enroll_usr")
	public String enrollUsr( UsrVO usr, Model model) {
		   
		
		System.out.println("usr----------" + usr);
		    service.enroll(usr);
		    
		    
		    return "redirect:/usr/enroll_success";
		}
	
	@GetMapping("/enroll_success")
	public String enrollUsrSuccess() {
		return "/usr/enroll_success";
	}

	// 단일 사용자 조회
	@GetMapping("/usr_detail")
	public String usrDetail(@RequestParam("no") String no, Model model) {
		UsrVO usr = service.getUsrByNo(no);
		model.addAttribute("usr", usr);
		return "/usr/usr_detail";
	}

	// 사용자 상세 정보 수정
	@GetMapping("/modify_usr")
	public String modfiyUsr(@RequestParam("no") String no, Model model) {

		UsrVO usr = service.getUsrByNo(no);
		model.addAttribute("usr", usr);
		return "/usr/modify_usr";
	}

	@PostMapping("/modify_usr")
	public String PostModfiyUsr(@ModelAttribute UsrVO usr) {
		service.modify(usr);

		return "redirect:/usr/usrList";
	}

	// 사용자 삭제
	@GetMapping("/remove_usr")
	public String removeUsrByNo(@RequestParam("no") String no) {

		service.remove(no);
		return "redirect:/usr/usrList";
	}

	// 선택된 사용자 정보(+프로젝트 목록) 가져오기
	@GetMapping("/w_enroll_usr")
	public String getWindowEnrollUsrIntoProjectPage(@RequestParam String selectedItems, Model model, Criteria cri) {
		String[] selectedItemList = selectedItems.split(",");
		List<UsrVO> ulist = new ArrayList<>();
		for (int i = 0; i < selectedItemList.length; i++) {
			UsrVO usr = service.getUsrByNo(selectedItemList[i]);
			ulist.add(usr);
		}
		model.addAttribute("ulist", ulist);
		model.addAttribute("project", pservice.getPjtList(cri));
		return "/usr/w_enroll_usr";
	}

	@ResponseBody
	@PostMapping("/w_enroll_usr")
	public ResponseEntity<?> postWindowEnrollUsrIntoProjectPage(@RequestParam("pjtNo") String pjtNo,
			@RequestParam("no[]") List<String> noList,
			@RequestParam("pjtmng_FromDate[]") List<String> pjtmngFromDateList,
			@RequestParam("pjtmng_ToDate[]") List<String> pjtmngToDateList,
			@RequestParam("pjtRole[]") List<String> pjtRoleList, RedirectAttributes redirectAttributes) {

		try {
			for (int i = 0; i < noList.size(); i++) {
				log.info(pjtNo);
				log.info(noList.get(i));
				log.info(pjtmngFromDateList.get(i));

				UsrPjtVO vo = new UsrPjtVO();
				vo.setPjtNo(pjtNo);
				vo.setNo(noList.get(i));
				vo.setPjtmng_ToDate(pjtmngToDateList.get(i));
				vo.setPjtmng_FromDate(pjtmngFromDateList.get(i));
				vo.setPjtRole(pjtRoleList.get(i));
				upservice.enrollUPMembers(vo);
			}

		} catch (DuplicateKeyException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("입력하신 데이터 중 중복되는 항목이 있습니다. 확인 후 다시 시도해주세요.");
		} catch (NumberFormatException ne) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 형식의 숫자 데이터가 있습니다. 확인 후 다시 시도해주세요.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에서 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}

		return ResponseEntity.ok("사용자 등록이 성공적으로 완료되었습니다.");
	}

	@GetMapping("/w_usr_search")
	public String getUsrSearchWindow() {
		return "/usr/w_usr_search";
	}

	@PostMapping("/w_usr_search")
	public String postUsrSearchWindow(Model model, @RequestParam("nm") String nm) {

		model.addAttribute("usr", service.searchNameOnWindow(nm));
		return "/usr/w_usr_search";
	}
	
	// 사번 중복 체크
	@PostMapping("/noCheck")
	@ResponseBody
	public int noCheck(@RequestParam("no") String no) {
		
		int cnt = service.noCheck(no);
		return cnt;
		
	}
	
}

