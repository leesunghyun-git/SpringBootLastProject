package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.CommonsReplyVO;

public interface CommonsReplyService {
	public List<CommonsReplyVO> commonsReplyListData(int cno,int start);
	public int commonsReplyTotalPage(int cno);
	void commonsReplyInsert(CommonsReplyVO vo);
	int commonsCount(int cno);
	void commonsDelete(int no);
	void commonsUpdate(CommonsReplyVO vo);
}
