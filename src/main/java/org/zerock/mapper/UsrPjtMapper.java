package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.UsrPjtDTO;
import org.zerock.domain.VO.UsrPjtVO;

public interface UsrPjtMapper {
	
public List<UsrPjtDTO> getUsrPjtList();
	
	public List<UsrPjtDTO> getUsrPjtListByTypes(
	        @Param("nm") String nm,
	        @Param("from") String from,
	        @Param("to") String to,
	        @Param("pjtRole") String pjtRole
	);
	
	public void enrollUPMembers(UsrPjtVO vo);

	public List<UsrPjtDTO> getUsrPjtListByPjtNo(String pjtNo);

}
