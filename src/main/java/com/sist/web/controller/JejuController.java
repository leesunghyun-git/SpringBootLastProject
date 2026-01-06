package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.BusanService;
import com.sist.web.service.JejuService;
import com.sist.web.vo.JejuVO;
import com.sist.web.vo.SeoulVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class JejuController {
	private final JejuService jService;
	@GetMapping("/jeju/list")
	public String seoul_location(@RequestParam(name = "page", required = false) String page,@RequestParam("contenttype")int contenttype, Model model) {
		if (page == null)
			page = "1";
		int curPage = Integer.parseInt(page);
		int start = (curPage - 1) * 12;
		Map map=new HashMap();
		map.put("start", start);
		map.put("contenttype", contenttype);
		List<JejuVO> list = jService.jejuListData(map);
		int totalPage = jService.jejuTotalPage(contenttype);
		final int BLOCK = 10;
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		if (endPage > totalPage)
			endPage = totalPage;
		 for(JejuVO vo:list)
		 {
			 String[] addrs=vo.getAddress().split(" ");
			 vo.setAddress(addrs[1]+" "+addrs[2]);
		 }
		String name="";
		if(contenttype==12) name="제주 관광지";
		else if(contenttype==14) name="제주 문화시설";
		else if(contenttype==15) name="제주 축제 & 공연";
		else if(contenttype==32) name="제주 숙박";
		else if(contenttype==38) name="제주 쇼핑";
		else if(contenttype==39) name="제주 음식";
		model.addAttribute("name", name);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("contenttype", contenttype);
		model.addAttribute("curCat", "jeju");
		model.addAttribute("main_jsp", "../jeju/list.jsp");
		return "main/main";
	}
	@GetMapping("/jeju/find")
	public String jeju_find(Model model) {
		
		model.addAttribute("curCat", "search");
		model.addAttribute("main_jsp", "../jeju/find.jsp");
		return "main/main";
	}
	@GetMapping("/jeju/detail_before")
	public String jeju_detail_before(@RequestParam("contentid")int contentid,@RequestParam("contenttype")int contenttype,HttpServletResponse response,RedirectAttributes ra,HttpServletRequest request)
	{
		Cookie cookie=new Cookie("jeju_"+contentid,String.valueOf(contentid));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("contentid", contentid);
		ra.addAttribute("contenttype", contenttype);
		return "redirect:/jeju/detail";
	}
	@GetMapping("/jeju/detail")
	public String jeju_detail(@RequestParam("contentid")int contentid,@RequestParam("contenttype")int contenttype,Model model)
	{
		JejuVO vo = new JejuVO();
		String jsp = "../jeju/";
		if(contenttype==12)
		{
			vo=jService.jejuAttractionDetailData(contentid);
			jsp=jsp+"attraction.jsp";
		}
		else if(contenttype==14)
		{
			vo=jService.jejuCultureDetailData(contentid);
			jsp=jsp+"culture.jsp";
		}
		else if(contenttype==15)
		{
			vo=jService.jejuFestivalDetailData(contentid);
			jsp=jsp+"festival.jsp";
		}
		else if(contenttype==32)
		{
			vo=jService.jejuStayDetailData(contentid);
			jsp=jsp+"stay.jsp";
		}
		else if(contenttype==38)
		{
			vo=jService.jejuShoppingDetailData(contentid);
			jsp=jsp+"shopping.jsp";
		}
		else if(contenttype==39)
		{
			vo=jService.jejuFoodDetailData(contentid);
			jsp=jsp+"foodstore.jsp";
		}
		model.addAttribute("vo", vo);
		model.addAttribute("curCat", "jeju");
		model.addAttribute("main_jsp", jsp);
		return "main/main";
	}

}
