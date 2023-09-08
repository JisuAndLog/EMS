package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.VO.PjtVO;

public interface PjtMapper {

	public List<PjtVO> getPjtList(Criteria cri);

	public void modifyPjt(String pjtNo);

	// @Update("Update pjt SET delete_col = 2 where prjNo=#{prjNo}")
	public void deletePjt(String pjtNo);

	public void enrollPjt(PjtVO pjt);

	public List<String> getPjtNames(String pjtNo);
	
	/*
	 * @Select("select count(*) from PROJECT where delete_col =1") 
	 * public int totalCount();
	 */

	public List<PjtVO> searchWithTypes(
			@Param("from") String from,
            @Param("to") String to,
            @Param("cmpyNm") String cmpyNm,
            @Param("pjtNm") String pjtNm, 
            @Param("pageNum") int pageNum,
            @Param("amount") int amount);

	public int getTotalCount(
			@Param("from") String from,
            @Param("to") String to,
            @Param("cmpyNm") String cmpyNm,
            @Param("pjtNm") String pjtNm,
            @Param("pageNum") int pageNum,
            @Param("amount") int amount);

}
