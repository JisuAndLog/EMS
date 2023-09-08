package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.VO.PjtVO;
import org.zerock.mapper.PjtMapper;

import lombok.AllArgsConstructor;

/*import lombok.extern.log4j.Log4j;

@Log4j*/
@Service
@AllArgsConstructor
public class PjtServiceImpl implements PjtService{
	
	private PjtMapper mapper;

	@Override
	public List<PjtVO> getPjtList(Criteria cri) {
		return mapper.getPjtList(cri);
	}


	@Override
	public void modifyPjt(String pjtNo) {
		mapper.modifyPjt(pjtNo);
		
	}

	@Override
	public void deletePjt(String pjtNo) {
		mapper.deletePjt(pjtNo);
		
	}

	@Override
	public void enrollPjt(PjtVO pjt) {
		mapper.enrollPjt(pjt);
		
	}

	@Override
	public List<String> getPjtNames(String pjtNo) {
		return mapper.getPjtNames(pjtNo);
	}

	@Override
	public List<PjtVO> searchWithTypes(String from, String to, String cmpyNm, String pjtNm, int pageNum, int amount) {
		return mapper.searchWithTypes(from, to, cmpyNm, pjtNm, pageNum, amount);
	}

	@Override
	public int getTotalCount(String from, String to, String cmpyNm, String pjtNm, int pageNum, int amount) {
		return mapper.getTotalCount(from, to, cmpyNm, pjtNm, pageNum, amount);
	}

}
