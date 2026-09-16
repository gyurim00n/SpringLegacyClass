package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//공지사항 목록 컨트롤러

public class NoticeController implements Controller{

	
	private NoticeDao noticeDao;
	
	public NoticeController() {
		super();
	}
	
	public NoticeController(NoticeDao noticeDao) {
		super();
		this.noticeDao = noticeDao;
	}
	
	public NoticeDao getNoticeDao(NoticeDao noticeDao) {
		return noticeDao;
	}
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}


	// http://localhost/customer/notice.htm?page=2&field=검색조건&query=검색어
	@Override
	public ModelAndView handleRequest(HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		//ModelAndView	리턴자료형 p282 모델+view
		

		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");

		int page = 1;
		String field = "title";
		String query = "";

		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;
		
		
		List<NoticeVO> list = this.noticeDao.getNotices(page,field,query);

		//ModelAndView	리턴자료형 p282 모델+view
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("message", "HelloWorld");

		mav.setViewName("notice.jsp");
			
		return mav;
	}

}
