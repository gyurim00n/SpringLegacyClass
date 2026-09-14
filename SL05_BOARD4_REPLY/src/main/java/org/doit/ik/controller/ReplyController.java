package org.doit.ik.controller;

import java.util.List;

import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyVO;
import org.doit.ik.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//REST 방식으로 요청과 응답 처리
//클<->서 데이터 포맷과 타입을 명확하게 설계...

@RestController
@Log4j
@AllArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {

	private ReplyService replyService;
	
	//[1]댓글 등록 컨트롤러 메서드 선언
	@PostMapping(value = "new", consumes= "application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create (@RequestBody ReplyVO replyVO){
		log.info("ReplyController.create()...replyVO:" + replyVO);
		int rowCount = this.replyService.register(replyVO);
		log.info("ReplyController.create()...REPLY INSERT COUNT:" + rowCount);
		return rowCount == 1 ? new ResponseEntity<>("SUCCESS", HttpStatus.OK) //200
							: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
	}	
	
	
	@GetMapping(value="pages/{bno}/{page}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page){
		log.info("ReplyController.getList()...replyVO:");
		Criteria cri= new Criteria(1, 10);
		return new ResponseEntity<>(this.replyService.getList(cri, bno), HttpStatus.OK);
	}
	
	//댓글 상세보기
	@GetMapping(value = "/{rno}", 
	         produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	   public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		log.info("ReplyController.get()...");
		return new ResponseEntity<>(this.replyService.get(rno), HttpStatus.OK);
	}
	
	//댓글수정
	//메서드에 두개들어가서 리퀘스트매핑써야한다.
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }
			, value = "/{rno}"
			, consumes = "application/json"
			, produces = {MediaType.TEXT_PLAIN_VALUE })
	   public ResponseEntity<String> modify(@RequestBody ReplyVO replyVO, @PathVariable("rno") Long rno){
		log.info("ReplyController.modify()...rno: " + rno);
		log.info("ReplyVO:" + replyVO);
		return this.replyService.modify(replyVO) == 1 ? 
				new ResponseEntity<>("SUCCESS", HttpStatus.OK) //200
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
	}
	
	//댓글 삭제
	@DeleteMapping(value = "/{rno}", 
	         produces = { MediaType.TEXT_PLAIN_VALUE})
	   public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("ReplyController.remove()...rno:" + rno);
		return this.replyService.remove(rno) == 1 ? 
				new ResponseEntity<>("SUCCESS", HttpStatus.OK) //200
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
	}
	
}
