package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserWalletService {
	
	/**
	 * 根据用户查询钱包的卡片
	 * @param userid 用户ID
	 * @param ownertype 所有者类型: 1用户2美发师3店铺
	 * @return
	 * @throws BaseException
	 */
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException;
	
	/**
	 * 根据userId更新钱包金额
	 * @param userSubInfo
	 * @return
	 */
	public boolean updateUserWalletByUserId(UserWalletModel userWalletModel) throws BaseException;
	
	/**
	 * 生成钱包
	 * @param UserWalletModel
	 * @return
	 */
	public UserWalletModel addUserWallet(UserWalletModel o) throws BaseException;
	
}
