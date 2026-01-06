package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/seoul/")
public class SeoulRestController {
	private final SeoulService sService;
	// 웹 개발 => SpringFramework => security
	// => AI : webSocket / kafka / batch / tast
	// 	 ---- Vue (Nuxt) / React (Next)
	@GetMapping("find_vue/")
	public ResponseEntity<Map> seoul_find_vue(@RequestParam("page")int page,@RequestParam("address")String address)
	{
		Map map = new HashMap();
		try {
			
			int start = ((page-1)*12);
			map.put("start", start);
			map.put("address", address);
			List<SeoulVO> list = sService.seoulFindData(map);
			map = new HashMap();
			final int BLOCK = 10;
			int totalPage=sService.seoulFindTotalPage(address);
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalPage)
				endPage=totalPage;
			 for(SeoulVO vo:list)
			 {
				 String[] addrs=vo.getAddress().split(" ");
				 vo.setAddress(addrs[0]+" "+addrs[1]+" "+addrs[2]);
			 }
			map.put("list", list);
			map.put("totalPage", totalPage);
			map.put("endPage",endPage);
			map.put("startPage", startPage);
			map.put("curPage",page);
			map.put("address", address);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
