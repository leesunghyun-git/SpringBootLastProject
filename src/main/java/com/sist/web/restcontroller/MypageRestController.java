package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageRestController {
	private final ReserveService rService;
	
	@GetMapping("/mypage/reserve_list/")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_list(HttpSession session){
		List<ReserveVO> list = new ArrayList<>();
		try {
			String id = (String)session.getAttribute("userid");
			list= rService.reserveListData(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
}
