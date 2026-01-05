package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.JejuVO;
import com.sist.web.vo.SeoulVO;
@Mapper
@Repository
public interface JejuMapper {
	public List<JejuVO> jejuListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM jejuTravel WHERE contenttype = #{contenttype}")
	public int jejuTotalPage(int contenttype);
}
