package com.sist.web.service;
import java.util.*;
import com.sist.web.vo.*;
public interface ReplyService {
	void replyInsert(ReplyVO vo);
	public List<ReplyVO> replyListData(int bno);
	int replyCount(int bno);
	void replyDelete(int no,int bno);
	void replyUpdate(ReplyVO vo);
}
