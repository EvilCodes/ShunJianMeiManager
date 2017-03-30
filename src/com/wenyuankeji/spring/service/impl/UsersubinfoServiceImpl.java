package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.dao.IUserProfessionLevelDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UsersubinfoServiceImpl implements IUsersubinfoService{

	@Autowired
	private IUsersubinfoDao usersubinfoDao;
	@Autowired
	private IUserProfessionLevelDao userProfessionLevelDao;
	@Autowired
	private IBaseConfigDao baseConfigDao;
	
	/************管理端************/
	@Override
	public List<UsersubinfoModel> searchUserinfo(String cityid, String userid,
			String tel, String startTime, String endTime, String checkstate,String txtNickname,
			int page, int rows) throws BaseException {
		return usersubinfoDao.searchUsersubinfo(cityid, userid, tel, startTime, endTime, checkstate, txtNickname,page, rows);
	}

	@Override
	public int searchUserinfoCount(String cityid, String userid, String tel,
			String startTime, String endTime, String checkstate,String txtNickname)
			throws BaseException {
		return usersubinfoDao.searchUserinfoCount(cityid, userid, tel, startTime, endTime, checkstate,txtNickname);
	}

	@Override
	public UsersubinfoModel searchUsersubinfo(int userid) throws BaseException {
		return usersubinfoDao.searchUsersubinfo(userid);
	}

	@Override
	public boolean updateUsersubinfo(UsersubinfoModel usersubinfoModel, int type)throws BaseException {
		if (0 == type) {
			//定级和状态都更新
			return usersubinfoDao.updateUsersubinfo(usersubinfoModel);
		}
		return usersubinfoDao.updateUsersubinfo(usersubinfoModel, type);
	}

	
	@Override
	public List<UserProfessionLevelModel> searchUserProfessionLevel() throws BaseException {
		return userProfessionLevelDao.searchUserProfessionLevel();
	}

	@Override
	public UsersubinfoModel searchUserSubInfoByUserId(int userId) {
		return usersubinfoDao.searchUserSubInfoByUserId(userId);
	}

	@Override
	public int addUserSubInfo(UsersubinfoModel userSubInfo) {
		return usersubinfoDao.addUserSubInfo(userSubInfo);
	}
	
	@Override
	public boolean updateUserSubInfoByUserId(UsersubinfoModel userSubInfo) throws BaseException{
		return usersubinfoDao.updateUserSubInfoByUserId(userSubInfo);
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode) throws BaseException {
		return baseConfigDao.searchBaseConfigByCode(configcode);
	}
}
