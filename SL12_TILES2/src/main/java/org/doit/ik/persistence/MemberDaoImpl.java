package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao{
   
   private final NamedParameterJdbcTemplate npJdbcTemplate;
   
   // 1. id에 해당하는 멤버 객체를 반환하는 메서드 
   public MemberVO getMember(String id) throws ClassNotFoundException, SQLException
   {
      String sql = "SELECT * "
                  + " FROM MEMBER "
                  + " WHERE id = :id";
      
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("id", id);
      
      return this.npJdbcTemplate.queryForObject(sql, paramSource, MemberVO.class);
   }
   
   // 2. 회원 가입 메서드
   public int insert(MemberVO memberVO) throws ClassNotFoundException, SQLException
   {
      String sql = "INSERT INTO MEMBER "
               + "( id, pwd, name, gender, birth, is_lunar, cphone, email, habit, regdate)"
               + " VALUES( :id, :pwd, :name, :gender, :birth, :is_lunar, :cphone, :email, :habit, SYSDATE)";

         SqlParameterSource paramSource= new BeanPropertySqlParameterSource(memberVO);

         return this.npJdbcTemplate.update(sql,  paramSource);
   }
}
