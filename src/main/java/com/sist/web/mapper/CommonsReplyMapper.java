package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface CommonsReplyMapper {
	@Select("SELECT no,cno,id,name,msg,sex,TO_CHAR(regdate,'yyyy-mm-dd HH24:MI:SS') as dbday,group_tab FROM commonsReply_2 WHERE cno=#{cno} ORDER BY group_id DESC,group_step ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<CommonsReplyVO> commonsReplyListData(@Param("cno")int cno,@Param("start")int start);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM commonsReply_2 WHERE cno=#{cno}")
	public int commonsReplyTotalPage(int cno);
	
	@Insert("INSERT INTO commonsReply_2(no,cno,id,name,sex,msg,group_id) VALUES(cs2_no_seq.nextval,#{cno},#{id},#{name},#{sex},#{msg},(SELECT NVL(MAX(group_id)+1,1) FROM commonsReply_2))")
	void commonsReplyInsert(CommonsReplyVO vo);
	
	@Select("SELECT COUNT(*) FROM commonsReply_2 WHERE cno=#{cno}")
	int commonsCount(int cno);
	
	@Delete("DELETE commonsReply_2 WHERE no = #{no}")
	void commonsDelete(int no);
	
	@Update("UPDATE commonsReply_2 SET msg=#{msg} WHERE no = #{no}")
	void commonsUpdate(CommonsReplyVO vo);
	
	@Update("UPDATE commonsReply_2 SET depth=depth-1 WHERE no = #{no}")
	void commonsDepthDecrement(int no);
	
	@Select("SELECT * FROM commonsReply_2 WHERE no =#{no}")
	CommonsReplyVO commonsInfoData(int no);
	
	@Delete("DELETE commonsReply_2 WHERE group_id = #{group_id}")
	void commonsAllDelete(int group_id);
	
	@Select("SELECT group_id,group_step,group_tab FROM commonsReply_2 WHERE no = #{no}")
	CommonsReplyVO commonsReplyParentData(int no);
	
	@Update("UPDATE commonsReply_2 SET group_step=group_step+1 WHERE group_id=#{group_id} AND group_step=#{group_step}")
	void commonsGroupStepIncrement(CommonsReplyVO vo);
	
	@Insert("INSERT INTO commonsReply_2 VALUES(cs2_no_seq.nextval, #{cno},#{id},#{name},#{sex},#{msg},#{group_id},#{group_step},#{group_tab},#{root},0,SYSDATE)")
	void commonsReplyReplyInsert(CommonsReplyVO vo);
	
	@Update("UPDATE commonsReply_2 SET depth=depth+1 WHERE no = #{no}")
	void commonsDepthIncrement(int no);
}
