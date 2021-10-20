package com.ssafy.guestbook.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {
   
   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

   @Autowired
   private MemberService memberService;
   
   @GetMapping("/register")
   public String register() {
      return "user/join";
   }
   
   @PostMapping("/register")
   public String register(MemberDto memberDto, Model model) {
      logger.debug("memberDto info : {}", memberDto);
      try {
         memberService.registerMember(memberDto);
         return "redirect:/user/login";   // redirect하는 법
      } catch (Exception e) {
         e.printStackTrace();
         model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
         return "error/error";
      }
      
   }
   
   @GetMapping("/login")
   public String login() {
      return "user/login";
   }
   
   // @~ 가 없으면 모델에 담아서 가라는 용도의 모델로 인식되기 때문에, 
   // 클라이언트에서 넘어온 파라미터를 받는 용도임을 알려주기 위해 @RequestParam 명시
   @PostMapping("/login")
   public String login(@RequestParam Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) {
      try {
         MemberDto memberDto = memberService.login(map);
         if(memberDto != null) {
            session.setAttribute("userinfo", memberDto);
            
            // Cookie 설정
            Cookie cookie = new Cookie("ssafy_id", map.get("userId"));
            cookie.setPath("/");
            
            // Cookie를 만들긴 하는데 저장 여부 체크에 따라서
            if("saveok".equals(map.get("idsave"))) {   // 아이디 저장을 체크 했다면 ?
               cookie.setMaxAge(60*60*24*365*40);
            } else {
               cookie.setMaxAge(0);
            }
            
            response.addCookie(cookie);
            return "redirect:/";
            
         } else {   // 로그인이 실패했다면
            // model은 담아서 보낼 용도이로
            model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
            return "user/login";
         }
      } catch (Exception e) {
         e.printStackTrace();
         model.addAttribute("msg", "로그인 중 문제 발생!!!");
         return "error/error";
      }
   }
   
   @GetMapping("/logout")
   public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/";
   }
}