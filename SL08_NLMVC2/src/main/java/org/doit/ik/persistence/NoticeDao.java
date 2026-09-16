package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.stereotype.Repository;


@Repository
public class NoticeDao {
	//1.공지사항의 갯수 반환.
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT "
				+ "FROM NOTICES WHERE "+field+" LIKE ?"; //왜 문자열연결? ->JRE11사용중이라서.
		
		//Text Block(""") 문법 11
		//얘는 15이상부터.
//		String sql = """
//		        SELECT COUNT(*) CNT
//		        FROM NOTICES
//		        WHERE %s LIKE ?
//		        """.formatted(field);
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"scott", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		
		
		ResultSet rs = st.executeQuery();
		rs.next();
		
		int cnt = rs.getInt("cnt");
		
		rs.close();
		st.close();
		con.close();
		
		return cnt;
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
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"scott", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, srow);
		st.setInt(3, erow);
		
		ResultSet rs = st.executeQuery();
		
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		
		while(rs.next()){
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setSeq(rs.getString("seq"));
			noticeVO.setTitle(rs.getString("title"));
			noticeVO.setWriter(rs.getString("writer"));
			noticeVO.setRegdate(rs.getDate("regdate"));
			noticeVO.setHit(rs.getInt("hit"));
			noticeVO.setContent(rs.getString("content"));
			noticeVO.setFilesrc(rs.getString("filesrc"));
			
			list.add(noticeVO);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	
	//글번호(seq)에 해당하는 공지사항을 삭제하는 메서ㅡㄷ
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		
		String sql = "DELETE FROM notices "
				+ "WHERE seq=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"scott", "tiger");
		// 2. ����
		PreparedStatement st = con.prepareStatement(sql);	
		st.setString(1, seq);
		
		int rowCount = st.executeUpdate();
		
		return rowCount;
	}
	//공지사항 수정하는 메서드
	public int update(NoticeVO noticeVO) throws ClassNotFoundException, SQLException{
		
		
		String sql = "UPDATE notices "
				+ "SET title=?, content=?, filesrc=? WHERE seq=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"scott", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, noticeVO.getTitle());
		st.setString(2, noticeVO.getContent());
		st.setString(3, noticeVO.getFilesrc());
		st.setString(4, noticeVO.getSeq());		
		
		int rowCount = st.executeUpdate();
		
		return rowCount;
	}
	
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM NOTICES WHERE seq="+seq;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"scott", "tiger");
		
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setSeq(rs.getString("seq"));
		noticeVO.setTitle(rs.getString("title"));
		noticeVO.setWriter(rs.getString("writer"));
		noticeVO.setRegdate(rs.getDate("regdate"));
		noticeVO.setHit(rs.getInt("hit"));
		noticeVO.setContent(rs.getString("content"));
		noticeVO.setFilesrc(rs.getString("filesrc"));
		
		rs.close();
		st.close();
		con.close();
		
		return noticeVO;
	}

	public int insert(NoticeVO n) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + "    (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), ?, ?, 'moon', SYSDATE, 0, ?"
	            + ")";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"scott", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, n.getTitle());
		st.setString(2, n.getContent());
		st.setString(3, n.getFilesrc());
		
		int rowCount = st.executeUpdate();
		
		st.close();
		con.close();
		
		return rowCount;
	}
}
