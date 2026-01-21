package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.NoticeMapper;
import com.sist.web.vo.NoticeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
	private final NoticeMapper nMapper;
	@Override

		public void noticeInsert(NoticeVO vo) {
			// TODO Auto-generated method stub
			nMapper.noticeInsert(vo);
		}
	@Override
	public List<NoticeVO> noticeListData(int start) {
		// TODO Auto-generated met
		return nMapper.noticeListData(start);
	}
	@Override
	public int noticeTotalPage() {
		// TODO Auto-generated method stub
		return nMapper.noticeTotalPage();
	}
	@Override
	public NoticeVO noticeAdminDetailData(int no) {
		// TODO Auto-generated method stub
		return nMapper.noticeDetailData(no);
	}
	@Override
	public NoticeVO noticeUserDetailData(int no) {
		// TODO Auto-generated method stub
		nMapper.noticeHitIncrement(no);
		return nMapper.noticeDetailData(no);
	}
	@Override
	public void noticeDeleteData(int no) {
		// TODO Auto-generated method stub
		nMapper.noticeDeleteData(no);
	}
	@Override
	public NoticeVO noticeFileData(int no) {
		// TODO Auto-generated method stub
		return nMapper.noticeFileData(no);
	}
	@Override
	public void noticeUpdate(NoticeVO vo) {
		// TODO Auto-generated method stub
		nMapper.noticeUpdate(vo);
	}
}

