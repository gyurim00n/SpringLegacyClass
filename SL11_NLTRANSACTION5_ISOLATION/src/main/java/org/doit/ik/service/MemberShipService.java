package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;

public interface MemberShipService {
	 // 트랜잭션 테스트 추상 메서드 추가
	   public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException;
}
