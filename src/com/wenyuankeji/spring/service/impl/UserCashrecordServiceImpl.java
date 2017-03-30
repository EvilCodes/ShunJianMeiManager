package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserCashrecordDao;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.service.IUserCashrecordService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserCashrecordServiceImpl implements IUserCashrecordService {

	@Autowired
	private IUserCashrecordDao userCashrecordDao;

	@Override
	public List<UserCashrecordModel> searchUserCashrecord(String startTime,
			String endTime, int page, int rows) throws BaseException {
		return userCashrecordDao.searchUserCashrecord(startTime, endTime, page,
				rows);
	}

	@Override
	public int searchUserCashrecord(String startTime, String endTime)
			throws BaseException {
		return userCashrecordDao.searchUserCashrecord(startTime, endTime);
	}

	@Override
	public boolean updateUserCashrecordById(int recordid, int state)
			throws BaseException {
		return userCashrecordDao.updateUserCashrecordById(recordid, state);
	}

}
