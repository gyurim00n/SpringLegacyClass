package org.doit.ik.aop4.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect //공통기능
@Component()
@Log4j
public class LogPrintProfiler4{
	//<aop:pointcut expression="execution(* org.doit.ik.aop..*.*(*,*))" id="calcPointcut"/>
	
	@Pointcut("execution(* org.doit.ik.aop..*.*(*,*))")
	private void calcPointcut() {}
	
	
	
	//p217 before advice
	@Before("calcPointcut()")
	public void before(JoinPoint joinPoint) throws Throwable{
		String methodName = joinPoint.getSignature().getName();
		log.info("😁😁>> " + methodName +"() : before 호출됨...");
	}


	//p222 around advice
	@Around("calcPointcut()")
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
	@AfterReturning(pointcut= "calcPointcut()", returning= "result")
	public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable{
		
		String methodName = joinPoint.getSignature().getName();
		log.info("😁😁>> " + methodName +"() : afterReturning 호출됨...");
	}
	
	
}
