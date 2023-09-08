package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* 페이징 처리, 검색 필터링 위한 정보 저장*/
@ToString
@Getter
@Setter
public class Criteria {
	
	private int pageNum; // 현재 페이지 번호
	private int amount; // 한 페이지에 표시될 데이터 개수
	
	private String type; // 검색 유형
	private String keyword; // 검색어
	
	// 페이지 번호 1, 개수 10
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}:type.split("");
	}

}
