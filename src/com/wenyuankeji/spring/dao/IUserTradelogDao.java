package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserTradelogDao {

	public List<UserTradelogModel> searchUserTradelog(String startTime,
			String endTime, int page, int rows) throws BaseException;

	public List<UserTradelogModel> searchUserTradelog(String startTime, String endTime)
			throws BaseException;

	public boolean addUserTradelog(UserTradelogModel userTradelog)
			throws BaseException;

	public String searchCountUserTradelog(String logtype, String startTime, String endTime)
			throws BaseException;
}
