package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//공지사항 상세보기 컨트롤러

public class NoticeDetailController implements Controller{

	
	private NoticeDao noticeDao;
	
	public NoticeDetailController() {
		super();
	}
	
	public NoticeDetailController(NoticeDao noticeDao) {
		super();
		this.noticeDao = noticeDao;
	}
	
	public NoticeDao getNoticeDao(NoticeDao noticeDao) {
		return noticeDao;
	}
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}


	// http://localhost/customer/noticeDetail.htm?seq=${ dto.seq }
	@Override
	public ModelAndView handleRequest(HttpServletRequest request
			, HttpServletResponse response) throws Exception {


		String seq= request.getParameter("seq");
		NoticeVO noticeVO= this.noticeDao.getNotice(seq);

		//ModelAndView	리턴자료형 p282 모델+view
		
		ModelAndView mav = new ModelAndView("noticeDetail.jsp");
		mav.addObject("noticeVO", noticeVO);
		

		//mav.setViewName("noticeDetail.jsp");
			
		return mav;
	}

}
