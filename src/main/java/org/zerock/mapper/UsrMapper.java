package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.domain.Criteria;
import org.zerock.domain.VO.UsrVO;

public interface UsrMapper {

	@Select("select entrDate, no, nm, birth, jobSkill, inoffi_sts from usr order by entrDate desc")
	public List<UsrVO> getList();
		
	// 페이징 처리
	public List<UsrVO> getListWithPaging(Criteria cri);
		
	public List<UsrVO> searchWithTypes(
			@Param("nm") String nm,
	        @Param("jobSkill") String jobSkill,
	        @Param("inoffi_sts") String inoffi_sts,
	        @Param("pageNum") int pageNum,
	        @Param("amount") int amount,
	        @Param("from") String from,
	        @Param("to") String to);
	
	public void enrollUsr(UsrVO usr);
	
	public UsrVO getUsrByNo(String no);
	
	@Select("select count(*) from usr where delete_col = 1") 
	public int totalCount();
	
	public void modifyUsr(UsrVO usr);

	@Update("update usr set delete_col = 2 where no = #{no}")
	public void removeUsrByNo(String no);
	
	// project 배치 화면에서 사용
	@Select("select distint u.no, "
			+ "u.nm"
			+ "from usr u"
			+ "left outer join usrPjt up on (u.no = up.no)")
	public List<UsrVO> getUsrNoNmIsNull();

	public int getTotalCount(Criteria cri);
	
	@Select("select nm, "
			+ "cd3.dtlcdnm as jobSkill, "
			+ "cd5.dtlcdnm as inoffi_sts"
			+ "from usr"
			+ "left outer join codedtl cd3 on (cd3.dcode='D300' and usr.jobSkill = cd3.dtlcd)"
			+ "left outer join codedtl cd5 on (cd5.dcode='D500' and usr.inoffi_sts = cd5.dtlcd)"
			+ "where nm like concat('%', #{nm}, '%')")
	public List<UsrVO> searchNameOnWindow(String nm);

	public int countSearchWithTypes(
			@Param("nm") String nm,
			@Param("jobSkill") String jobSkill,
            @Param("inoffi_sts") String inoffi_sts,
			@Param("pageNum") int pageNum,
			@Param("amount") int amount,
			@Param("from") String from,
			@Param("to") String to);

	public int noCheck(String no);	
	
}
