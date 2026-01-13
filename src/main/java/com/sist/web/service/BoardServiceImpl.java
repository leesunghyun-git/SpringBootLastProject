package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;
import com.sist.web.mapper.*;
/*
 * 			VO / Entity / DTO / Recode
 * 			 |		|	   |	  | getter => Kotlin (data)
 * 						 getter/setter
 * 				 테이블 제어 (컬럼과 일치)
 * 				  => JPA
 * 			불변 (setter가 없다)
 * 			*** VO / DTO
 * 
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper bMapper;
	@Override
		public List<BoardVO> boardListData(int start) {
			// TODO Auto-generated method stub
			return bMapper.boardListData(start);
		}
	@Override
	public int boardTotalPage() {
		// TODO Auto-generated method stub
		return bMapper.boardTotalPage();
	}
	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		bMapper.boardInsert(vo);
	}
	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		bMapper.boardHitInclude(no);
		return bMapper.boardDetailData(no);
	}
	@Override
	public void boardUpdateData(BoardVO vo) {
		// TODO Auto-generated method stub
		bMapper.boardUpdateData(vo);
	}
	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return bMapper.boardDetailData(no);
	}
	@Override
	public String boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		String result = "NO";
		String dbpwd = bMapper.boardGetPassword(vo.getNo());
		if(dbpwd.equals(vo.getPwd()))
		{
			result ="YES";
			bMapper.boardUpdate(vo);
		}
		return result;
	}
	@Override
	public String boardDelete(BoardVO vo) {
		// TODO Auto-generated method stub
		String result = "NO";
		String dbpwd = bMapper.boardGetPassword(vo.getNo());
		if(dbpwd.equals(vo.getPwd()))
		{
			result ="YES";
			bMapper.boardDelete(vo.getNo());
		}
		return result;
	}
}
