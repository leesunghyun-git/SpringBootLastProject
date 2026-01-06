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

import com.sist.web.service.JejuService;
import com.sist.web.vo.JejuVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jeju/")
public class JejuRestController {
	private final JejuService jService;
	
	@GetMapping("find_vue/")
	public ResponseEntity<Map> jeju_find_vue(@RequestParam("page")int page,@RequestParam("fd")String fd,@RequestParam("selected")int selected)
	{
		Map map = new HashMap();
		try {
			
			int start = ((page-1)*12);
			
			map.put("fd", fd);
			map.put("selected", selected);
			int totalPage=jService.jejuFindTotalPage(map);
			map.put("start", start);
			List<JejuVO> list = jService.jejuFindData(map);
			map = new HashMap();
			final int BLOCK = 5;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalPage)
				endPage=totalPage;
			 for(JejuVO vo:list)
			 {
				 String[] addrs=vo.getAddress().split(" ");
				 vo.setAddress(addrs[0]+" "+addrs[1]+" "+addrs[2]);
			 }
			map.put("list", list);
			map.put("totalPage", totalPage);
			map.put("endPage",endPage);
			map.put("startPage", startPage);
			map.put("curPage",page);
			map.put("fd", fd);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
