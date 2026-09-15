package org.doit.ik.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.doit.ik.domain.DeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*@Repository*/

public class DeptMapperDao2 implements DeptMapper{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<DeptDTO> selectDept() {
		List<DeptDTO> list = this.sqlSession.selectList("org.doit.ik.mapper.DeptMapper.selectDept");
		return new ArrayList<>(list);
	}

	@Override
	public int insertDept(DeptDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDept(int deptno) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
