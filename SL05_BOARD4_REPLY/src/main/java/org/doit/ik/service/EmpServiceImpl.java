package org.doit.ik.service;

import org.doit.ik.mapper.EmpMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService{
	private final EmpMapper empMapper;

	//null 이라면 enpno는 사용가능하기에 true를 반환한다..
	@Override
	public boolean isEmpnoAvailable(int empno) {
		// TODO Auto-generated method stub
		return this.empMapper.checkEmpno(empno) == null;
	}
	
	
}
