package org.doit.ik.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor

public class Criteria {
	private int pageNum; //현재 페이지 번호
	private int amount;	//현 페이지에 출력할 게시글 수 
	
	
	private String type;	//검색조건  "tcw" -> t /c /w
	private String keyword;	//검색어
	
	public Criteria() {
		
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {} : type.split("");
		
	}
}
