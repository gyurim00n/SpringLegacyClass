package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.doit.ik.domain.scott.DeptDTO;
import org.doit.ik.domain.scott.EmpDTO;

public interface EmpDeptMapper {
	//부서조회
	ArrayList<DeptDTO> selectDept();
	
	//사원조회
	ArrayList<EmpDTO> selectEmp();

	//부서별 사원조회
	ArrayList<EmpDTO> selectEmp(int[] deptnoArr);
}
