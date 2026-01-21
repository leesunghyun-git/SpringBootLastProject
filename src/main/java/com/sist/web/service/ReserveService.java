package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

public interface ReserveService {
	public List<SeoulVO> seoulReserveData(Map map);
	public int seoulReserveTotalData(String address);
	String reserveInsert(ReserveVO vo);
	List<ReserveVO> reserveListData(String id);
	List<ReserveVO> reserveAdminListData();
	SeoulVO reserveFirstData(int cno);
	void reserveAdminOk(int no);
	void reserveUserCancel(int no);
	void reserveAdminDelete(int no);
	ReserveVO reserveDetailData(int no);
}
