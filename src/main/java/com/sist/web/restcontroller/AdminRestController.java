package com.sist.web.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.ReserveService;
import com.sist.web.vo.ReserveVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminRestController {
	private final ReserveService rService;
	
	@GetMapping("/admin/reserve_list/")
	public ResponseEntity<List<ReserveVO>> admin_reserve_list(HttpSession session){
		List<ReserveVO> list = new ArrayList<>();
		try {
			list= rService.reserveAdminListData();
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ReserveVO>>(list,HttpStatus.OK);
	}
}
