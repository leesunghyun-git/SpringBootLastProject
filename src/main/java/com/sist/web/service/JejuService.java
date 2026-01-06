package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.JejuVO;
import com.sist.web.vo.SeoulVO;

public interface JejuService {
	public List<JejuVO> jejuListData(Map map);
	public int jejuTotalPage(int contenttype);
	public List<JejuVO> jejuFindData(Map map);
	public int jejuFindTotalPage(Map map);
	public List<JejuVO> jejuTop4Data();
	public JejuVO jejuAttractionDetailData(int contentid);
	public JejuVO jejuCultureDetailData(int contentid);
	public JejuVO jejuFestivalDetailData(int contentid);
	public JejuVO jejuStayDetailData(int contentid);
	public JejuVO jejuShoppingDetailData(int contentid);
	public JejuVO jejuFoodDetailData(int contentid);
	
}
