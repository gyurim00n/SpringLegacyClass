package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyVO;
import org.doit.ik.mapper.ReplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//REST 방식으로 요청과 응답 처리
//클<-JSON, 일반문자열->서 데이터 포맷과 타입을 명확하게 설계...(필요)

@Service
@Log4j
@AllArgsConstructor

public class ReplyServiceImpl implements ReplyService{
	
	private ReplyMapper replyMapper;
		
	@Override
	public int register(ReplyVO replyVO) {
		log.info("❤️ReplyServiceImpl.register()...");
		return this.replyMapper.insert(replyVO);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("❤️ReplyServiceImpl.get()...");
		return this.replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("❤️ReplyServiceImpl.modify()...");
		return this.replyMapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("❤️ReplyServiceImpl.remove()...");
		return this.replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("❤️ReplyServiceImpl.getList()...");
		return this.replyMapper.getListWithPaging(cri, bno);
	}

}
