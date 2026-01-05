package com.sist.web.service;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.*;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SeoulServiceImpl implements SeoulService{
	// @Autowired
	private final SeoulMapper sMapper;
	/*
	 * @Autowired
	 * public SeoulServiceImpl (SeoulMapper sMapper)
	 * {
	 * 	this.sMapper = sMapper;
	 * }
	 */
	@Override
	public List<SeoulVO> seoulListData(Map map) {
		// TODO Auto-generated method stub
		return sMapper.seoulListData(map);
	}
	@Override
	public int seoulTotalPage(int contenttype) {
		// TODO Auto-generated method stub
		return sMapper.seoulTotalPage(contenttype);
	}
	@Override
	public SeoulVO seoulAttractionDetailData(int contentid) {
	// TODO Auto-generated method stub
		sMapper.seoulHitIncrement(contentid);
		return sMapper.seoulAttractionDetailData(contentid);
	}
	
	
}
