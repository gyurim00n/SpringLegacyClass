package org.doit.ik.di3;

import org.doit.ik.di3.RecordViewImpl3;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		//p103 애노테이션을 이용한 객체 간의 의존 자동 연결
		String [] resourceLocations = {"classpath:org/doit/ik/di3/application-context3.xml"};
	
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		

		RecordViewImpl3 rvi = ctx.getBean("rvi", RecordViewImpl3.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println("끝@");
	}
}
