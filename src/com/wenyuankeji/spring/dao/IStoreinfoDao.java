package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IStoreinfoDao {

	/**
	 * 根据商户id查询商户信息
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public StoreinfoModel searchStoreinfo(int storeid)throws BaseException;

	/**
	 * 修改用户扩展信息
	 * @param storeinfoModel
	 * @return
	 */
	public boolean updateStoreinfo(StoreinfoModel storeinfoModel);
	
	/**
	 * 查询美发店
	 * @param storeid 商户id
	 * @param startTime 注册开始时间
	 * @param endTime 注册结束时间
	 * @param state 审核状态
	 * @param name 商户名称
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List<StoreinfoModel> searchStoreinfo(String storeid,String startTime,String endTime,String state,String name,int page, int rows) throws BaseException;

	/**
	 * 查询美发店
	 * @param storeid 商户id
	 * @param startTime 注册开始时间
	 * @param endTime 注册结束时间
	 * @param state 审核状态
	 * @param name 商户名称
	 * @return
	 * @throws BaseException
	 */
	public int searchStoreinfoCount(String storeid,String startTime,String endTime,String state,String name) throws BaseException;
	
	/**
	 * 商户生成
	 * @param storeinfoModel
	 * @return
	 * @throws Exception
	 */
	public int addInitStoreinfo(StoreinfoModel storeinfoModel) throws Exception;
	
	/**
	 * 美发店信息验证
	 * @param storeinfoModel
	 * @return
	 * @throws BaseException
	 */
	public boolean updateStore(StoreinfoModel storeinfo) throws BaseException;
}
