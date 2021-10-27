package com.ssafy.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.test.model.PhoneDto;
import com.ssafy.test.model.service.PhoneService;

@Controller
public class MainController {

	@Autowired
	private PhoneService phoneService;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "phone/PhoneReg";
	}

	@PostMapping("/register")
	public String register(PhoneDto phoneDto) throws Exception {
		phoneService.registerPhone(phoneDto);
		return "phone/result";
	}
	
	@GetMapping("/list")
	public String list(Model model) throws Exception {
		List<PhoneDto> list = phoneService.listPhone();
		model.addAttribute("phones", list);
		return "phone/list";
	}
	
	@GetMapping("/detail")
	public String detail(String num, Model model) throws Exception {
		PhoneDto phoneDto = phoneService.getPhone(num);
		model.addAttribute("phone", phoneDto);
		return "phone/detail";
	}
	
	@GetMapping("/delete")
	public String delete(String num) throws Exception {
		phoneService.deletePhone(num);
		return "redirect:/list";
	}
}
