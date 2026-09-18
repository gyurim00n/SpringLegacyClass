package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class MemberShipServiceImpl implements MemberShipService {
	
	private final NoticeDao noticeDao;
	 // [5] 선언적 트랜잭션 방법으로 처리한 메서드
	   @Transactional(propagation = Propagation.REQUIRED)
	   @Override
	   public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {

	      this.noticeDao.insert(noticeVO); // @T    TEST 2 - 2
	      
	      noticeVO.setTitle(noticeVO.getTitle() + " - two");
	      this.noticeDao.insert(noticeVO); // @T   
	      
	   }  
	   
}