package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.BusanService;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@RequestMapping("/busan/")
@RestController
@RequiredArgsConstructor
public class BusanRestController {
	private final BusanService bService;
	
	@GetMapping("find_vue/")
	public ResponseEntity<Map> busan_find_vue(@RequestParam("page")int page,@RequestParam("address")String address)
	{
		Map map = new HashMap();
		try {
			
			int start = ((page-1)*12);
			map.put("start", start);
			map.put("address", address);
			List<BusanVO> list = bService.busanFindData(map);
			map = new HashMap();
			final int BLOCK = 10;
			int totalPage=bService.busanFindTotalPage(address);
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalPage)
				endPage=totalPage;
			 for(BusanVO vo:list)
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
