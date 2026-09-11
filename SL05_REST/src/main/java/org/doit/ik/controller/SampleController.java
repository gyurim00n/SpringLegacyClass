package org.doit.ik.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.doit.ik.domain.SampleVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/sample")
public class SampleController {
	//컨트롤러 메서드 								일반적인 문자열 생성합ㄴ디ㅏ..(생산하는 MIME타입) 
@GetMapping(value="/getText", produces= "text/plain; charset=UTF-8")
public String getText() {
	log.info("sadsfdasdafasdf");
	return "안녕하세요~";

}



//컨트롤러 메서드: 객체 반환 SampleVO.java
//@GetMapping(value="/getSampleVO", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@GetMapping(value="/getSampleVO", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
public SampleVO getSampleVO() {
	log.info("---------SampleController.getSamplvVO");
	return new SampleVO(1, "스타", "강");

}

//@GetMapping(value="/getSampleVOList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@GetMapping(value="/getSampleVOList", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
public List<SampleVO> getSampleVOList() {
	log.info("---------SampleController.getSampleVOList");
	return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, "first-"+i, "last-" + i)).collect(Collectors.toList());

}

}
