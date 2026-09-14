package org.doit.ik.controller;

import org.doit.ik.service.EmpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
public class EmpController {
	//private BoardService boardService;
		
	//spring 4.3 이상에서 자동 주입
	private EmpService empService;
	
	
	// /empnoCheck/\${empno} 컨트롤러 메서드 선언
	@GetMapping("/empnoCheck/{empno}")
	public String checkEmpno(@PathVariable("empno") int empno) {
		boolean isAvailable= this.empService.isEmpnoAvailable(empno);
		return isAvailable? "AVAILABLE" : "x";
	}
	
}
