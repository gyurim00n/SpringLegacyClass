package org.doit.ik.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/common")
@RequiredArgsConstructor
@Log4j
public class CommonController {
	@GetMapping("/accessError.htm")
	public String accessDeineid (Model model, Authentication authentication) {
		System.out.println("cc cc ");
		model.addAttribute("msg", "접근금지됨");
		return "/common/accessError";
	}
}
