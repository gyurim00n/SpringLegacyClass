package org.doit.ik.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeVO {
	
	private String seq;
	private String title;
	private String writer;
	private Date regdate;
	private String filesrc;  // 수정   첨부된파일의 이름
	private int hit;
	private String content;
	
	/* <input type="file" id="txtFile" name="file" /> */
	// private MultipartFile file;
	private CommonsMultipartFile file;
	
}
