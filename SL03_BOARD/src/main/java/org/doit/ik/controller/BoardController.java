package org.doit.ik.controller;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/")
public class BoardController {
	//private BoardService boardService;
		
	//spring 4.3 이상에서 자동 주입
	private BoardService boardService;
	
	//[1]/board/list + GET 게시글 목록 요청
	@GetMapping(value = "list")
	public void list(Model model) { //보이드면 요청url과 같음
		log.info("😂 BoardController.list()...GET");
		List<BoardVO> list = this.boardService.getList();
		model.addAttribute("list", this.boardService.getList());
		/* return "/board/list"; */
	}
	
	//[2]/board/register + GET 게시글 쓰기 페이지 요청
	@GetMapping(value = "register")
	public void register(Model model) { //보이드면 요청url과 같음
		log.info("😂 BoardController.register()...GET");
	}
	
	//[2-2]/board/register + POST 게시글 쓰기 페이지 요청
	@PostMapping(value = "register")
	public String register(Model model, BoardVO boardVO, RedirectAttributes rttr) { //보이드면 요청url과 같음
		log.info("😂 BoardController.register()...POST");
		log.info("~~~" + boardVO);
		//포워딩
		this.boardService.register(boardVO);
		
		/* rttr.addAttribute("result", boardVO.getBno()); list.jsp?result=*/
		rttr.addFlashAttribute("result", boardVO.getBno());
		
		
		//리다이렉트 
		return "redirect:/board/list";
	}
	
	// /board/get?bno=4
	// /board/modify?bno=4
	//[3]
	@GetMapping(value = {"get", "modify"})
	public void get(Model model, @RequestParam("bno") Long bno) { //보이드면 요청url과 같음
		log.info("😂 BoardController.get&modify()...GET bno : " + bno);
		BoardVO boardVO = this.boardService.get(bno);
		model.addAttribute("boardVO", boardVO);
		/* return "/board/list"; */
		
		//return "//board/get" - > get.jsp //보이드라 줘서 겟매핑의 주소와 같다 
	}
	
	
	// /board/get?
		//[3]
		@GetMapping(value = "remove")
		public String remove(Model model, @RequestParam("bno") Long bno, RedirectAttributes rttr) { //보이드면 요청url과 같음
			log.info("😂 BoardController.remove()...GET bno : " + bno);
			if(this.boardService.remove(bno)) {
				rttr.addFlashAttribute("result", "REMOVE_SUCCEED"); //1회성으로.
				rttr.addFlashAttribute("bno", bno); // ?bno=6
				
				
			};
			
			return "redirect:/board/list"; 
			
			//return "//board/get" - > get.jsp //보이드라 줘서 겟매핑의 주소와 같다 
		}	
		
	
		//[2-2]/board/register + POST 수정 요청: 컨트롤러 메서드 선언
		@PostMapping(value = "modify")
		public String modify(BoardVO boardVO, RedirectAttributes rttr) { //커맨드객체 파라미터
			log.info("😂 BoardController.modify()...POST");
			log.info("~~~" + boardVO);
			//포워딩
			if(this.boardService.modify(boardVO)) {
				rttr.addFlashAttribute("result", "SUCCESS");
			}
			
			/* rttr.addAttribute("result", boardVO.getBno()); list.jsp?result=*/
			rttr.addAttribute("bno", boardVO.getBno());
			
			//리다이렉트가 될 때 전달되기 위한 파라미터를 갖고간다. 
			//리다이렉트 
			return "redirect:/board/get";
		}
	
}
