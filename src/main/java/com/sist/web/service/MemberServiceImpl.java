package com.sist.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.MemberMapper;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper mMapper;
	
	@Override
	public int idCheck(String userid) {
		// TODO Auto-generated method stub
		return mMapper.idCheck(userid);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	// 일괄처리 => 성공 / 실패
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		mMapper.memberInsert(vo);
		
	}
	@Override
	public void memberAuthority(String userid) {
		// TODO Auto-generated method stub
		mMapper.memberAuthority(userid);
	}
	@Override
	public MemberVO memberInfoData(String userid) {
		// TODO Auto-generated method stub
		return mMapper.memberInfoData(userid);
	}
}
