package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserCashrecordService {


	public List<UserCashrecordModel> searchUserCashrecord(String startTime,
			String endTime, int page, int rows) throws BaseException;

	public int searchUserCashrecord(String startTime, String endTime)
			throws BaseException;
	
	public boolean updateUserCashrecordById(int recordid, int state)
			throws BaseException;
}
