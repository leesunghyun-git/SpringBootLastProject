package com.sist.web.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.vo.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;

@Controller
@RequiredArgsConstructor
public class SeoulController {
	private final SeoulService sService;

	@GetMapping("/seoul/list")
	public String seoul_location(@RequestParam(name = "page", required = false) String page,@RequestParam("contenttype")int contenttype, Model model) {
		if (page == null)
			page = "1";
		int curPage = Integer.parseInt(page);
		int start = (curPage - 1) * 12;
		Map map=new HashMap();
		map.put("start", start);
		map.put("contenttype", contenttype);
		List<SeoulVO> list = sService.seoulListData(map);
		int totalPage = sService.seoulTotalPage(contenttype);
		final int BLOCK = 10;
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		if (endPage > totalPage)
			endPage = totalPage;
		 for(SeoulVO vo:list)
		 {
			 String[] addrs=vo.getAddress().split(" ");
			 vo.setAddress(addrs[0]+" "+addrs[1]+" "+addrs[2]);
		 }
		String name="";
		if(contenttype==12) name="서울 관광지";
		else if(contenttype==14) name="서울 문화시설";
		else if(contenttype==15) name="서울 축제 & 공연";
		else if(contenttype==32) name="서울 숙박";
		else if(contenttype==38) name="서울 쇼핑";
		else if(contenttype==39) name="서울 음식";
		model.addAttribute("name", name);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("contenttype", contenttype);
		model.addAttribute("curCat", "seoul");
		model.addAttribute("main_jsp", "../seoul/list.jsp");
		return "main/main";
	}
	/*
	 * 	sendRedirect : RedirectAttributes
	 *  forward : Model (HttpServletRequest)
	 * 
	 */
	@GetMapping("/seoul/detail_before")
	public String seoul_detail_before(@RequestParam("contentid")int contentid,@RequestParam("contenttype")int contenttype,HttpServletResponse response,RedirectAttributes ra,HttpServletRequest request)
	{
		Cookie cookie=new Cookie("seoul_"+contentid,String.valueOf(contentid));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("contentid", contentid);
		ra.addAttribute("contenttype", contenttype);
		return "redirect:/seoul/detail";
	}
	@GetMapping("/seoul/detail")
	public String seoul_detail(@RequestParam("contentid")int contentid,@RequestParam("contenttype")int contenttype,Model model)
	{
		SeoulVO vo = new SeoulVO();
		String jsp = "../seoul/";
		if(contenttype==12)
		{
			vo=sService.seoulAttractionDetailData(contentid);
			jsp=jsp+"attraction.jsp";
		}
		else if(contenttype==14)
		{
			vo=sService.seoulCultureDetailData(contentid);
			jsp=jsp+"culture.jsp";
		}
		else if(contenttype==15)
		{
			vo=sService.seoulFestivalDetailData(contentid);
			jsp=jsp+"festival.jsp";
		}
		else if(contenttype==32)
		{
			vo=sService.seoulStayDetailData(contentid);
			jsp=jsp+"stay.jsp";
		}
		else if(contenttype==38)
		{
			vo=sService.seoulShoppingDetailData(contentid);
			jsp=jsp+"shopping.jsp";
		}
		else if(contenttype==39)
		{
			vo=sService.seoulFoodDetailData(contentid);
			jsp=jsp+"foodstore.jsp";
		}
		model.addAttribute("vo", vo);
		model.addAttribute("curCat", "seoul");
		model.addAttribute("main_jsp", jsp);
		return "main/main";
	}
	@GetMapping("/seoul/find")
	public String seoul_find(Model model) {
		
		model.addAttribute("curCat", "search");
		model.addAttribute("main_jsp", "../seoul/find.jsp");
		return "main/main";
	}
	
}
