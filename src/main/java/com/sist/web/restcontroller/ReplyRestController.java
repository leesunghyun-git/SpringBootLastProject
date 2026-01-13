package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.*;
import com.sist.web.vo.ReplyVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReplyRestController {
	private final ReplyService rService;
	public Map commons(int bno)
	{
		Map map= new HashMap();
		List<ReplyVO> list = rService.replyListData(bno);
		int count=rService.replyCount(bno);
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	@GetMapping("/reply/list_vue/")
	public ResponseEntity<Map> reply_list_vue(@RequestParam("bno")int bno)
	{
		Map map = new HashMap();
		try {
			List<ReplyVO> list = rService.replyListData(bno);
			int count=rService.replyCount(bno);
			map.put("list", list);
			map.put("count", count);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/reply/insert_vue/")
	public ResponseEntity<Map> reply_insert_vue(@RequestBody ReplyVO vo)
	{
		Map map = null;
		try {
			rService.replyInsert(vo);
			map = commons(vo.getBno());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@DeleteMapping("/reply/delete_vue/")
	public ResponseEntity<Map> reply_delete_vue(@RequestParam("no")int no,@RequestParam("bno")int bno)
	{
		Map map = null;
		try {
			rService.replyDelete(no,bno);
			map = commons(bno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PutMapping("/reply/update_vue/")
	public ResponseEntity<Map> reply_update_vue(@RequestBody ReplyVO vo)
	{
		Map map = null;
		try {
			rService.replyUpdate(vo);
			map = commons(vo.getBno());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
