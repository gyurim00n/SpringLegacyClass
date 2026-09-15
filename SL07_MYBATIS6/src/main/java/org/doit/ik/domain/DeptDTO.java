package org.doit.ik.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class DeptDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	
	//1-N 연관 관계
	private List<EmpDTO> empList;
}
