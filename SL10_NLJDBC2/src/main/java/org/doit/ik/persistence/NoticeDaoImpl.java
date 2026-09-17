package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doit.ik.domain.NoticeVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeDaoImpl implements NoticeDao{
   
   private final NamedParameterJdbcTemplate npJdbcTemplate;
   
   // 1. 공지사항의 갯수를 반환하는 메서드
   public int getCount(String field, String query) throws ClassNotFoundException, SQLException
   { 
      // p491 queryForObject() 설명
      String sql = " SELECT COUNT(*) CNT "
                   + " FROM NOTICES "
                   + " WHERE " + field + " LIKE :q";
      
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("q", query);
      
      return this.npJdbcTemplate.queryForObject(sql, paramSource, Integer.class); 
   }
   
   // 2. 공지사항의 목록을 List 로 반환하는 메서드
   public List<NoticeVO> getNotices(
         int page           // 현재 페이지 번호
         , String field      // 검색조건
         , String query    // 검색어
         ) throws ClassNotFoundException, SQLException
   {               
      
      int srow = 1 + (page-1)*15;
      int erow = 15 + (page-1)*15;
      
      String sql = " SELECT * "
               + "  FROM ( "
               + "                 SELECT ROWNUM NUM, N.* "
               + "                 FROM ("
               + "                          SELECT * "
               + "                          FROM NOTICES "
               + "                          WHERE "+field+" LIKE :query "
                     + "                   ORDER BY REGDATE DESC"
                     + "                ) N"
                     + "  ) "
               +  " WHERE NUM BETWEEN :srow AND :erow ";
      Map<String, Object> paramMap = new HashMap<>();
      paramMap.put("query", '%' + query + '%');
      paramMap.put("erow", erow);
      paramMap.put("srow", srow);
      
     
      //System.out.println(this.npJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class)));
      return this.npJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
      
   }
   
   // 글번호(seq)에 해당하는 공지사항을 삭제하는 메서드
   public int delete(String seq) throws ClassNotFoundException, SQLException
   {      
      String sql = "DELETE FROM notices "
                  + " WHERE seq= :seq";    
      
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("seq", seq);
      
      return this.npJdbcTemplate.update(sql, paramSource); 
   }
   
   // 3. 공지사항 수정하는 메서드 
   public int update(NoticeVO noticeVO) throws ClassNotFoundException, SQLException{
      
      String sql = "UPDATE notices "
                   + " SET title= :title , content= :content , filesrc= :filesrc "
                   + " WHERE seq= :seq";
      
      SqlParameterSource paramSource =  new BeanPropertySqlParameterSource(noticeVO);
      return this.npJdbcTemplate.update(sql, paramSource);
//       MapSqlParameterSource paramSource = new MapSqlParameterSource();
//       paramSource.addValue("title", noticeVO.getTitle());
//       paramSource.addValue("content", noticeVO.getContent());
//       paramSource.addValue("filesrc", noticeVO.getFilesrc());
//       paramSource.addValue("seq", noticeVO.getSeq());
//      return this.npJdbcTemplate.update(sql, paramSource);
   }
   
   // 공지사항 상세 보기
   public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
   {
      String sql = "SELECT * "
              + " FROM NOTICES "
              + " WHERE SEQ= :seq";
      
      
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("seq", seq);
      
      return this.npJdbcTemplate.queryForObject(sql, paramSource, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class)); 
     
   }

   // 공지사항 추가하는 메서드
   public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException {
      String sql = "INSERT INTO NOTICES"
            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
            + " VALUES( "
            + "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'scott', SYSDATE, 0, :filesrc)";

      SqlParameterSource paramSource =  new BeanPropertySqlParameterSource(noticeVO);
      return this.npJdbcTemplate.update(sql, paramSource);
      
   }
}
