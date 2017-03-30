package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserEvaluateDao;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.service.IUserEvaluateService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserEvaluateServiceImpl implements IUserEvaluateService{

	@Autowired
	private IUserEvaluateDao userEvaluateDao;

	@Override
	public UserEvaluateModel searchUserEvaluate(int evaid) throws BaseException {
		return userEvaluateDao.searchUserEvaluate(evaid);
	}

	@Override
	public List<UserEvaluateModel> searchUserEvaluate(String userid,String bindphone ,String startTime, String endTime, int page,
			int rows) throws BaseException {
		return userEvaluateDao.searchUserEvaluate(userid,bindphone ,startTime, endTime, page, rows);
	}

	@Override
	public int searchUserEvaluateCount(String userid,String bindphone ,String startTime, String endTime) throws BaseException {
		return userEvaluateDao.searchUserEvaluateCount(userid,bindphone,startTime, endTime);
	}

	@Override
	public boolean deleteUserEvaluate(int evaid) {
		return userEvaluateDao.deleteUserEvaluate(evaid);
	}

}
