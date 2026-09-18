package org.doit.ik.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO {
	//member 테이블의 컬럼명--> 필드명
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar; //2.수정
	private String cphone;	//3.수정
	private String email;
	private String habit;
	private Date   regdate;	//4.수정
	
	private int point;//추가
}
