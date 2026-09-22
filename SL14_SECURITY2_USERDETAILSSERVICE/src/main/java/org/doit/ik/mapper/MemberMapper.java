package org.doit.ik.mapper;

import java.sql.SQLException;

import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.MemberVO;
 
public interface MemberMapper {
	 
	public MemberVO getMember(@Param("id") String  id) throws ClassNotFoundException, SQLException;
 
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
	//회원id(username)을 매개변수로 회원정보를 반환하는 메서드..
	public MemberVO read(@Param("userid")String user) throws ClassNotFoundException, SQLException;
	 
}
