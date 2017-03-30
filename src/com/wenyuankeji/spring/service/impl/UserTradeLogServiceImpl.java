package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.service.IUserTradeLogService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserTradeLogServiceImpl implements IUserTradeLogService {

	@Autowired
	private IUserTradelogDao userTradelogDao;

	@Override
	public List<UserTradelogModel> searchUserTradelog(String startTime,
			String endTime, int page, int rows) throws BaseException {
		return userTradelogDao.searchUserTradelog(startTime, endTime, page,
				rows);
	}

	@Override
	public List<UserTradelogModel> searchUserTradelog(String startTime, String endTime)
			throws BaseException {
		return userTradelogDao.searchUserTradelog(startTime, endTime);
	}

	@Override
	public boolean addUserTradelog(UserTradelogModel userTradelog)
			throws BaseException {
		return userTradelogDao.addUserTradelog(userTradelog);
	}

	@Override
	public String searchCountUserTradelog(String logtype, String startTime,
			String endTime) throws BaseException {
		return userTradelogDao.searchCountUserTradelog(logtype, startTime, endTime);
	}

}
