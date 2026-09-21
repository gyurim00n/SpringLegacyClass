package org.doit.ik.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
	
	// member 테이블의 컬럼명 == 필드명 동일...
	private String id;          // 1. uid -> id 수정
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	
	/* <input type="date" name="birth" id="birth">
	 * 
	 * @DateTimeFormat(pattern = "yyyy-MM-dd")
	 * private Date birth;
	 *  */
	
	private String is_lunar;  // 2. 수정
	private String cphone;   // 3. 수정
	private String email;
	private String habit;
	private Date   regdate;  // 4. 수정
	
	private int point;   // 추가
	 
}
