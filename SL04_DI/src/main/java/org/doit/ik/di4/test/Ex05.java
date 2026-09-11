package org.doit.ik.di4.test;

import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		//p115 컴포넌트 스캔을 이용한 빈 자동 등록+ 자동의존 주입
		//p103 애노테이션을 이용한 객체 간의 의존 자동 연결
		String [] resourceLocations = {"classpath:org/doit/ik/di4/application-context4.xml"};
	
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		

		RecordViewImpl4 rvi = ctx.getBean("rvi", RecordViewImpl4.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println("끝@"); 
	}
}
