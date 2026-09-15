package org.doit.ik.aop4;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		//p226 @aspect 어노테이션 기반: 스프링 AOP
		//1. @Aspect 어노테이션 Aspect 클래스 구현: pointcut + advice
		//2. @aspectj-autoproxy를 application-context.xml에 추가
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop4/application-context.xml");
		
		Calculator calc = ctx.getBean("calc4", Calculator.class);
		System.out.println(calc.add(10,20));
		
	System.out.println("end");
	}

}
