package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getList(){
		log.info("❤️BoardServiceImple.getList()...");
		
		return this.boardMapper.getList();
	}

	@Override
	public int register(BoardVO boardVO) {
		log.info("❤️BoardServiceImple.register()...");
		return this.boardMapper.insertSelectKey(boardVO);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("❤️BoardServiceImple.get()...bno:" + bno);
		return this.boardMapper.read(bno);
	}

	@Override
	public boolean remove(Long bno) {
		log.info("❤️BoardServiceImple.remove()...bno:" + bno);
		return this.boardMapper.delete(bno) == 1;
	}
}
