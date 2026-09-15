package org.doit.ik.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;

public interface DeptMapper {
	
	//부서 조회
	   ArrayList<DeptDTO> selectDept();
	   
	   // 부서추가 추상 메서드
	   int insertDept(DeptDTO dto);
	   
	   // 부서 삭제 추상 메서드
	   int deleteDept( @Param("deptno") int deptno); // Integer.deptno
	
}
