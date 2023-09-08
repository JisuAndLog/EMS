package org.zerock.service;

import java.util.List;

import org.zerock.domain.UsrPjtDTO;
import org.zerock.domain.VO.UsrPjtVO;


public interface UsrPjtService {
	public List<UsrPjtDTO> getUsrPjtList();
	public List<UsrPjtDTO> getUsrPjtListByTypes(String nm, String from, String to, String pjtRole);
	public void enrollUPMembers(UsrPjtVO vo);
	public List<UsrPjtDTO> getUsrPjtListByPjtNo(String pjtNo);

}
