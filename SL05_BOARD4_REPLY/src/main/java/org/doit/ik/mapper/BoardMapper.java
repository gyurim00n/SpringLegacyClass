package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardMapper {
	

	//[1]게시글 목록 (페이징 처리 X)
	List<BoardVO> getList();
	
	//[1-2] 게시글 목록 + 페이징 처리O
	List<BoardVO> getListwithPaging(Criteria criteria);
	int getTotalCount(Criteria criteria); //총 레코드 수 
	
	
	//[2]게시글 쓰기
	int insert(BoardVO boardVO);
	
	//[2-2]게시글 쓰기 + 글 번호 반환 기능 추가 구현
	int insertSelectKey(BoardVO boardVO);
	
	//[3] 게시글 상세보기 
	BoardVO read(@Param("bno") Long bno);
	
	//[4]게시글 삭제
	int delete(@Param("bno") Long bno);

	int update(BoardVO boardVO);
}
