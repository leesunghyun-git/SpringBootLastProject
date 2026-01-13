package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
// RouterController => Pinia 에서 router
// 화면만 변경 => 실제 RestController
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService bService;
	
	@GetMapping("/board/list")
	// 사용자가 어떤 값을 보내야 하는지
	public String board_list(@RequestParam(name="page",required=false)String page,Model model)
	{
		if(page == null)
			page = "1";
		int curPage = Integer.parseInt(page);
		int start = (curPage-1)*10;
		List<BoardVO> list = bService.boardListData(start);
		int totalPage = bService.boardTotalPage();
		int startPage = ((curPage-1)/10*10)+1;
		int endPage = ((curPage-1)/10*10)+10;
		if(endPage>totalPage)
			endPage=totalPage;
		model.addAttribute("curPage", curPage);
		model.addAttribute("list", list);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("main_jsp", "../board/list.jsp");
		model.addAttribute("curCat", "board");
		model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "main/main";
	}
	@GetMapping("/board/insert")
	public String board_insert(Model model)
	{
		model.addAttribute("main_jsp", "../board/insert.jsp");
		model.addAttribute("curCat", "board");
		return "main/main";
	}
	/*
	 * 	@RequestParam => 단일 값
	 *  @ModelAttribute => VO 단위
	 * 	@RequestBody => @RestController
	 * 					JSON => 객체변환
	 * 	----------------------- 사용자 전송한 값을 받는 경우
	 * 	브라우저 전송
	 * 	Model / HttpServletRequest
	 * 	  |				|
	 * 	 일반		interceptor / aop / task
	 */
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(@ModelAttribute BoardVO vo)
	{
		bService.boardInsert(vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/detail")
	public String board_detail(@RequestParam("no")int no,Model model)
	{
		BoardVO vo = bService.boardDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/detail.jsp");
		model.addAttribute("curCat", "board");
		return "main/main";
	}
	@GetMapping("/board/update")
	public String board_update(@RequestParam("no")int no,Model model)
	{
		BoardVO vo = bService.boardUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/update.jsp");
		model.addAttribute("curCat", "board");
		return "main/main";
	}
	@PostMapping(value="/board/update_ok",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String board_update(@ModelAttribute BoardVO vo)
	{
		String res="";
		String result = bService.boardUpdate(vo);
		if(result.equals("YES"))
		{
			res = "<script>"
					+ "location.href='/board/detail?no="+vo.getNo()+"'"
					+"</script>";
		}
		else {
			res = "<script>"
				+ "alert('비밀번호가 틀립니다');"
				+"history.back();"
				+"</script>";
		}
		
		return res;
	}
	@GetMapping("/board/delete")
	public String board_delete(@RequestParam("no")int no, Model model)
	{
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../board/delete.jsp");
		model.addAttribute("curCat", "board");
		return "main/main";
	}
	@PostMapping(value="/board/delete_ok",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String board_delete(@ModelAttribute BoardVO vo)
	{
		String res="";
		String result = bService.boardDelete(vo);
		if(result.equals("YES"))
		{
			res = "<script>"
					+ "location.href='/board/list'"
					+"</script>";
		}
		else {
			res = "<script>"
				+ "alert('비밀번호가 틀립니다');"
				+"history.back();"
				+"</script>";
		}
		return res;
	}
	

}
