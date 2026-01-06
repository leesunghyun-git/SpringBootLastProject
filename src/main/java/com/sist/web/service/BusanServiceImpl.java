package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BusanMapper;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService{
	private final BusanMapper bMapper;
	@Override
	public List<BusanVO> busanListData(Map map) {
		// TODO Auto-generated method stub
		return bMapper.busanListData(map);
	}
	@Override
	public int busanTotalPage(int contenttype) {
		// TODO Auto-generated method stub
		return bMapper.busanTotalPage(contenttype);
	}
	@Override
	public List<BusanVO> busanFindData(Map map) {
		// TODO Auto-generated method stub
		return bMapper.busanFindData(map);
	}
	@Override
	public int busanFindTotalPage(String address) {
		// TODO Auto-generated method stub
		return bMapper.busanFindTotalPage(address);
	}
	@Override
	public List<BusanVO> busanTop4Data() {
		// TODO Auto-generated method stub
		return bMapper.busanTop4Data();
	}
	@Override
	public BusanVO busanCultureDetailData(int contentid) {
		// TODO Auto-generated method stub
		bMapper.busanHitIncrement(contentid);
		return bMapper.busanCultureDetailData(contentid);
	}
	@Override
	public BusanVO busanFestivalDetailData(int contentid) {
		// TODO Auto-generated method stub
		bMapper.busanHitIncrement(contentid);
		return bMapper.busanFestivalDetailData(contentid);
	}
	@Override
	public BusanVO busanStayDetailData(int contentid) {
		// TODO Auto-generated method stub
		bMapper.busanHitIncrement(contentid);
		return bMapper.busanStayDetailData(contentid);
	}
	@Override
	public BusanVO busanShoppingDetailData(int contentid) {
		// TODO Auto-generated method stub
		bMapper.busanHitIncrement(contentid);
		return bMapper.busanShoppingDetailData(contentid);
	}
	@Override
	public BusanVO busanFoodDetailData(int contentid) {
		// TODO Auto-generated method stub
		bMapper.busanHitIncrement(contentid);
		return bMapper.busanFoodDetailData(contentid);
	}
	@Override
	public BusanVO busanAttractionDetailData(int contentid) {
		// TODO Auto-generated method stub
		bMapper.busanHitIncrement(contentid);
		return bMapper.busanAttractionDetailData(contentid);
	}
}
