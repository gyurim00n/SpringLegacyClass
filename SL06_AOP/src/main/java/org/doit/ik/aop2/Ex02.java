package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
	//1. 스프링 AOP API 사용하는 법
	//application-context.xml
	
		//org.doit.ik.aop2.adivce 패키지
		// ㄴ LogPrintAroundADvice.java 공통 기능 클래스 추가
	
		
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop2/application-context.xml");
	Calculator calc = ctx.getBean("calcProxy", Calculator.class);
	System.out.println(calc.add(10,20));
	
//	calc= ctx.getBean("calc", Calculator.class);
//	System.out.println(calc.add(1,2));
	
	
	System.out.println("end");
	}

}
