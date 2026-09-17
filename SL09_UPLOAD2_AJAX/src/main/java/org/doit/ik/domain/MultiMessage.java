package org.doit.ik.domain;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MultiMessage {
	
	private String output;
	
	/* <input type="file" name="attach" multiple="multiple"/> */
	//private CommonsMultipartFile[] attach;
	private List<CommonsMultipartFile> attach;
}
