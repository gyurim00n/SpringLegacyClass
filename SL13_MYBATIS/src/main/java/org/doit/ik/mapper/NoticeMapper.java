package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.NoticeVO;
import org.springframework.transaction.annotation.Transactional;
//@Transactional

public interface NoticeMapper {	

	   public int getCount(@Param("field") String field, @Param("query") String query) throws ClassNotFoundException, SQLException;

	   public List<NoticeVO> getNotices(@Param("page") int page ,@Param("field")  String field, @Param("query") String query) throws ClassNotFoundException, SQLException;

	   public int delete(String seq) throws ClassNotFoundException, SQLException;

	   public int update(NoticeVO noticeVO) throws ClassNotFoundException, SQLException;

	   public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;

	   @Transactional()
	   public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException;
	   
	   public void hitUp(String seq) throws ClassNotFoundException, SQLException;
	   public int getHit(String seq) throws ClassNotFoundException, SQLException;
	   
	   
}
