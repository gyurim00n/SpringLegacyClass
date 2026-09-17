package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.domain.NoticeVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class MemberDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	//1.id에 해당하는 멤버 객체를 반환하는메서드
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				+ "FROM MEMBER "
				+ "WHERE id=?";
		
		return this.jdbcTemplate
				.queryForObject(
				sql
				, new Object[] {id}
				, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
	}
	
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException
	{
		String sql = "INSERT INTO MEMBER "
	            + "( id, pwd, name, gender, birth, is_lunar, cphone, email, habit, regdate) "
	            + " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		return this.jdbcTemplate.update(sql, member.getId()
				, member.getPwd()
				, member.getName()
				, member.getGender()
				, member.getBirth()
				, member.getIs_lunar()
				, member.getCphone()
				, member.getEmail()
				, member.getHabit()
				);
	}
}
