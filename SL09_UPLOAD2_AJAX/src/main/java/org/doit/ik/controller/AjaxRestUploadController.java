package org.doit.ik.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.doit.ik.domain.Message;
import org.doit.ik.domain.MultiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/ajax/")
public class AjaxRestUploadController {


	@PostMapping(value = "uploadAjax", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<String>> uploadAjax(@RequestParam("attachList") List<MultipartFile> attachList){
		
		List<String> list = new ArrayList<String>();
		
		
		String uploadRealPath = "C:\\upload";
		// 2026/09/17
		String datePath = getFolder();
		//uploadRealPath += "\\"+ datePath; // c:\\upoad\\2026\\09\\17
		File uploadFolder = new File(uploadRealPath, datePath);
		if(!uploadFolder.exists()) uploadFolder.mkdirs();
		
		for (MultipartFile attach : attachList) {

			if ( !attach.isEmpty() ) {
				log.info("-".repeat(30));
				String originalFilename = attach.getOriginalFilename();
				log.info("🤩2. originalFilename : " + originalFilename);

				long fileSize = attach.getSize();
				log.info("🤩3. fileSize : " + fileSize);

				//1. UUID_동일한 파일명 
				UUID uuid = UUID.randomUUID();
				originalFilename = uuid + "_" + originalFilename;
				
				// 첨부파일 저장
				// String uploadRealPath = request.getServletContext().getRealPath("/cmr/upload");         
				
				
				
				
				File dest = new File(uploadFolder, originalFilename);
				try {
					attach.transferTo(dest); //실제 파일을 저장하는 함수. 
					
					list.add(originalFilename);
					
					if(checkImageType(dest)) {
	                   // File thumbnailFile = new File(uploadRealPath, "s_" + originalFilename);
						File thumbnailFile = new File(uploadFolder, "s_" + originalFilename);
	                    // InputStream 대신 File 객체를 직접 전달하여 썸네일 생성
	                    Thumbnailator.createThumbnail(dest, thumbnailFile, 100, 100);
	                }
					
				} catch (IllegalStateException | IOException e) { 
					e.printStackTrace();
				}  // try
			} // if         
		} // foreach

		log.info("🤩 end. ");
		return new ResponseEntity<>(list, HttpStatus.OK);
	}//method

	private boolean checkImageType(File file) {
		try {
			//파일의 실제 MIME타입(content-type) 알아내는 코딩
			//System.out.println("🧨🧨" + Files.probeContentType(file.toPath()));
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	//날짜 폴더명을 만들어서 문자열로 반환하는 메서드 yyyy-MM-dd
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String str = sdf.format(today);
		return str.replace("-", File.separator); //2026/09/17
	}
}
