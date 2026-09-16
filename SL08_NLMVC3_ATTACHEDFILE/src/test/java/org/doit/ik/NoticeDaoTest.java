package org.doit.ik;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.junit.Test;

public class NoticeDaoTest {
	@Test
	public void testInsertNotice(){
		NoticeDao noticeDao = new NoticeDao();
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("첫번째 제목");
		noticeVO.setContent("첫번쨰 내용");
		//noticeVO.setWriter("moon1");
		
		try {
			int rowCount = noticeDao.insert(noticeVO);
			System.out.println(rowCount);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end.");
		
	}
}
