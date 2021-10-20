package com.ssafy.guestbook.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.GuestBookService;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService;

	@GetMapping("/register")
	public String register() {
		return "guestbook/write";
	}

	@PostMapping("/register")
	public String register(GuestBookDto guestBookDto, Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		guestBookDto.setUserId(memberDto.getUserId());
		try {
			guestBookService.registerArticle(guestBookDto);
			return "redirect:/guestbook/list?pg=1&key=&word=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","글작성 처리중 문제발생!!!");
			return "error/error.jsp";
		}
	}

	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) {
		ModelAndView mav = new ModelAndView();

		String spp = map.get("spp"); // size per page(페이지당 글갯수)
		map.put("spp", spp != null ? spp : "10");
		try {
			List<GuestBookDto> list = guestBookService.listArticle(map);
			mav.addObject("articles", list);
			mav.setViewName("guestbook/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "글목록 출력 중 문제 발생!!!");
			mav.setViewName("error/error");
		}
		return mav;
	}
	
	@GetMapping("/modify")
	public ModelAndView modify(@RequestParam("articleno") int articleNo) {
		ModelAndView mav = new ModelAndView();
		try {
			GuestBookDto guestBookDto = guestBookService.getArticle(articleNo);
			mav.addObject("article",guestBookDto);
			mav.setViewName("guestbook/modify");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "글얻기 중 문제 발생!!!");
			mav.setViewName("error/error");
		}
		return mav;
	}
	
	@PostMapping("/modify")
	public String modify(GuestBookDto guestBookDto, Model model) {
		try {
			guestBookService.updateArticle(guestBookDto);
//			model.addAttribute("msg","글 수정 성공!!!");
			return "redirect:/guestbook/list?pg=1&key=&word=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","글수정 처리중 문제발생!!!");
			return "error/error.jsp";
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("articleno") int articleNo, Model model) {
		try {
			guestBookService.deleteArticle(articleNo);
//			model.addAttribute("msg","글 삭제 성공!!!");
			return "redirect:/guestbook/list?pg=1&key=&word=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","글삭제 처리중 문제발생!!!");
			return "error/error.jsp";
		}
	}
	
}
