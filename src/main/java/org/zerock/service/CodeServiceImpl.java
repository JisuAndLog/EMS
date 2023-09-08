package org.zerock.service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.VO.CodeDTLVO;
import org.zerock.domain.VO.CodeVO;
import org.zerock.mapper.CodeMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class CodeServiceImpl implements CodeService {
	
	private CodeMapper mapper;
	
	@Override
	public List<CodeDTLVO> getCodeVal(String codeVal) {
		log.info("service >>> " + codeVal);
		return mapper.getCodeVal(codeVal);
	}
	
	/*
	@Override
	public List<CodeDTLVO> getCmpyNmCode() {
		return getCmpyNmCode();		
	}*/

	@Override
	public List<CodeDTLVO> getCodeList() {
		return mapper.getCodeList();
	}

	@Override
	public List<CodeVO> getCode() {
		return mapper.getCode();
	}

	@Override
	public void insertDCode(CodeDTLVO vo) {
		mapper.insertDCode(vo);

	}

}
