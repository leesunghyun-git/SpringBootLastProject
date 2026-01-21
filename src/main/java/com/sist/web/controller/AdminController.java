package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin/main")
	public String admin_main(Model model) {
		
		model.addAttribute("curCat", "adminpage");
		model.addAttribute("admin_jsp", "../admin/admin_home.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	@GetMapping("/admin/reserve")
	public String admin_reserve(Model model) {
		
		model.addAttribute("curCat", "adminpage");
		model.addAttribute("admin_jsp", "../admin/admin_reserve.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}

}
