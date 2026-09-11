package org.doit.ik.di5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthFailLogger {	//[3]
	
	// [1] 설정 없으면 기본값 5
	// @Value("${authfail.threshold:5}") 
	// private int threshold;
	// [2] application.properties 또는 application.yml에 설정 가능:
	// authfail.threshold=5
	
	private int threshold = 5;
	private int failCounts;
	
	public void insertBadPw(String userId, String inputPw) {
		System.out.printf("AuthFail [type=badpw, userid=%s, pw=%s]\n", userId, inputPw);
		failCounts++;
		if (threshold > 0 && failCounts > threshold) {
			notifyTooManyFail();
			failCounts = 0;
		}
	}

	private void notifyTooManyFail() {
		System.out.println("너무 많은 로그인 시도 실패");
	}

	public void setThreshold(int thresold) {
		this.threshold = thresold;
	}

}
