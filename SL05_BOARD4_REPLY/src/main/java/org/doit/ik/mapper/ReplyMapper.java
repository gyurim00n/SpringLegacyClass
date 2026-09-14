package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyVO;

public interface ReplyMapper {
	//[1]댓글쓰기
	int insert(ReplyVO replyVO);
	
	//[2] 댓글 조회: 게시글 번호(bno)를 조회해서 댓글 목록 반환
	ReplyVO read(Long rno);
	
	//[3]댓삭
	int delete(Long rno);
	
	//[4]댓글 수정
	int update(ReplyVO replyVO);
	
	//[5]댓글 목록
	//MyBatis에서 두개 이상의 데이터를 파라미터로 전달하는법?
	//1)Map을 이용
	//2)@ㅖㅁㄱ므 어노테이션 이용
	List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	//[6]댓글 갯수
	int getCountByBno(Long bno);
}
