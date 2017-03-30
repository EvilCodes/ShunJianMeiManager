package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

public interface ICouponsDao {
	
	/**
	 * 添加优惠券
	 * @return
	 */
	public int addCoupons(CouponsModel couponsModel) throws BaseException;
	
	/**
	 * 添我的优惠卷
	 * @throws BaseException
	 */
	public Integer addUserCouponsModel(UserCouponsModel o)throws BaseException;
	
}
