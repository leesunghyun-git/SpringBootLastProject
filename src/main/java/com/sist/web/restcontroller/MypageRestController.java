package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageRestController {
	private final ReserveService rService;
	
	public List<ReserveVO> mypage_reserve_commons(HttpSession session){
		String id = (String)session.getAttribute("userid");
		List<ReserveVO> list = rService.reserveListData(id);
		return list;
	}
	
	@GetMapping("/mypage/reserve_list/")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_list(HttpSession session){
		List<ReserveVO> list = new ArrayList<>();
		try {
			list = mypage_reserve_commons(session);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/mypage/reserve_cancel_vue/")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_cancel_OK(@RequestParam("no")int no,HttpSession session){
		List<ReserveVO> list = new ArrayList<>();
		try {
			rService.reserveUserCancel(no);
			list= mypage_reserve_commons(session);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/mypage/reserve_detail_vue/")
	public ResponseEntity<ReserveVO> reserve_detail(@RequestParam("no")int no)
	{
		ReserveVO vo = null;
		try {
			vo = rService.reserveDetailData(no);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}	
	
}
