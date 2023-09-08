package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.VO.PjtVO;

public interface PjtService {
	
	public List<PjtVO> getPjtList(Criteria cri);

	public void modifyPjt(String pjtNo);

	public void deletePjt(String pjtNo);

	public void enrollPjt(PjtVO pjt);

	public List<String> getPjtNames(String pjtNo);

	List<PjtVO> searchWithTypes(String from, String to, String cmpyNm, String pjtNm, int pageNum, int amount);

	public int getTotalCount(String from, String to, String cmpyNm, String pjtNm, int pageNum, int amount);

}
