package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.BusanService;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class BusanController {
	private final BusanService bService;
	@GetMapping("/busan/list")
	public String seoul_location(@RequestParam(name = "page", required = false) String page,@RequestParam("contenttype")int contenttype, Model model) {
		if (page == null)
			page = "1";
		int curPage = Integer.parseInt(page);
		int start = (curPage - 1) * 12;
		Map map=new HashMap();
		map.put("start", start);
		map.put("contenttype", contenttype);
		List<BusanVO> list = bService.busanListData(map);
		int totalPage = bService.busanTotalPage(contenttype);
		final int BLOCK = 10;
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		if (endPage > totalPage)
			endPage = totalPage;
		 for(BusanVO vo:list)
		 {
			 String[] addrs=vo.getAddress().split(" ");
			 vo.setAddress(addrs[1]+" "+addrs[2]);
		 }
		String name="";
		if(contenttype==12) name="부산 관광지";
		else if(contenttype==14) name="부산 문화시설";
		else if(contenttype==15) name="부산 축제 & 공연";
		else if(contenttype==32) name="부산 숙박";
		else if(contenttype==38) name="부산 쇼핑";
		else if(contenttype==39) name="부산 음식";
		model.addAttribute("name", name);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("contenttype", contenttype);
		model.addAttribute("curCat", "busan");
		model.addAttribute("main_jsp", "../busan/list.jsp");
		return "main/main";
	}
}
