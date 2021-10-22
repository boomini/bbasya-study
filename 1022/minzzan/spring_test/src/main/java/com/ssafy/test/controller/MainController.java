package com.ssafy.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.test.service.TestService;

@Controller
public class MainController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/a")
	public String aa() {
		return "index";
	}
}
