package org.zerock.service;

import java.util.List;

import org.zerock.domain.VO.CodeDTLVO;
import org.zerock.domain.VO.CodeVO;

public interface CodeService {
	
	public List<CodeDTLVO> getCodeVal(String codeVal);

	//public List<CodeDTLVO> getCmpyNmCode();
	
	public List<CodeDTLVO> getCodeList();

	public List<CodeVO> getCode();
	
	public void insertDCode(CodeDTLVO vo);

}
