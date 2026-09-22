package org.doit.ik.mapper;

import java.sql.SQLException;

import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.MemberVO;
 
public interface MemberMapper {
	 
	public MemberVO getMember(@Param("id") String  id) throws ClassNotFoundException, SQLException;
 
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	 
}
