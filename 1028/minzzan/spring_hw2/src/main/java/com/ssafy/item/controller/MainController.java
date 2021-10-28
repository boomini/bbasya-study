package com.ssafy.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.item.model.ItemDto;
import com.ssafy.item.model.service.ItemService;

@Controller
public class MainController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "item/register";
	}

	@PostMapping("/register")
	public String register(ItemDto itemDto) throws Exception {
		itemService.registerItem(itemDto);
		return "item/result";
	}
	
	@GetMapping("/list")
	public String list(Model model) throws Exception {
		List<ItemDto> list = itemService.listItem();
		model.addAttribute("items", list);
		return "item/list";
	}
	
	@GetMapping("/detail")
	public String detail(String item_code, Model model) throws Exception {
		ItemDto itemDto = itemService.getItem(item_code);
		model.addAttribute("item", itemDto);
		return "item/detail";
	}
	
	@GetMapping("/delete")
	public String delete(String item_code) throws Exception {
		itemService.deleteItem(item_code);
		return "redirect:/list";
	}
}
