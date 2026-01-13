package com.sist.web.service;

import com.sist.web.vo.MemberVO;

public interface MemberService {
	public void memberInsert(MemberVO vo);
	public int idCheck(String userid);
	public void memberAuthority(String userid);
	public MemberVO memberInfoData(String userid);
}
