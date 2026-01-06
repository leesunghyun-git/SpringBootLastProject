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
	@Override
	public List<JejuVO> jejuFindData(Map map) {
		// TODO Auto-generated method stub
		return jMapper.jejuFindData(map);
	}
	@Override
	public int jejuFindTotalPage(Map map) {
		// TODO Auto-generated method stub
		return jMapper.jejuFindTotalPage(map);
	}
	@Override
	public List<JejuVO> jejuTop4Data() {
		// TODO Auto-generated method stub
		return jMapper.jejuTop4Data();
	}
	@Override
	public JejuVO jejuAttractionDetailData(int contentid) {
		// TODO Auto-generated method stub
		jMapper.jejuHitIncrement(contentid);
		return jMapper.jejuAttractionDetailData(contentid);
	}
	@Override
	public JejuVO jejuCultureDetailData(int contentid) {
		// TODO Auto-generated method stub
		jMapper.jejuHitIncrement(contentid);
		return jMapper.jejuCultureDetailData(contentid);
	}
	@Override
	public JejuVO jejuFestivalDetailData(int contentid) {
		// TODO Auto-generated method stub
		jMapper.jejuHitIncrement(contentid);
		return jMapper.jejuFestivalDetailData(contentid);
	}
	@Override
	public JejuVO jejuStayDetailData(int contentid) {
		// TODO Auto-generated method stub
		jMapper.jejuHitIncrement(contentid);
		return jMapper.jejuStayDetailData(contentid);
	}
	@Override
	public JejuVO jejuShoppingDetailData(int contentid) {
		// TODO Auto-generated method stub
		jMapper.jejuHitIncrement(contentid);
		return jMapper.jejuShoppingDetailData(contentid);
	}
	@Override
	public JejuVO jejuFoodDetailData(int contentid) {
		// TODO Auto-generated method stub
		jMapper.jejuHitIncrement(contentid);
		return jMapper.jejuFoodDetailData(contentid);
	}
	
}
