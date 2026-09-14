package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyVO;

public interface ReplyService {
	
	//댓글등록
	public int register(ReplyVO replyVO);
	
	//댓글 상세보기
	public ReplyVO get(Long rno);
	
	//댓글 수정
	public int modify(ReplyVO vo);
	
	//댓글 삭제
	public int remove(Long rno);
	
	//댓글 목록 조회
	public List<ReplyVO>getList(Criteria cri, Long bno);
	
	
}
