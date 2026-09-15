package org.doit.ik.controller;

import java.util.ArrayList;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ScottController {
	
	@Setter(onMethod=@__({@Autowired}))
	   private DeptMapper deptMapper;
	
	@GetMapping("/scott/dept")
	public void dept(Model model) {
		log.info("😘ScottController.dept()...");
		ArrayList<DeptDTO>list = this.deptMapper.selectDept();
		model.addAttribute("list",list);
	}
	
}
