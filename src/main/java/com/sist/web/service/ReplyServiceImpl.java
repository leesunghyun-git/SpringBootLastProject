package com.sist.web.service;
import java.util.*;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.*;
import com.sist.web.vo.MemberVO;
import com.sist.web.vo.ReplyVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	private final BoardMapper bMapper;
	private final ReplyMapper rMapper;
	private final MemberMapper mMapper;

	@Override
	public void replyInsert(ReplyVO vo) {
		// TODO Auto-generated method stub
		MemberVO uvo = mMapper.memberInfoData(vo.getId());
		vo.setName(uvo.getUsername());
		vo.setSex(uvo.getSex());
		rMapper.replyInsert(vo);
		int replycount = rMapper.replyCount(vo.getBno());
		bMapper.boardReplyUpdate(vo.getBno(), replycount);
	}
	@Override
	public List<ReplyVO> replyListData(int bno) {
		// TODO Auto-generated method stub
		return rMapper.replyListData(bno);
	}
	@Override
	public int replyCount(int bno) {
		// TODO Auto-generated method stub
		return rMapper.replyCount(bno);
	}
	@Override
	public void replyDelete(int no,int bno) {
		// TODO Auto-generated method stub
		rMapper.replyDelete(no);
		int replycount = rMapper.replyCount(bno);
		bMapper.boardReplyUpdate(bno, replycount);
	}
	@Override
	public void replyUpdate(ReplyVO vo) {
		// TODO Auto-generated method stub
		rMapper.replyUpdate(vo);
	}

}
