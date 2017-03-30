package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserEvaluateDao {

	/**
	 * 根据商户id查询评价信息
	 * @param evaid
	 * @return
	 * @throws BaseException
	 */
	public UserEvaluateModel searchUserEvaluate(int evaid)throws BaseException;

	/**
	 * 查询评价
	 * @param userid
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
	 * @param userid
	 * @param bindphone
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
