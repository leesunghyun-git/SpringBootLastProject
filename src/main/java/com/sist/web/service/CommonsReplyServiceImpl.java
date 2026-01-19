package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import com.sist.web.mapper.*;
import com.sist.web.vo.CommonsReplyVO;
@Service
@RequiredArgsConstructor
/*
 * 		Service : DataBase , OpenAPI , AI => 요청처리 => Back-End의 중심
 * 		=> Security : BI
 * 		
 * 		Repository : 오라클 / MySQL만 연동
 * 		--------------------------------
 * 		Controller : 결과값을 받아서 브라우저로 전송
 * 			| Front-End => 조립 => 결과값 추출
 * 
 * 		Component : 기타
 * 			| AOP / Task / Batch
 * 
 * 		= Controller : Router 역할
 * 		= RestController : 데이터 전송
 * 		
 * 		Server ======= Client
 * 		  |
 * 		순수하게 서버 역할만 ... (화면 제어가 없다) => Front에서 자체 처리
 * 											   router => Vue/React
 * 
 * 
 * 		var / val fun 함수명():String => data class Sawon(int age)
 * 
 */
public class CommonsReplyServiceImpl implements CommonsReplyService{
	private final CommonsReplyMapper crMapper;
	@Override
	public void commonsReplyInsert(CommonsReplyVO vo) {
		// TODO Auto-generated method stub
		crMapper.commonsReplyInsert(vo);
	}
	@Override
	public List<CommonsReplyVO> commonsReplyListData(int cno, int start) {
		// TODO Auto-generated method stub
		return crMapper.commonsReplyListData(cno,start);
	}
	@Override
	public int commonsReplyTotalPage(int cno) {
		// TODO Auto-generated method stub
		return crMapper.commonsReplyTotalPage(cno);
	}
	@Override
	public int commonsCount(int cno) {
		// TODO Auto-generated method stub
		return crMapper.commonsCount(cno);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void commonsDelete(int no) {
		// TODO Auto-generated method stub
		CommonsReplyVO vo = crMapper.commonsInfoData(no);
		if(vo.getGroup_step()==0)
		{
			crMapper.commonsAllDelete(vo.getGroup_id());
		}
		else {
			crMapper.commonsDelete(no);
		}
	}
	@Override
	public void commonsUpdate(CommonsReplyVO vo) {
		// TODO Auto-generated method stub
		crMapper.commonsUpdate(vo);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void commonsReplyReplyInsert(CommonsReplyVO vo) {
		// TODO Auto-generated method stub
		int pno = vo.getNo();
		CommonsReplyVO pvo=crMapper.commonsReplyParentData(pno);
		crMapper.commonsGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		
		crMapper.commonsReplyReplyInsert(vo);
		crMapper.commonsDepthIncrement(pno);
	}
}
