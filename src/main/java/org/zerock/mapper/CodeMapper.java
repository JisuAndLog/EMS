package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.domain.VO.CodeDTLVO;
import org.zerock.domain.VO.CodeVO;

public interface CodeMapper {
	
	@Select("select dtlcd, dtlcdnm "
			+ "from codedtl "
			+ "where dcode = #{codeVal}")
    public List<CodeDTLVO> getCodeVal(@Param("codeVal") String codeVal); 
	
	/*
	 * // 프로젝트 진행 상황이라 사용하지 않을 듯
	 * 
	 * @Select("SELECT DTLCODE,DTLCODENM FROM DCODEDTL WHERE DCODE = 'P001'") public
	 * List<CodeDTLVO> getProjectStatusDcode();
	 */
	
	// 코드마스터 목록에서 사용됨
	@Select("select * from codedtl order by dcode, dtlcd")
	public List<CodeDTLVO> getCodeList();
	
	@Select("select * from code")
	public List<CodeVO> getCode();
	
	@Insert ("insert into codedtl (dcode, dtlcd, dtlcdnm) values (#{dcode}, #{dtlcd}, #{dtlcdnm})")
	public void insertDCode(CodeDTLVO vo);

}
