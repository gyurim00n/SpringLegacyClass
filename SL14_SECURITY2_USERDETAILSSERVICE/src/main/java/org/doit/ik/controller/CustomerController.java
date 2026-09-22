package org.doit.ik.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.doit.ik.service.MemberShipService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

// 공지사항
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
@Log4j
public class CustomerController {

	private final NoticeMapper noticeDao;
	private final MemberShipService memberShipService;
 
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/notice.htm")
	public String notices( 
			  @RequestParam(value = "page", defaultValue = "1") int page 
			, @RequestParam(value = "field", defaultValue = "title") String field
			, @RequestParam(value = "query", defaultValue = "") String query 
			, Model model) throws ClassNotFoundException, SQLException {

		System.out.println("🤩 CustomerController.notices()...");

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
  
		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");

		return "customer.notice";  
	}
 
	@GetMapping("/noticeDetail.htm")
	public ModelAndView noticeDetail(@RequestParam("seq") String seq) throws ClassNotFoundException, SQLException {		
		System.out.println("🤩 CustomerController.noticeDetail()...");
		 
		this.noticeDao.hitUp(seq); 
		NoticeVO noticeVO = this.noticeDao.getNotice(seq);		 
		ModelAndView mav = new ModelAndView("customer.noticeDetail");
		mav.addObject("noticeVO", noticeVO); 

		return mav;		 
	}
 
	@GetMapping("/noticeReg.htm")
	public String noticeReg() {
		System.out.println("🤩 CustomerController.noticeReg()... GET");
		return "customer.noticeReg";
	}

	 
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;		
		while( true ) {			
			File f = new File(uploadRealPath, originalFilename);			
			if( !f.exists() ) return originalFilename;	 
			String fileName = originalFilename.substring(0, originalFilename.length() - 4 ); 
			String ext =  originalFilename.substring(originalFilename.length() - 4 ); 
			originalFilename = fileName+"-"+(index)+ext;
			index++;
		} // while 
	}
 
	@PostMapping("/noticeReg.htm")
	public String noticeReg(
			NoticeVO noticeVO
			, RedirectAttributes rttr 
			, HttpServletRequest request
			) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		System.out.println("🤩 CustomerController.noticeReg()... POST");

		// 실제 서버에 배포 경로
		String uploadRealPath = null;

		// 0. 첨부된 파일이 있다면   upload 폴더 생성
		CommonsMultipartFile multipartFile = noticeVO.getFile();
		
		if( !multipartFile.isEmpty() ) {
			// ㄱ. 파일 upload 폴더에 추가
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("🤣🤣🤣  uploadRealPath : " + uploadRealPath); 
			
			//   첨부파일의 원래 이름
			String originalFilename =  multipartFile.getOriginalFilename(); 
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);

			File dest = new File(uploadRealPath, filesystemName );
			multipartFile.transferTo(dest);   
			noticeVO.setFilesrc(filesystemName);
		} // if
 
		noticeVO.setWriter("kenik"); 
		int rowCount =  1; 
		this.memberShipService.insertAndPointUpOfMember(noticeVO, "kenik"); 
		
		if ( rowCount == 1 ) { 
			rttr.addFlashAttribute("result", rowCount); // 일회성
			return "redirect:notice.htm";
		} else {
			return "noticeReg.htm?error";
		} // if

	}
 
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq
			, Model model) throws ClassNotFoundException, SQLException {
		System.out.println("🤩 CustomerController.noticeEdit()... GET");
		NoticeVO noticeVO = this.noticeDao.getNotice(seq);
		model.addAttribute("noticeVO", noticeVO);
		return "customer.noticeEdit";
	}
 
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(
			NoticeVO noticeVO  // 커맨드 객체 + 새로 첨부된 파일 
			, @RequestParam("o_filesrc") String ofilesrc
			, HttpServletRequest request
			, RedirectAttributes rttr  
			, Model model) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		System.out.println("🤩 CustomerController.noticeEdit()... POST");
 
		String uploadRealPath = null; 
		CommonsMultipartFile multipartFile = noticeVO.getFile();
		if( !multipartFile.isEmpty() ) { 
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("🤣🤣🤣  uploadRealPath : " + uploadRealPath); 		
			File delFilesrc = new File(uploadRealPath, ofilesrc);
			if( delFilesrc.exists() && delFilesrc.isFile() ) delFilesrc.delete(); 
			String originalFilename =  multipartFile.getOriginalFilename(); 
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);

			File dest = new File(uploadRealPath, filesystemName );
			multipartFile.transferTo(dest);   
			noticeVO.setFilesrc(filesystemName);
		} else{
			noticeVO.setFilesrc(ofilesrc);
		} // if
 
		int rowCount = this.noticeDao.update(noticeVO);

		rttr.addAttribute("seq", noticeVO.getSeq());
		return "redirect:noticeDetail.htm";		 
	}
 
	@GetMapping("/noticeDel.htm")
	public String noticeDel(
			RedirectAttributes rttr
			, @RequestParam("seq") String seq
			, @RequestParam("filesrc") String filesrc
			, HttpServletRequest request
			) throws ClassNotFoundException, SQLException {
		System.out.println("🤩 CustomerController.noticeDel()... ");

		// 1. 삭제 DB 처리
		int rowCount = this.noticeDao.delete(seq);
		// 2. 첨부된 파일 있는 경우 첨부파일 삭제 추가
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		File delFilesrc = new File(uploadRealPath, filesrc);
		if( delFilesrc.exists() && delFilesrc.isFile() ) delFilesrc.delete();

		// 
		rttr.addAttribute("page", "1");
		rttr.addFlashAttribute("result", rowCount);
		return "redirect:notice.htm";
	}
 
	@GetMapping("/download.htm")
	public void download(
			@RequestParam("dir") String p  , 
			@RequestParam("file") String f ,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {

		String fname =  f;  		
		response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));
		String fullPath = request.getServletContext().getRealPath(	p + "/" + fname);

		FileInputStream fin = new FileInputStream(fullPath);
		ServletOutputStream sout = response.getOutputStream(); // 응답 스트림
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fin.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size); 
		}
		fin.close();
		sout.close();

	}

} // class











