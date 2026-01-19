package com.sist.web.restcontroller;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;

import com.sist.web.service.*;
import java.util.*;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
// 추상화 => ChatModel => GPT / GEN / En / Oll
public class CommonsReplyRestController {
	private final CommonsReplyService crService;
	public Map commons_list(int cno,int page)
	{
		Map map = new HashMap();
		int start = (page-1)*10;
		List<CommonsReplyVO> list =crService.commonsReplyListData(cno, start);
		int totalPage = crService.commonsReplyTotalPage(cno);
		int startPage = ((page-1)/5*5)+1;
		int endPage = ((page-1)/5*5)+5;
		if(endPage>totalPage)
			endPage=totalPage;
		int count = crService.commonsCount(cno);
		map.put("list",list);
		map.put("curPage", page);
		map.put("startPage", startPage);
		map.put("cno", cno);
		map.put("totalPage", totalPage);
		map.put("endPage", endPage);
		map.put("count", count);
		
		return map;
	}
	@GetMapping("/commons/list_vue/")
	public ResponseEntity<Map> commons_list_vue(@RequestParam("cno")int cno,@RequestParam("page")int page)
	{
		Map map = null;
		try {
			map = commons_list(cno, page);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/commons/insert_vue/")
	public ResponseEntity<Map> commons_insert_vue(@RequestBody CommonsReplyVO vo,HttpSession session)
	{
		Map map = null;
		try {
			String id =(String)session.getAttribute("userid");
			String name =(String)session.getAttribute("username");
			String sex =(String)session.getAttribute("sex");
			vo.setId(id);
			vo.setName(name);
			vo.setSex(sex);
			crService.commonsReplyInsert(vo);
			map = commons_list(vo.getCno(), vo.getPage());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@DeleteMapping("/commons/delete_vue/")
	public ResponseEntity<Map> commons_delete_vue(@RequestParam("cno")int cno,@RequestParam("page")int page,@RequestParam("no")int no)
	{
		Map map = null;
		try {
			crService.commonsDelete(no);
			map = commons_list(cno, page);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PutMapping("/commons/update_vue/")
	public ResponseEntity<Map> commons_update_vue(@RequestBody CommonsReplyVO vo)
	{
		Map map = null;
		try {
			crService.commonsUpdate(vo);
			map = commons_list(vo.getCno(), vo.getPage());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/commons/reply_reply/insert_vue/")
	public ResponseEntity<Map> commons_reply_reply_vue(@RequestBody CommonsReplyVO vo,HttpSession session)
	{
		Map map = null;
		try {
			String id =(String)session.getAttribute("userid");
			String name =(String)session.getAttribute("username");
			String sex =(String)session.getAttribute("sex");
			vo.setId(id);
			vo.setName(name);
			vo.setSex(sex);
			crService.commonsReplyReplyInsert(vo);
			map = commons_list(vo.getCno(), vo.getPage());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
		
}
