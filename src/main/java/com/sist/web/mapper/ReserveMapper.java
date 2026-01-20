package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface ReserveMapper {
	public List<SeoulVO> seoulReserveData(Map map);
	
	public int seoulReserveTotalData(String address);
	
	@Insert("INSERT INTO reserve_2(no,cno,id,rday,rtime,rinwon) VALUES(r2_no_seq.nextval,#{cno},#{id},#{rday},#{rtime},#{rinwon})")
	void reserveInsert(ReserveVO vo);
	/*@Results({
		@Result(column="title",property = "svo.title"),
		@Result(column="image1",property = "svo.image1"),
		@Result(column="address",property = "svo.address")		
	})*/
	@ResultMap("resMap")
	@Select("SELECT r.no,cno,rday,rtime,rinwon,TO_CHAR(regdate,'YYYY-mm-dd') as dbday,isReserve,title,image1,address from reserve_2 r, seoultravel s WHERE id = #{id} AND r.cno=s.contentid ORDER BY no DESC")
	List<ReserveVO> reserveListData(String id);
	@ResultMap("resMap")
	@Select("SELECT r.no,id,cno,rday,rtime,rinwon,TO_CHAR(regdate,'YYYY-mm-dd') as dbday,isReserve,title,image1,address from reserve_2 r, seoultravel s WHERE r.cno=s.contentid ORDER BY no DESC")
	List<ReserveVO> reserveAdminListData();
	
	@Select("SELECT no,contentid,image1,title,x,y,address FROM seoultravel WHERE contentid=#{cno}")
	SeoulVO reserveFirstData(int cno);
	
}
