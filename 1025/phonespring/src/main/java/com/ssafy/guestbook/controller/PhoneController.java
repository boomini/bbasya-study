package com.ssafy.guestbook.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.PhoneDto;
import com.ssafy.guestbook.model.service.PhoneService;

@Controller
@RequestMapping("/guestbook")
public class PhoneController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private PhoneService phoneService;
	
	@GetMapping("/register")
	public String register() {
		return "guestbook/write";
	}

	@PostMapping("/register")
	public String register(PhoneDto phoneDto, Model model, HttpSession session) throws Exception {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		try {
		phoneService.registerArticle(phoneDto);
		return "redirect:/guestbook/sucess.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "저장 중 오류가 발생했습니다.");
//			return "error/error";
//		}
	}

	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) {
		ModelAndView mav = new ModelAndView();

		String spp = map.get("spp"); // size per page(페이지당 글갯수)
		map.put("spp", spp != null ? spp : "10");
		try {
			List<PhoneDto> list = phoneService.listArticle(map);
			mav.addObject("articles", list);
			mav.setViewName("phone/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "글 목록 출력 중 오류가 발생했습니다.");
			mav.setViewName("error/error");
		}
		return mav;
	}

//	@GetMapping("/modify")
//	public ModelAndView modify(@RequestParam("num") String num) {
//		ModelAndView mav = new ModelAndView();
//		try {
//			PhoneDto phoneDto = phoneService.getArticle(num);
//			mav.addObject("article", phoneDto);
//			mav.setViewName("phone/modify");
//		} catch (Exception e) {
//			e.printStackTrace();
//			mav.addObject("msg", "글얻기 중 문제 발생!!!");
//			mav.setViewName("error/error");
//		}
//		return mav;
//	}
//
//	@PostMapping("/modify")
//	public String modify(PhoneDto phoneDto, Model model) {
//		try {
//			phoneService.updateArticle(phoneDto);
////			model.addAttribute("msg", "글 수정 성공!!!");
//			return "redirect:/phone/list?pg=1&key=&word=";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글수정 처리중 문제발생!!!");
//			return "error/error";
//		}
//	}

	@GetMapping("/delete")
	public String delete(@RequestParam("num") String num, Model model, RedirectAttributes redirectAttributes) {
		try {
			phoneService.deleteArticle(num);
			redirectAttributes.addAttribute("msg", "글 삭제 성공!!!");
//			redirectAttributes.addFlashAttribute("msg", "글 삭제 성공!!!");
			return "redirect:/guestbook/list?pg=1&key=&word=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "삭제 중 오류가 발생했습니다.");
			return "error/error";
		}
	}
}
