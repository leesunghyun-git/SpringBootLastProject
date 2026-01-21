package com.sist.web.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.ReserveService;
import com.sist.web.vo.ReserveVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminRestController {
	private final ReserveService rService;
	private final SimpMessagingTemplate template;
	public List<ReserveVO> commons_reserve()
	{
		List<ReserveVO> list= rService.reserveAdminListData();
		return list;
	}
	
	
	@GetMapping("/admin/reserve_list/")
	public ResponseEntity<List<ReserveVO>> admin_reserve_list(){
		List<ReserveVO> list = new ArrayList<>();
		try {
			list= commons_reserve();
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ReserveVO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/admin/reserve_ok_vue/")
	public ResponseEntity<List<ReserveVO>> admin_reserve_ok(@RequestParam("no") int no,@RequestParam("id")String id){
		List<ReserveVO> list = new ArrayList<>();
		try {
			rService.reserveAdminOk(no);
			list= commons_reserve();
			
			template.convertAndSend(
				"/sub/notice/"+id,
				"[😊 예약 승인] 예약이 완료되었습니다."
			);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ReserveVO>>(list,HttpStatus.OK);
	}
	@DeleteMapping("/admin/reserve_delete_vue/")
	public ResponseEntity<List<ReserveVO>> admin_reserve_delete(@RequestParam("no") int no,@RequestParam("id")String id){
		List<ReserveVO> list = new ArrayList<>();
		try {
			rService.reserveAdminDelete(no);
			list= commons_reserve();
			template.convertAndSend(
					"/sub/notice/"+id,
					"[😊 예약 승인] 예약이 취소되었습니다."
				);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ReserveVO>>(list,HttpStatus.OK);
	}
}
