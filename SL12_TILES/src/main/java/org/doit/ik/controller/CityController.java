package org.doit.ik.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class CityController {
	
	@GetMapping("/city/london")
	public String london() {
		return "city/london.tiles"; //타일즈뷰
				
	}
	
	@GetMapping("/city/paris")
	public String paris() {
		return "city/paris.tiles"; 
		
	}
	
	@GetMapping("/city/seoul")
	public String seoul() {
		return "city/seoul.tiles"; 
		
	}
	
}
