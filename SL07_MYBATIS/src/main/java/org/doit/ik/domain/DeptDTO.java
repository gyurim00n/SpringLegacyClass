package org.doit.ik.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Alias("DeptDTOAlias")
public class DeptDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	private int numberOfEmps; //해당 부서의 사원수 
}
