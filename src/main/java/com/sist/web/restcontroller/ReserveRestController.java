package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;
@RestController
@RequiredArgsConstructor
public class ReserveRestController {
	private final ReserveService rService;
	private final String[] times= {
		"09:00" , "10:00" , "11:00", "12:00" ,"12:30","13:00","13:30",
		"14:00","15:00","16:00","17:00","18:00","18:30","19:00","20:00","20:30","21:00","22:00"
	};
	public List<String> timeRand() {
	      List<String> res=new ArrayList<String>();
	      int[] com=new int[(int)(Math.random()*6)+5];
	      boolean bClick=false;
	      int su=0;
	      for(int i=0;i<com.length;i++)
	      {
	         bClick=true;
	         while(bClick)
	         {
	            su=(int)(Math.random()*18);
	            bClick=false;
	            for(int j=0;j<i;j++)
	            {
	               if(com[j]==su)
	               {
	                  bClick=true;
	                  break;
	               }
	            }
	         }
	         com[i]=su;
	      }
	      Arrays.sort(com);
	      for(int i=0;i<com.length;i++)
	      {
	         res.add(times[com[i]]);
	      }
	      return res;
	   }
	@GetMapping("/reserve/food_list_vue/")
	public ResponseEntity<Map> food_list_vue(@RequestParam("address")String address, @RequestParam("page")int page)
	{
		Map map = new HashMap();
		try {
			map.put("address", address);
			map.put("start",(page-1)*10);
			List <SeoulVO> list = rService.seoulReserveData(map);
			int totalPage = rService.seoulReserveTotalData(address);
			map=new HashMap();
			map.put("list", list);
			map.put("totalPage", totalPage);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	/*
		Vue
			=> mounted => onMounted(()=>{})
			=> watch => 설정된 데이터 변경시에만 호출
			=> computed => 완성된 데이터
			=> getters / actions / state
	*/
	@GetMapping("/reserve/time_list_vue/")
	public ResponseEntity<Map> time_list_vue()
	{
		Map map = new HashMap();
		try {
			List<String> list = timeRand();
			map.put("list", list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	@PostMapping("/reserve/insert_vue/")
	public ResponseEntity<String> reserve_insert(@RequestBody ReserveVO vo,HttpSession session)
	{
		String res="NO";
		try {
			String id = (String)session.getAttribute("userid");
			vo.setId(id);
			res= rService.reserveInsert(vo);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@GetMapping("/reserve/reserve_first/")
	public ResponseEntity<SeoulVO> reserve_first(@RequestParam("cno")int cno)
	{
		SeoulVO vo = null;
		try {
			vo = rService.reserveFirstData(cno);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
		
		
	}
	
}
