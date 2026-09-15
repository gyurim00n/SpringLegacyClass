package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.DeptEmpSalgradeDTO;
import org.doit.ik.domain.EmpDTO;

public interface DeptEmpSalgradeMapper {
	//List<DeptEmpSalgradeDTO> getDeptEmpInfo();
	
	//1.모든 부서 정조 보회
	List<DeptEmpSalgradeDTO> getDept();
	//2. 각 해당 부서원 정보를 조회
	List<EmpDTO> getEmpOfDept(int deptno);
	
}
