package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.UsrPjtDTO;
import org.zerock.domain.VO.UsrPjtVO;
import org.zerock.mapper.UsrPjtMapper;

import lombok.AllArgsConstructor;

/*import lombok.extern.log4j.Log4j;

@Log4j*/
@Service
@AllArgsConstructor
public class UsrPjtServiceImpl implements UsrPjtService {
	
	@Autowired
	UsrPjtMapper mapper;
	
	@Override
	public List<UsrPjtDTO> getUsrPjtList() {
		return mapper.getUsrPjtList();
	}

	@Override
	public List<UsrPjtDTO> getUsrPjtListByTypes(String nm, String from, String to, String pjtRole) {
		System.out.println(mapper.getUsrPjtListByTypes(nm, from, to, pjtRole));
		return mapper.getUsrPjtListByTypes(nm, from, to, pjtRole);
	}

	@Override
	public void enrollUPMembers(UsrPjtVO vo) {
		mapper.enrollUPMembers(vo);
	}

	@Override
	public List<UsrPjtDTO> getUsrPjtListByPjtNo(String pjtNo) {
		return mapper.getUsrPjtListByPjtNo(pjtNo);
	}

}
