package org.doit.ik.controller;

import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.DeptEmpSalgradeDTO;
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

		List<DeptEmpSalgradeDTO> list = this.deptEmpSalgradeMapper.getDeptEmpInfo();		
		model.addAttribute("list", list );

	}

}
