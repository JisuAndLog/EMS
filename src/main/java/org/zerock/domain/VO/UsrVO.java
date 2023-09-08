package org.zerock.domain.VO;

import lombok.Data;


@Data
public class UsrVO {
	
	private String entrDate;	
	private String no; // pk
	
	private String nm;
	private String birth;
	private String sex;
	private String jobRank;
	
	private String jobSkill;
	
	private String inoffi_sts;
	
	private String pswd;
	
	private String devPt;
	private String mblNo;
	private String emailAddr;
	private char delete_col;
	public String getInoffi_sts() {
		return inoffi_sts;
	}
	
	
/*
// Default constructor 
public UsrVO() {
    }
	
// 생성자 추가
public UsrVO(String entrDate, String no, String nm, String birth, String sex, String jobRank, String jobSkill, String inoffi_sts) {
    this.entrDate = entrDate;
    this.no = no;
    this.nm = nm;
    this.birth = birth;
    this.sex = sex;
    this.jobRank = jobRank;
    this.jobSkill = jobSkill;
    this.inoffi_sts = inoffi_sts;
	}*/
}

