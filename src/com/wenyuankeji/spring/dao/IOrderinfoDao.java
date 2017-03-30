package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IOrderinfoDao {
	
	public OrderinfoModel searchOrderinfo(int orderid);

	public int searchOrderinfo(int customerid, int userid, int storeid,
			String startTime, String endTime, int paystate, int orderid,
			String customerTelephone,String userTelephone);

	public List<OrderinfoModel> searchOrderinfo(int customerid, int userid,
			int storeid, String startTime, String endTime, int paystate, int orderid,
			String customerTelephone,String userTelephone,int page, int rows);

	public boolean updateOrderinfo(OrderinfoModel orderinfo);

	/**
	 * 获取异常订单列表
	 * @param paystate
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<OrderinfoModel> searchOrderExceptionInfo( int paystate, int page, int rows);
	
	/**
	 * 获取异常订单数目
	 * @param paystate
	 * @return
	 */
	public int searchOrderExceptionInfoCount(int paystate);
	
	/**
	 * 获取订单统计信息
	 * @param paystate
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<OrderinfoModel> searchOrderCountInfo(int paystate,String startTime,String endTime, int page, int rows);
	
	/**
	 * 获取订单统计信息数据
	 * @param paystate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int searchOrderCountInfoCount(String paystate,String startTime,String endTime);
	
	/**
	 * 修改订单状态
	 * @param paystate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean updateOrderinfoState(int orderid, int state);
	
	/**
	 * 统计订单优惠的总额
	 * @param paystate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String searchOrderinfoCount(String startTime,String endTime);
	
	/**
	 * 获取订单的服务项目
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public List<String> searchOrderServicenameList(int orderid);
}
