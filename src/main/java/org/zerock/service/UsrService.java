package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.VO.UsrVO;

public interface UsrService {
	
	public void enroll(UsrVO usr);
	
	public void modify(UsrVO usr);
	
	public void remove(String no);
	
	public List<UsrVO> getList(Criteria cri);

	public UsrVO getUsrByNo(String no);
	
	public List<UsrVO> searchWithTypes(String nm, String jobSkill, String inoffi_sts, int pageNum, int amount, String from, String to);
	
	// 사번 중복 체크
	public int noCheck(String no);
	
	
	  // 프로젝트 배치 화면에서 사용됨 
	  public List<UsrVO> getUsrNoNmIsNull();
	  
	  public int getTotal(Criteria cri);
	  
	  public List<UsrVO> searchNameOnWindow(String nm);
	  
	  int countSearchWithTypes(String nm, String jobSkill, String inoffi_sts, int pageNum, int amount, String from, String to);

	 
}
