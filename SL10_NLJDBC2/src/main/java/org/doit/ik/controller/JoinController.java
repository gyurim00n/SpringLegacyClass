package org.doit.ik.controller;

import org.doit.ik.persistence.MemberDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

//공지사항
@Controller
@RequestMapping("/joinus")
@RequiredArgsConstructor
public class JoinController {

	private final MemberDao memberDao;

	//1. 회원가입: /joinus/join.htm -> /joinus/join.jsp
	@GetMapping("/join.htm")
	public String  join() throws Exception{
		System.out.println("😁JoinController.join()...");
		return "join.jsp";
	}
	
	//2. 로그인: /joinus/login.htm -> /joinus/login.jsp
	@GetMapping("/login.htm")
	public String login() throws Exception{
		System.out.println("😁JoinController.login()...");
		return "login.jsp";
	}
	
	
}//class
