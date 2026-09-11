package org.doit.ik.di2;

import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//p85 스프링을 사용해서 객체 조립/사용하기 (자바 코드 config.java)
		//(성적 정보를 입력받아서 출력하는일)
		
		//p62
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
	RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println("끝@");
	}

}
