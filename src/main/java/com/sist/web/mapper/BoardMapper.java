package com.sist.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Mapper // 구현
@Repository // 메모리 할당
/*
 *  Spring Framework : XML + Annotation
 *  SpringBoot : Annotation
 *  ---------- Framework (JSP / ThymeLeaf)
 *  		    ORM (MyBatis / JPA)
 *  			=> View단 (Pinia = react 비슷)
 *  ---------- Security : 일반 / JWT => 토큰생성
 *  					   |     |
 *  					 Session Cookie
 *  ---------- WebSocket
 *  ---------- Spring AI
 *  ---------- Batch : Task (실시간) => 스케쥴러
 *  ---------- Kafka : 메세지 (알림)
 *  
 *  Vue / Vuex / Pinia (JQuery : Ajax)
 *  ------------------
 *  React / Redux / TanStack-Query => Next : TypeScript
 *  				|
 *  				MSA => NodeJS / SpringBoot
 *  -------------------------------------------------------
 *  	CI/CD
 *  		Git Action / Docker / Docker-compose (MSA)
 *  					   |
 *  					 Docker Hub
 *  		Kube => 우분투에서 처리하는 내용 (MSA)
 *  		Jenkin => 모니터링 => webhook
 *  							   | Git
 *  		--------------------------------------------------------
 *  		| 자바 / 오라클 / JSP / MVC
 *  		| JavaScript / HTML/CSS
 *  		---------------------------- 공통 기반
 */
public interface BoardMapper {
	@Select("SELECT no,subject,name,hit,replycount,TO_CHAR(regdate,'yyyy-mm-dd') as dbday FROM board_2 ORDER BY no DESC OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM board_2")
	public int boardTotalPage();
	
	@SelectKey(keyProperty = "no" ,resultType = int.class,before=true,statement="SELECT NVL(MAX(no)+1,1) as no FROM board_2")
	@Insert("INSERT INTO board_2 VALUES(#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0,0)")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE board_2 SET HIT = HIT +1 WHERE no = #{no}")
	void boardHitInclude(int no);
	
	@Select("SELECT no,subject,name,hit,content,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,replycount FROM board_2 WHERE no = #{no}")
	public BoardVO boardDetailData(int no);
	
	@Update("UPDATE BOARD_2 SET name=#{name},subject=#{subject},content=#{content} WHERE no = #{no}")
	void boardUpdateData(BoardVO vo);
	
	@Select("SELECT pwd FROM board_2 WHERE no = #{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE board_2 SET name=#{name},subject=#{subject},content=#{content} WHERE no = #{no}")
	void boardUpdate(BoardVO vo);
	
	@Delete("DELETE board_2 WHERE no = #{no}")
	void boardDelete(int no);
	
	@Update("UPDATE board_2 SET replycount = #{replycount} WHERE no = #{no}")
	void boardReplyUpdate(@Param("no")int no,@Param("replycount")int replycount);
}
