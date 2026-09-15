package org.doit.ik.aop3;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
//p209 xml 기반 스프링 aop 적용방법
		//1.스프링 aop 모듈 추가: pom.xml
		//	spring-aop, aspectweaver
		//2.공통 기능을 할 클래스 선언
		//3.XML 파일<aop:aspect> 설정.
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop3/application-context.xml");
		
		Calculator calc = ctx.getBean("calc3", Calculator.class);
		System.out.println(calc.add(10,20));
		
	System.out.println("end");
	}

}
