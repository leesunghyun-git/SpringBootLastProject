package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface ReplyMapper {
	@Select("SELECT no,bno,id,name,sex,msg,TO_CHAR(regdate,'yyyy-mm-dd hh24:mi:ss'),group_tab,group_id as dbday FROM board_reply_2 WHERE bno = #{bno} ORDER BY group_id DESC, group_tab ASC,no DESC")
	public List<ReplyVO> replyListData(int bno);
	
	@Select("SELECT COUNT(*) FROM board_reply_2 WHERE bno=#{bno}")
	int replyCount(int bno);
	
	@Insert("INSERT INTO board_reply_2(no,bno,id,name,sex,msg) VALUES("
			+ "br2_no_seq.nextval, #{bno} , #{id}, #{name}, #{sex}, #{msg})")
	void replyInsert(ReplyVO vo);
	
	@Delete("DELETE board_reply_2 WHERE no = #{no}")
	void replyDelete(int no);
	
	@Update("UPDATE board_reply_2 SET msg = #{msg} WHERE no = #{no}")
	void replyUpdate(ReplyVO vo);
	
}
