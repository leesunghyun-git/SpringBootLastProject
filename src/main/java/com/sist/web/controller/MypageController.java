package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
	
	@GetMapping("/mypage/main")
	public String mypage_main(Model model) {
		
		model.addAttribute("curCat", "mypage");
		model.addAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
		model.addAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "main/main";
	}
}
