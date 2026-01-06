package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BusanVO;
@Mapper
@Repository
public interface BusanMapper {
	public List<BusanVO> busanListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM busanTravel WHERE contenttype = #{contenttype}")
	public int busanTotalPage(int contenttype);
	public List<BusanVO> busanFindData(Map map);
	public int busanFindTotalPage(String address);
	public List<BusanVO> busanTop4Data();
	public BusanVO busanAttractionDetailData(int contentid);
	public void busanHitIncrement(int contentid);
	public BusanVO busanCultureDetailData(int contentid);
	public BusanVO busanFestivalDetailData(int contentid);
	public BusanVO busanStayDetailData(int contentid);
	public BusanVO busanShoppingDetailData(int contentid);
	public BusanVO busanFoodDetailData(int contentid);
}
