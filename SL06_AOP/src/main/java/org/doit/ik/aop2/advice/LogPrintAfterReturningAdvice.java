package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("logPrintAfterReturningAdvice")
@Log4j
public class LogPrintAfterReturningAdvice implements AfterReturningAdvice{ 
   
   @Override
   public void afterReturning(
           Object returnValue, // 결과값
           Method method, 
           Object[] args, 
           Object target
           ) throws Throwable {
      
      String methodName = method.getName();
      log.info("<< " + methodName +"() : LogPrintAfterReturningAdvice 호출됨...");
      
   }
   
    

}

