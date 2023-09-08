package org.zerock.domain.VO;

import lombok.Data;

@Data
public class PjtVO {
	private String pjtNo;
    private String pjtNm;
    private String pjtFromDate;
    private String pjtToDate;
    private String cmpyNm;
    private String pjtSkill;
    private char delete_col;

}
