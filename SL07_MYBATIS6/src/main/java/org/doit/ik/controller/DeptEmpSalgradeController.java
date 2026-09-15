package org.doit.ik.controller;

import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.DeptEmpSalgradeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@AllArgsConstructor
public class DeptEmpSalgradeController {
		
	private DeptEmpSalgradeMapper deptEmpSalgradeMapper;

	@RequestMapping(value = "/dept/emp", method = RequestMethod.GET)
	public void getDeptEmpSalgrade(Locale locale, Model model) {
		log.info("getDeptEmpSalgrade");

		//1.모든 부서정보 조회
		//empList는 <collection>에 의해 자동으로 채워진다.
		List<DeptDTO> desList = this.deptEmpSalgradeMapper.getDept();
		
		
		//2.각 부서의 부서원별 조회
		/*
		 * for(DeptEmpSalgradeDTO dto: desList) { List<EmpDTO> empList =
		 * this.deptEmpSalgradeMapper.getEmpOfDept(dto.getDeptno());
		 * dto.setEmpList(empList); }
		 */
		model.addAttribute("desList", desList);
		
	}

}
