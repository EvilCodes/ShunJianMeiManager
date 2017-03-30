package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserinfoService {

	public int searchAllUserinfo(UserinfoModel userinfo);

	public List<UserinfoModel> searchAllUserinfo(UserinfoModel userinfo, int page,
			int rows);

	//public boolean addUserinfo(UserinfoModel userinfo);

	public boolean updateUserinfo(UserinfoModel userinfo);

	//public boolean deleteUserinfo(UserinfoModel userinfo);
	
	/**
	 * 根据手机号查询用户表
	 * @param username 用户名
	 * @return 1： 用户  2： 美发师
	 * @throws BaseException
	 */
	public UserinfoModel searchUserinfo(String username,int type)throws BaseException;
	
	/**
	 * 查询用户信息
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public UserinfoModel searchUserinfoById(int id)throws BaseException;
	
	/**
	 * 根据userId修改用户基本信息
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfoByUserId(UserinfoModel userInfo) throws BaseException;

	/**
	 * 管理员登录
	 * @param userInfo
	 * @return
	 */
	public UserinfoModel searchUserInfoLogin(String username, String password) throws BaseException;
}
