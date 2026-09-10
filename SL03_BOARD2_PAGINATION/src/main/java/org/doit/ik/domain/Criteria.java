package org.doit.ik.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class Criteria {
	private int pageNum; //현재 페이지 번호
	private int amount;	//현 페이지에 출력할 게시글 수 
	
	public Criteria() {
		
		this(1,3);
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
