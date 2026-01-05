package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sist.web.mapper.JejuMapper;
import com.sist.web.vo.JejuVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JejuServiceImpl implements JejuService{
	private final JejuMapper jMapper;
	
	@Override
	public List<JejuVO> jejuListData(Map map) {
		// TODO Auto-generated method stub
		return jMapper.jejuListData(map);
	}
	@Override
	public int jejuTotalPage(int contenttype) {
		// TODO Auto-generated method stub
		return jMapper.jejuTotalPage(contenttype);
	}
}
