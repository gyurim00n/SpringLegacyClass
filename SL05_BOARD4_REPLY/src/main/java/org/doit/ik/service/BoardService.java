package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardService {
	//[1] 게시글 목록
	List<BoardVO> getList();

	
	//[1-2]게시글 목록+ 페이징 처리O
	List<BoardVO> getListWithPaging(Criteria criteria);
	//[1-3]총 레코드 수
	int getTotalCount(Criteria criteria);
	
	//[2]게시글 쓰기
	int register(BoardVO boardVO);
	
	//[3]게시글 상세보기
	BoardVO get(Long bno);
	
	//[4]
	boolean remove(Long bno);

	//[5] 게시글 수정
	boolean modify(BoardVO boardVO);
	
}
