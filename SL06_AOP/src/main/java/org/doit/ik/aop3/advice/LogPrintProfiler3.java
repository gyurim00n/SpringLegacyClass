package org.doit.ik.aop3.advice;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component()
@Log4j
public class LogPrintProfiler3{

	//p217 before advice
	public void before(JoinPoint joinPoint) throws Throwable{
		String methodName = joinPoint.getSignature().getName();
		log.info("😁😁>> " + methodName +"() : before 호출됨...");
	}


	//p222 around advice
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{

		// TODO Auto-generated method stub
		long start = System.nanoTime();

		//핵심 기능 + - * /
		//호출되는 메서드이름
		String methodName = joinPoint.getSignature().getName();
		log.info(">💕" + methodName+"() start");

		Object result = joinPoint.proceed();

		long end = System.nanoTime();
		log.info(">💕" + methodName+"() end");
		log.info(">💕💕" + methodName+"() 처리시간 : " + (end-start) +"ns");
		return result;
	}

	//p219 after returning advice
	public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable{
		
		String methodName = joinPoint.getSignature().getName();
		log.info("😁😁>> " + methodName +"() : afterReturning 호출됨...");
	}
	
	
}
