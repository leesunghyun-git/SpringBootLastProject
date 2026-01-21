package com.sist.web.service;

import java.util.List;


import com.sist.web.vo.NoticeVO;

public interface NoticeService {
	public List<NoticeVO> noticeListData(int start);
	int noticeTotalPage();
	void noticeInsert(NoticeVO vo);
	NoticeVO noticeAdminDetailData(int no);
	NoticeVO noticeUserDetailData(int no);
	NoticeVO noticeFileData(int no);
	void noticeDeleteData(int no);
	void noticeUpdate(NoticeVO vo);
}
