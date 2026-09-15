package org.doit.ik.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("DeptEmpSalgradeDTOAlias")
@Getter

public class DeptEmpSalgradeDTO {
	
	//1:1 연관관계 
	//DeptDTO
	private DeptDTO deptDTO;
	
	private EmpDTO empDTO;
	//1:N 연관 관계
	//EmpDTO
		//ㄴSalgrade
	//private int grade;
	//private List<EmpDTO> empList;
	
	
}
