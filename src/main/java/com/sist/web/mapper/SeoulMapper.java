package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface SeoulMapper {

	public List<SeoulVO> seoulListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoulTravel WHERE contenttype = #{contenttype}")
	public int seoulTotalPage(int contenttype);
	public List<SeoulVO> seoulFindData(Map map);
	public int seoulFindTotalPage(String address);
	public SeoulVO seoulAttractionDetailData(int contentid);
	public void seoulHitIncrement(int contentid);
	public SeoulVO seoulCultureDetailData(int contentid);
	public SeoulVO seoulFestivalDetailData(int contentid);
	public SeoulVO seoulStayDetailData(int contentid);
	public SeoulVO seoulShoppingDetailData(int contentid);
	public SeoulVO seoulFoodDetailData(int contentid);
	public List<SeoulVO> seoulTop5Data();
}
