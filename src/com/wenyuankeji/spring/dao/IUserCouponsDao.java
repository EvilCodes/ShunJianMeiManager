package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserCouponsDao {

	/**
	 * 添加我的优惠券
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addUserCoupons(UserCouponsModel o) throws BaseException;
	
}
