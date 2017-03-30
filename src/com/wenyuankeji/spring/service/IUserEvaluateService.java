package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserEvaluateService {

	/**
	 * 根据id查询评价信息
	 * @param sid
	 * @return
	 * @throws BaseException
	 */
	public UserEvaluateModel searchUserEvaluate(int evaid)throws BaseException;

	/**
	 * 查询评价
	 * @param bindphone
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List<UserEvaluateModel> searchUserEvaluate(String userid,String bindphone,String startTime,String endTime,int page, int rows) throws BaseException;

	/**
	 * 查询评价Count
	 * @param startTime 注册开始时间
	 * @param endTime 注册结束时间
	 * @return
	 * @throws BaseException
	 */
	public int searchUserEvaluateCount(String userid,String bindphone,String startTime,String endTime) throws BaseException;
	
	/**
	 * 删除评价信息
	 * @param userEvaluateModel
	 * @return
	 */
	public boolean deleteUserEvaluate(int evaid);
}
