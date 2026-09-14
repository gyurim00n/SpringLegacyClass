package org.doit.ik.aop2.advice;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("logPrintAroundAdvice")
@Log4j
public class LogPrintAroundAdvice implements MethodInterceptor{
	
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		// TODO Auto-generated method stub
		long start = System.nanoTime();
		
		//핵심 기능 + - * /
		//호출되는 메서드이름
		String methodName = method.getMethod().getName();
		log.info(">💕" + methodName+"() start");
		
		Object result = method.proceed();
		
		long end = System.nanoTime();
		log.info(">💕" + methodName+"() end");
		log.info(">💕💕" + methodName+"() 처리시간 : " + (end-start) +"ns");
		return result;
	}

}
