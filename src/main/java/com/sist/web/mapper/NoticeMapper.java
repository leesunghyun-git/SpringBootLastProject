package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.NoticeVO;

@Mapper
@Repository
public interface NoticeMapper {
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd') as dbday, hit,type "
			+ "FROM notice_2 ORDER BY no DESC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<NoticeVO> noticeListData(int start);
	
	@Select("SELECT CEIL(count(*)/10.0) FROM NOTICE_2")
	int noticeTotalPage();
	
	@Insert("INSERT INTO notice_2(no,type,subject,content,filename,filecount)"
			+ "VALUES (no2_no_seq.nextval,#{type},#{subject},#{content},#{filename},#{filecount})")
	void noticeInsert(NoticeVO vo);
	
	@Update("UPDATE notice_2 SET hit = hit +1 where no = #{no}")
	void noticeHitIncrement(int no);
	
	@Select("SELECT no,subject,name,type,hit,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,filecount,filename,content FROM notice_2 WHERE no = #{no}")
	NoticeVO noticeDetailData(int no);
	
	@Select("SELECT filename,filecount FROM notice_2 WHERE no = #{no}")
	NoticeVO noticeFileData(int no);
	
	@Delete("DELETE notice_2 WHERE no = #{no}")
	void noticeDeleteData(int no);
	
	@Update("UPDATE notice_2 SET type=#{type}, subject=#{subject}, content=#{content} WHERE no = #{no}")
	void noticeUpdate(NoticeVO vo);
}
