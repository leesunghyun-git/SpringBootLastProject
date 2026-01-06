package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.BusanVO;
import com.sist.web.vo.SeoulVO;

public interface BusanService {
	public List<BusanVO> busanListData(Map map);
	public int busanTotalPage(int contenttype);
	public List<BusanVO> busanFindData(Map map);
	public int busanFindTotalPage(String address);
	public List<BusanVO> busanTop4Data();
	public BusanVO busanCultureDetailData(int contentid);
	public BusanVO busanFestivalDetailData(int contentid);
	public BusanVO busanStayDetailData(int contentid);
	public BusanVO busanShoppingDetailData(int contentid);
	public BusanVO busanFoodDetailData(int contentid);
	public BusanVO busanAttractionDetailData(int contentid);
}
