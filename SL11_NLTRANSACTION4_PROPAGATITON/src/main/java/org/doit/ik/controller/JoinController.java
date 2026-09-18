package org.doit.ik.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.MemberDao;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

// 공지 사항
@Controller
@RequestMapping("/joinus")
@RequiredArgsConstructor
@Log4j
public class JoinController {

   private final MemberDao memberDao;
   
   // 1. 회원가입 : /joinus/join.htm -> /joinus/join.jsp
   @GetMapping("/join.htm")
   public String join() throws Exception{
      System.out.println(">😂😁😊 JoinController.join()... : GET");
      return "join.jsp";
   }
   /* 의미 알아보기
   // [2] p334 요청 파라미터의 값 변환처리
      @InitBinder
      public void initBinder(WebDataBinder binder) { 
         
          binder.registerCustomEditor(String.class, "habit", new PropertyEditorSupport() {
              @Override
              public void setAsText(String text) {
                  setValue(text);
              }

              @Override
              public void setValue(Object value) {
                  if (value instanceof String[]) {
                      String joined = String.join(",", (String[]) value);
                      super.setValue(joined);
                  } else {
                      super.setValue(value);
                  }
              }
          });
      }
    */   
   // 1-1. 회원가입 등록 : /joinus/join.htm + POST -> 리다이렉트 /joinus/index.htm
   @PostMapping("/join.htm")
   public String join(MemberVO memberVO, RedirectAttributes rttr, @RequestParam("year") String year,@RequestParam("month") String month, @RequestParam("day") String day) throws Exception{
      System.out.println(">😂😁😊 JoinController.join()... : POST");
      
      String birth = year + "-" + month + "-"   + day;
      memberVO.setBirth(birth);
      
      int rowCount = this.memberDao.insert(memberVO);
      rttr.addFlashAttribute("result",rowCount); // 처리는 아직안했음
      return "redirect:../index.htm";
   }
   
   // 2. 로그인   : /joinus/login.htm -> /joinus/login.jsp
   @GetMapping("/login.htm")
   public String login() throws Exception{
      System.out.println(">😂😁😊 JoinController.login()...");
      return "login.jsp";
   }
   
} // class























