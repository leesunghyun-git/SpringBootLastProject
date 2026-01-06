package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.BusanService;
import com.sist.web.service.JejuService;
import com.sist.web.service.SeoulService;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.JejuVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;
/*
 * 	1. 일반 보안 => 자동 로그인 => 프로시저 / 트리거 / 람다식
 *  2. JWT => 카카오 / 구글 로그인
 *  3. 소켓 : 실시간 : 그룹 / 1:1 => Spring AI (챗봇)
 *  4. 실시간 메세지 전송 : kafka / batch
 *  5. MSA : React => NodeJS => JPA / MySQL
 *  6. => CI/CD => 통합 => Spring Data => elasticSearch / Redis
 * 
 *  AI
 */
@Controller
@RequiredArgsConstructor
public class MainController {
	private final SeoulService sService;
	private final BusanService bService;
	private final JejuService jService;
	
	@GetMapping("/main")
	public String main_main(Model model)
	{
		List<JejuVO> jList=jService.jejuTop4Data();
		for(JejuVO jvo:jList)
		{
			String[] datas=jvo.getAddress().split(" ");
			jvo.setAddress(datas[1]+" "+datas[2]);
		}
		List<BusanVO> bList=bService.busanTop4Data();
		for(BusanVO bvo:bList)
		{
			String[] datas=bvo.getAddress().split(" ");
			bvo.setAddress(datas[1]+" "+datas[2]);
		}
		List<SeoulVO> sList=sService.seoulTop5Data();
		for(SeoulVO svo:sList)
		{
			String[] datas=svo.getAddress().split(" ");
			svo.setAddress(datas[1]+" "+datas[2]);
		}
		model.addAttribute("jList", jList);
		model.addAttribute("bList", bList);
		model.addAttribute("sList", sList);
		model.addAttribute("curCat", "home");
		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
}
