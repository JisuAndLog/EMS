package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.VO.UsrVO;
import org.zerock.mapper.UsrMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UsrServiceImpl implements UsrService {
	
	private UsrMapper mapper;

	@Override
	public void modify(UsrVO usr) {
		mapper.modifyUsr(usr);
		
	}

	@Override
	public void remove(String no) {
		mapper.removeUsrByNo(no);
		
	}

	@Override
	public List<UsrVO> getList(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}

	@Override
	public UsrVO getUsrByNo(String no) {
		return mapper.getUsrByNo(no);
	}

	@Override
	public List<UsrVO> searchWithTypes(String nm, String jobSkill, String inoffi_sts, int pageNum, int amount, String from,
			String to) {
		log.info("서비스 null처리 정상작동");
		return mapper.searchWithTypes(nm, jobSkill, inoffi_sts, pageNum, amount, from, to);
	}
	
	@Override
	public List<UsrVO> getUsrNoNmIsNull() {	
		return mapper.getUsrNoNmIsNull();
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<UsrVO> searchNameOnWindow(String nm) {
		return mapper.searchNameOnWindow(nm);
	}

	@Override
	public int countSearchWithTypes(String nm, String jobSkill, String inoffi_sts, int pageNum, int amount, String from,
			String to) {
		return mapper.countSearchWithTypes(nm, jobSkill, inoffi_sts, pageNum, amount, from, to);
	}

	@Override
	public void enroll(UsrVO usr) {
		mapper.enrollUsr(usr);
		
	}

	@Override
	public int noCheck(String no) {		
		int cnt = mapper.noCheck(no);
		System.out.println("cnt: " + cnt);
		return cnt;
	}

}
