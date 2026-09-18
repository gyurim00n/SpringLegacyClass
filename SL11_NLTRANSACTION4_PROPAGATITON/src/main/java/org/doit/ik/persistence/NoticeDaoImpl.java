package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doit.ik.domain.NoticeVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeDaoImpl implements NoticeDao{
   
   private final NamedParameterJdbcTemplate npJdbcTemplate;
   // private final DataSourceTransactionManager transactionManager;
   // private final TransactionTemplate transactionTemplate;
   
   // 1. 공지사항의 갯수를 반환하는 메서드
   public int getCount(String field, String query) throws ClassNotFoundException, SQLException
   { 
      // p491 queryForObject() 설명
      String sql = " SELECT COUNT(*) CNT "
                   + " FROM NOTICES "
                   + " WHERE " + field + " LIKE : query";       
      
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("query", query);
      
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
      paramMap.put("query", "%"+query+"%");
      paramMap.put("srow", srow);
      paramMap.put("erow", erow);
      
      return this.npJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
      
   }
   
   // 글번호(seq)에 해당하는 공지사항을 삭제하는 메서드
   public int delete(String seq) throws ClassNotFoundException, SQLException
   {      
      String sql = "DELETE FROM notices "
                  + " WHERE seq=:seq";    
      
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("seq", seq);
      
      return this.npJdbcTemplate.update(sql, paramSource);
   }
   
   // 3. 공지사항 수정하는 메서드 
   public int update(NoticeVO noticeVO) throws ClassNotFoundException, SQLException{
      
      String sql = "UPDATE notices "
                   + " SET title=:title, content=:content, filesrc=:filesrc "
                   + " WHERE seq=:seq";
      
      SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
      
      return this.npJdbcTemplate.update(sql, paramSource);
      
      /*
       MapSqlParameterSource paramSource = new MapSqlParameterSource();
       paramSource.addValue("title", noticeVO.getTitle());
       paramSource.addValue("content", noticeVO.getContent());
       paramSource.addValue("filesrc", noticeVO.getFilesrc());
       paramSource.addValue("seq", noticeVO.getSeq());
       
       return this.npJdbcTemplate.update(sql, paramSource); 
      */
   }
   
   // 공지사항 상세 보기
   public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
   {
      String sql = "SELECT * "
              + " FROM NOTICES "
              + " WHERE SEQ=:seq";
      
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("seq", seq);
       
      return this.npJdbcTemplate.queryForObject(sql, paramSource, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
   }
   
   // [2] 공지사항 추가하는 메서드 = (1) 공지사항 등록 + (2) 포인트 증가
   @Transactional(propagation = Propagation.REQUIRED)
   public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException {
       // 1. 공지사항 쓰기
         String sql1 = "INSERT INTO NOTICES"
               + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
               + " VALUES( "
               + "    (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer , SYSDATE, 0, :filesrc"
               + ")";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
         npJdbcTemplate.update(sql1 ,  paramSource );
         
         // 2. 작성자의 포인트 1증가
         String sql2 = "UPDATE member "
               + " SET point = point + 1 "
               + " WHERE id = :id ";

         MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
         paramSource2.addValue("id", "scott");
         return npJdbcTemplate.update(sql2 , paramSource2);
   }
   
   /*
   // [1] 공지사항 추가하는 메서드
   public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException {
      String sql = "INSERT INTO NOTICES"
            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
            + " VALUES( "
            + "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer , SYSDATE, 0, :filesrc)";

      SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
      
      return this.npJdbcTemplate.update(sql, paramSource);
      
   }
      */
   /*
   // [5] 선언적 트랜잭션 방법으로 처리한 메서드
   // @Transactional(propagation = Propagation.REQUIRED)
   @Override
   public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {

      insert(noticeVO); // @T    TEST 2 - 2
      
      noticeVO.setTitle(noticeVO.getTitle() + " - two");
      insert(noticeVO); // @T   
      
   }  
   /*
   /*
   // [4] 선언적 트랜잭션 방법으로 처리한 메서드
      @Override
      @Transactional(propagation = Propagation.REQUIRED)
      public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {
         // 1. 공지사항 쓰기
         String sql1 = "INSERT INTO NOTICES"
               + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
               + " VALUES( "
               + "    (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer , SYSDATE, 0, :filesrc"
               + ")";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
         npJdbcTemplate.update(sql1 ,  paramSource );
         
         // 2. 작성자의 포인트 1증가
         String sql2 = "UPDATE member "
               + " SET point = point + 1 "
               + " WHERE id = :id ";

         MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
         paramSource2.addValue("id", id);
         npJdbcTemplate.update(sql2 , paramSource2);
         
      }  
   /*
   // [3] 주입받은 transactionTemplate을 사용해서 트랜잭션 처리한 메서드
      @Override
      public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {
         // 1. 공지사항 쓰기
         String sql1 = "INSERT INTO NOTICES"
               + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
               + " VALUES( "
               + "    (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer , SYSDATE, 0, :filesrc"
               + ")";
         
         // 2. 작성자의 포인트 1증가
         String sql2 = "UPDATE member "
               + " SET point = point + 1 "
               + " WHERE id = :id ";
         
         // 3. 트랜잭션 처리 p515
         this.transactionTemplate.execute( new TransactionCallbackWithoutResult() {
         
         @Override
         protected void doInTransactionWithoutResult(TransactionStatus status) {
              // 1.
              SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
               npJdbcTemplate.update(sql1 ,  paramSource );
               // 2.
               MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
               paramSource2.addValue("id", id);
               npJdbcTemplate.update(sql2 , paramSource2);
         }
      
      });
         
         
      }  
   /*
   // [2] 주입받은 transactionManager을 사용해서 트랜잭션 처리한 메서드
      @Override
      public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {
         // 1. 공지사항 쓰기
         String sql1 = "INSERT INTO NOTICES"
               + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
               + " VALUES( "
               + "    (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer , SYSDATE, 0, :filesrc"
               + ")";
         
         // 2. 작성자의 포인트 1증가
         String sql2 = "UPDATE member "
               + " SET point = point + 1 "
               + " WHERE id = :id ";
         
         // 3. 트랜잭션 처리
         
         // 트랜잭션을 어떤 방식으로 실행할 것인지에 대한 설정 정보를 담는 변수
         //               ㄴ ( 전파방식, 격리 레벨 )
         TransactionDefinition definition = new DefaultTransactionDefinition();
         
         // Spring에서 트랜잭션의 현재 상태를 저장하는 변수
         TransactionStatus status = this.transactionManager.getTransaction(definition);
         
         try {
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
            this.npJdbcTemplate.update(sql1 ,  paramSource );
             
            MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
            paramSource2.addValue("id", id);
            this.npJdbcTemplate.update(sql2 , paramSource2);

            // 커밋            
           this.transactionManager.commit(status);
             } catch (Exception e) {
           // 롤백
            this.transactionManager.rollback(status);
            } // t - c
         
      }  

   // [1] 트랜잭션 처리가 되지 않은 메서드
      @Override
      public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {
         // 1. 공지사항 쓰기
         String sql = "INSERT INTO NOTICES"
               + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
               + " VALUES( "
               + "    (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer , SYSDATE, 0, :filesrc"
               + ")";
         
         SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
           
          this.npJdbcTemplate.update(sql ,  paramSource );
          
         // 2. 작성자의 포인트 1증가
         sql = "UPDATE member "
               + " SET point = point + 1 "
               + " WHERE id = :id ";
         MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
         paramSource2.addValue("id", id);
         this.npJdbcTemplate.update(sql, paramSource2);
      }
      */   
   
}








