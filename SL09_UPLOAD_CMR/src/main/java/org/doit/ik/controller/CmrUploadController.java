package org.doit.ik.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.doit.ik.domain.Message;
import org.doit.ik.domain.MultiMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cmr")
public class CmrUploadController {

	   @GetMapping("multiupload")
	   public void multiupload() {}
	   
	   @PostMapping("multiupload")
	   public void multiupload(MultiMessage multiMessage) {
	      
	      log.info("🤩> CmrUploadController.multiupload()... + POST");
	      
	      // 1. 
	      String output = multiMessage.getOutput(); 
	      log.info("🤩1. output : " + output);
	      
	      // 2. 
	      List<CommonsMultipartFile> attachList = multiMessage.getAttach();
	      
	      for (CommonsMultipartFile attach : attachList) {
	         
	         if ( !attach.isEmpty() ) {
	            log.info("-".repeat(30));
	            String originalFilename = attach.getOriginalFilename();
	            log.info("🤩2. originalFilename : " + originalFilename);
	            
	            long fileSize = attach.getSize();
	            log.info("🤩3. fileSize : " + fileSize);
	            
	            // 첨부파일 저장
	            // String uploadRealPath = request.getServletContext().getRealPath("/cmr/upload");         
	            String uploadRealPath = "C:\\upload";
	            File dest = new File(uploadRealPath, originalFilename);
	            try {
	               attach.transferTo(dest);
	            } catch (IllegalStateException | IOException e) { 
	               e.printStackTrace();
	            }  // try
	         } // if         
	      } // foreach
	      
	      log.info("🤩 end. ");
	      
	   }

	/////////////////////////////////////////////////////////////
	@GetMapping("upload")
	public void upload() {

	}


	@PostMapping("upload")
	public void upload(Message message) {
		//String 
		log.info("💕>CmrUploadController.upload+POST" );

		//1.
		String output=message.getOutput();
		log.info("💕1.output: " + output );

		//2.
		MultipartFile attach= message.getAttach();
		if ( !attach.isEmpty() ) {
            log.info("-".repeat(30));
            String originalFilename = attach.getOriginalFilename();
            log.info("🤩2. originalFilename : " + originalFilename);
            
            long fileSize = attach.getSize();
            log.info("🤩3. fileSize : " + fileSize);
            
            // 첨부파일 저장
            // String uploadRealPath = request.getServletContext().getRealPath("/cmr/upload");         
            String uploadRealPath = "C:\\upload";
            File dest = new File(uploadRealPath, originalFilename);
            try {
               attach.transferTo(dest);
            } catch (IllegalStateException | IOException e) { 
               e.printStackTrace();
            }  // try
         } // if         

	}
}
