package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.MemberService;

import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;
@RestController
@RequiredArgsConstructor
public class MemberRestController {
	private final MemberService mService;
	
	@GetMapping("/member/idcheck_vue/")
	public ResponseEntity<Integer> member_idCheck_vue(@RequestParam("userid")String userid)
	{
		int count = 0;
		try {
			count = mService.idCheck(userid);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(count,HttpStatus.OK);
	}
}
