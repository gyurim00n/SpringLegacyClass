package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;
	
	/*[1]
	@Test
	public void testPaging() {
		
		Criteria criteria = new Criteria(2,2);
		List<BoardVO> list =  this.boardMapper.getListwithPaging(criteria);
		
		list.forEach(boardVO -> System.out.println("😂" + boardVO));
		
	}
	*/
	
	@Test
	public void testSearch() {
		
		Criteria criteria = new Criteria(1,10);
		criteria.setType("TCW");
		criteria.setKeyword("스프링-1");
		
		
		List<BoardVO> list =  this.boardMapper.getListwithPaging(criteria);
		
		list.forEach(boardVO -> System.out.println("😂" + boardVO));
		
	}
}
