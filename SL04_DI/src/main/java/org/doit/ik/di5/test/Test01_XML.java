package org.doit.ik.di5.test;

import org.doit.ik.di5.AuthException;
import org.doit.ik.di5.AuthenticationService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test01_XML {

	public static void main(String[] args) {
		/* [복습문제] p62 예제 클래스들로 구성하여 
		 * application-context.xml 로 컴포넌트 스캔을 사용 예제
		 * */ 
		
		String [] resourceLocations = {  "classpath:org/doit/ik/di5/application-context5.xml" };
		GenericXmlApplicationContext ctx= new GenericXmlApplicationContext(resourceLocations);
		
		AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);
		
		System.out.println( authSvc.getUserRepository().getUserMap().size() );
		

		/*
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "11111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "111111");
		
		try { 
			//authSvc.authenticate("bkchoi2", "1111");
		} catch (UserNotFoundException ex) {
		}
		authSvc.authenticate("bkchoi", "1234");
		PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
		pwChgSvc.changePassword("bkchoi", "1234", "5678");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1234");
		authSvc.authenticate("bkchoi", "5678");
		*/
		
		ctx.close();
	}
	
	private static void runAuthAndCatchAuthEx(
			AuthenticationService authSvc, String userId, String password) {
		try {
			authSvc.authenticate(userId, password);
		} catch (AuthException ex) {
		}
	}

}
