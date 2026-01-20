package com.sist.web.service;


import org.springframework.stereotype.Service;

import com.sist.web.mapper.ReserveMapper;

import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService{
	private final ReserveMapper rMapper;
	@Override
		public List<SeoulVO> seoulReserveData(Map map) {
			// TODO Auto-generated method stub
		List<SeoulVO> list = rMapper.seoulReserveData(map);
		for(SeoulVO vo : list)
		{
			String address = vo.getAddress();
			String[] addrs=address.split(" ");
			String newAddr="";
			if(addrs.length>3)
			{
				for(int i=1;i<=addrs.length-1;i++)
				{
					newAddr+=" "+addrs[i];
				}
			}
			vo.setAddress(newAddr);
		}
			return list;
		}
	@Override
	public int seoulReserveTotalData(String address) {
		// TODO Auto-generated method stub
		return rMapper.seoulReserveTotalData(address);
	}
	@Override
	public String reserveInsert(ReserveVO vo) {
		String res = "NO";
		try {
			rMapper.reserveInsert(vo);
			res="YES";
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub
		return res;
	}
	@Override
	public List<ReserveVO> reserveListData(String id) {
		List<ReserveVO> list =null;
		// TODO Auto-generated method stub
		try {
			list=rMapper.reserveListData(id);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	@Override
	public List<ReserveVO> reserveAdminListData() {
		// TODO Auto-generated method stub
		return rMapper.reserveAdminListData();
	}
	@Override
	public SeoulVO reserveFirstData(int cno) {
		// TODO Auto-generated method stub
		return rMapper.reserveFirstData(cno);
	}
}
