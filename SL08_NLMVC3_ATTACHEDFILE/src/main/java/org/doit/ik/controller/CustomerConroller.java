package org.doit.ik.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

//공지사항
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerConroller {





	private final NoticeDao noticeDao;



	//p356
	//[1] 컨트롤러 메서드 : 공지사항 목록
	@GetMapping(value= "/notice.htm")
	public String notices(@RequestParam(value="page", defaultValue ="1")int page
			,@RequestParam(value="field", defaultValue ="title")String field
			,@RequestParam(value="query", defaultValue ="")String query
			,Model model
			) throws ClassNotFoundException, SQLException { //(void, String, ModelAndView, 객체 다 됨)
		System.out.println("😁dddCustomerConroller.notices()");


		List<NoticeVO> list = this.noticeDao.getNotices(page,field,query);

		//ModelAndView	리턴자료형 p282 모델+view

		model.addAttribute("message", "HelloWorld");


		model.addAttribute("list", list);


		return "notice.jsp";

	}

	//[2] 컨트롤러 메서드: 공지사항 상세보기 
	@GetMapping(value="/noticeDetail.htm")
	public ModelAndView noticeDetail(@RequestParam("seq") String seq) throws ClassNotFoundException, SQLException {
		System.out.println("😁dddCustomerConroller.noticeDetail()");

		ModelAndView mav = new ModelAndView("noticeDetail.jsp");

		NoticeVO noticeVO= this.noticeDao.getNotice(seq);

		mav.addObject("noticeVO", noticeVO);

		return mav;


	}

	//[3] 컨트롤러 메서드: 공지사항 글쓰기
	//customer/noticeReg.htm -> noticeReg.jsp 포워딩
	@GetMapping(value="/noticeReg.htm")
	public String noticeReg(){
		System.out.println("😁dddCustomerConroller.noticeReg()..GET");
		return "noticeReg.jsp";
	}
	/*
	//[3-2] 컨트롤러 메서드: 공지사항 글쓰기
	//customer/noticeReg.htm + POST 
	@PostMapping(value="/noticeReg.htm")
	public String noticeReg(@RequestParam(value="title", required = true) String title
			,@RequestParam(value="content", required = false) String content
			,RedirectAttributes rttr) throws ClassNotFoundException, SQLException{
		System.out.println("😁dddCustomerConroller.noticeReg()..post");
		//1. DB insert 첳리
		NoticeVO noticeVO = NoticeVO.builder()
				.title(title)
				.content(content)
				.build();
		int rowCount = this.noticeDao.insert(noticeVO);
		rttr.addFlashAttribute("result", rowCount); //1회성


		//2. 목록 페이지 이동
		//return "noticeReg.jsp?error", // 포워딩
		return "redirect:notice.htm";
	}
	 */

	// [3]                                                  a.txt
	   private String getFileNameCheck(String uploadRealPath, String originalFilename) {
	      int index = 1;      
	      while( true ) {         
	         File f = new File(uploadRealPath, originalFilename);         
	         if( !f.exists() ) return originalFilename;   
	         // a
	         String fileName = originalFilename.substring(0, originalFilename.length() - 4 );
	         // .txt
	         String ext =  originalFilename.substring(originalFilename.length() - 4 );
	         //                        a-2.txt  
	         originalFilename = fileName+"-"+(index)+ext;
	         index++;
	      } // while 
	   }
	
	
	
	//[3-3] 컨트롤러 메서드: 공지사항 글쓰기
	
	//customer/noticeReg.htm + POST 
	@PostMapping(value="/noticeReg.htm")
	public String noticeReg(NoticeVO noticeVO //이 객체 하나로 requestparam 반복하지않아도 된다.
			,RedirectAttributes rttr
			,HttpServletRequest request) throws ClassNotFoundException, SQLException, IllegalStateException, IOException{
		System.out.println("😁dddCustomerConroller.noticeReg()..post");
		
		//실제 서버에 배포경로
		String uploadRealPath= null;
		//0.첨부된파일이 있다면...	upload 폴더 생성 ///src/main/webapp/customer/upload
		CommonsMultipartFile multipartFile = noticeVO.getFile();
		if(!multipartFile.isEmpty()) {
			//ㄱ. 파일 upload폴더에 추가
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("👍👍👍 uploadRealPath: " + uploadRealPath);
			//첨부 파일의 원래 이름
			String  originalFilename = multipartFile.getOriginalFilename();
			//upload 폴더에 저장할 파일 이름
			String filesystemName= getFileNameCheck(uploadRealPath, originalFilename);
			
			
			File dest= new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest); //파일 저장
			
			//ㄴ. NoticeVO.setFilesrc(첨부된 파일명)
			noticeVO.setFilesrc(filesystemName);
			
			
		}//if
		
		//1. DB insert 첳리

		int rowCount = this.noticeDao.insert(noticeVO);
		rttr.addFlashAttribute("result", rowCount); //1회성

		if (rowCount ==1) {
			//2. 목록 페이지 이동
		
			return "redirect:notice.htm";
		}else {
			
			return "noticeReg.htm?error";
		}//if
	}
	
	//[4] 컨트롤러 메서드 : 공지사항 수정하기
	//noticeEdit?seq=${noticeVo.seq}.htm"
	@GetMapping(value="/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq, Model model) throws ClassNotFoundException, SQLException{
		//값이 넘겨져 올거라 model 이라함.
		
		//쿼리에 들어갈거라 string으로  seq받아도 괜찮
		NoticeVO noticeVO= this.noticeDao.getNotice(seq);
		model.addAttribute("noticeVO", noticeVO);
		System.out.println("😁dddCustomerConroller.noticeEdit()..GET");
		
		return "noticeEdit.jsp";
	}
	
	//noticeEdit.htm?seq=3" + POST
	@PostMapping(value="/noticeEdit.htm")
	public String noticeEdit(NoticeVO noticeVO, RedirectAttributes rttr) throws ClassNotFoundException, SQLException {
		System.out.println("😁dddCustomerConroller.noticeEdit()..POST");
		int rowCount = this.noticeDao.update(noticeVO);

		rttr.addFlashAttribute("result", rowCount); //1회성
		rttr.addAttribute("seq", noticeVO.getSeq());
		
		return "redirect:noticeDetail.htm";
	}
	//[5]공지사항 삭제	->redirect:notice.htm 으로 요청 
	//http://localhost/customer/noticeDel.htm?seq=3
	@GetMapping(value="/noticeDel.htm")
	public String noticeDel(@RequestParam("seq") String seq, RedirectAttributes rttr) throws ClassNotFoundException, SQLException {
		System.out.println("😁dddCustomerConroller.noticeDel()..GET");
		int rowCount = this.noticeDao.delete(seq);
		rttr.addFlashAttribute("result", rowCount); //1회성
		rttr.addAttribute("page", 1);
		return "redirect:notice.htm";
	}
}
