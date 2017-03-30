package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserPhotoMappingDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class UserinfoServiceImpl implements IUserinfoService {

	@Autowired
	private IUserinfoDao userinfoDao;

	@Autowired
	private IUserPhotoMappingDao userPhotoMappingDao;
	
	@Value("#{configProperties['ipHost']}")
	private String ipHost;
	
	@Value("#{configProperties['imgIpHost']}")
	private String imgIpHost;

	@Override
	public int searchAllUserinfo(UserinfoModel userinfo) {

		return userinfoDao.searchAllUserinfo(userinfo);
	}

	@Override
	public List<UserinfoModel> searchAllUserinfo(UserinfoModel userinfo,
			int page, int rows) {
		List<UserinfoModel> userinfoList = userinfoDao.searchAllUserinfo(userinfo, page, rows);
		
		for(UserinfoModel obj : userinfoList){
			//用户相册
			UserPhotoMappingModel userPhotoMapping = userPhotoMappingDao.searchUserPhotoMapping(obj.getUserid(), 1);
			if(userPhotoMapping != null){
				//用户头像
				obj.setPicturepath(imgIpHost + userPhotoMapping.getBasePicture().getPicturepath());
			}
			
			obj.setReferee(ShunJianMeiUtil.dateToString(obj.getCreatetime()));
		}

		return userinfoList;
	}

	@Override
	public boolean updateUserinfo(UserinfoModel userinfo) {

		return userinfoDao.updateUserinfo(userinfo);
	}

	@Override
	public UserinfoModel searchUserinfo(String username, int type) throws BaseException {
		return userinfoDao.searchUserinfo(username, type);
	}

	@Override
	public UserinfoModel searchUserinfoById(int id) throws BaseException{
		return userinfoDao.searchUserinfoById(id);
	}
	
	@Override
	public boolean updateUserInfoByUserId(UserinfoModel userInfo) throws BaseException{
		return userinfoDao.updateUserInfoByUserId(userInfo);
	}

	@Override
	public UserinfoModel searchUserInfoLogin(String username, String password)
			throws BaseException {
		return userinfoDao.searchUserInfoLogin(username, password);
	}

}
