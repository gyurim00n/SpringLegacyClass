package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class NoticeDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	//1.공지사항의 갯수 반환.
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		//p491 queryForObject() 설명
		String sql = "SELECT COUNT(*) CNT "
				+ "FROM NOTICES WHERE "+field+" LIKE ?"; //왜 문자열연결? ->JRE11사용중이라서.
		//int cnt =this.jdbcTemplate.queryForObject(sql, Integer.class, "%" + query + "%");
		return this.jdbcTemplate.queryForObject(sql, Integer.class, "%" + query + "%");
	}
	
	//2.공지사항의 목록을 List로 반환하는 메서드...
	public List<NoticeVO> getNotices(
			int page, 		//현재페이지
			String field, 	//검색조건
			String query	//검색어
			) throws ClassNotFoundException, SQLException
		{					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = " SELECT * "
	               + "  FROM ( "
	               + "                 SELECT ROWNUM NUM, N.* "
	               + "                 FROM ("
	               + "                          SELECT * "
	               + "                          FROM NOTICES "
	               + "                          WHERE "+field+" LIKE ? "
	                     + "                   ORDER BY REGDATE DESC"
	                     + "                ) N"
	                     + "  ) "
	                +  " WHERE NUM BETWEEN ? AND ? ";
	return this.jdbcTemplate.query(
			sql
			, new Object[] {"%" + query + "%", srow, erow}
			, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class)
			);
		
		
	}
	
	//글번호(seq)에 해당하는 공지사항을 삭제하는 메서ㅡㄷ
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		
		String sql = "DELETE FROM notices "
				+ "WHERE seq=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"scott", "tiger");
		
		return this.jdbcTemplate.update(sql, seq);
	}
	//공지사항 수정하는 메서드
	public int update(NoticeVO noticeVO) throws ClassNotFoundException, SQLException{
		
		
		String sql = "UPDATE notices "
				+ "SET title=?, content=?, filesrc=? WHERE seq=?";
		
	
		return this.jdbcTemplate.update(sql, noticeVO.getTitle()
				, noticeVO.getContent()
				, noticeVO.getFilesrc()
				, noticeVO.getSeq());
	}
	
	
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM NOTICES WHERE seq=?";

		return this.jdbcTemplate
				.queryForObject(sql
								, new Object[] {seq}
								, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}

	public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + "    (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), ?, ?, 'moon', SYSDATE, 0, ?"
	            + ")";
		
		return this.jdbcTemplate.update(sql, noticeVO.getTitle()
				, noticeVO.getContent()
				, noticeVO.getFilesrc()
				);
	}
}
