package org.doit.ik;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.scott.DeptDTO;
import org.doit.ik.domain.scott.EmpDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpDeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ScottController {
	
	// @Log4j == private static final Logger logger = LoggerFactory.getLogger(TimeMybatisController.class);
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private EmpMapper empMapper;
	
	
	
	//@RequestMapping(value = "/scott/dept", method = RequestMethod.GET)
	@GetMapping(value = "/scott/dept")
	public String dept(HttpServletRequest request) {
		log.info("🤩 ScottController.dept()...");
		
		ArrayList<DeptDTO> list =  this.deptMapper.selectDept();
		request.setAttribute("list", list);
		return "/scott/dept";
	}
	
	//deptno=10&deptno=30
	@PostMapping(value = "/scott/emp")
	public String emp(Model model, @RequestParam("deptno") int [] deptnoArr) {
		log.info("🤩 ScottController.emp()...");
		
		ArrayList<EmpDTO> list =  this.empMapper.selectEmp(deptnoArr);
	
		model.addAttribute("list", list);
		return "/scott/emp";
	}
	
	@GetMapping(value = "/scott/empdept") //못지키면 405
	public String empdept(Model model, @RequestParam(value = "deptno", defaultValue = "10") int deptno) {
		//if ..로 null을 지정해도 되지만, ㄱ그런경우에는 defaultValue를 지정해도 된다.
		//deptno 하나만 필요하면 required가 필요없으나, 여러개 붙으면 뭐가 required=false인지 알려줘야됨
		log.info("🤩 ScottController.empdept()...");
		
		ArrayList<DeptDTO> dlist =  this.deptMapper.selectDept();
		ArrayList<EmpDTO> elist =  this.empMapper.selectEmpDept(deptno);
		
		
		model.addAttribute("dlist", dlist);
		
		
		model.addAttribute("elist", elist);
		model.addAttribute("deptno", deptno);
		return "/scott/empdept";
	}
}
