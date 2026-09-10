package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.BoardVO;

public interface BoardMapper {
	

	//[1]게시글 목록
	List<BoardVO> getList();
	
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
