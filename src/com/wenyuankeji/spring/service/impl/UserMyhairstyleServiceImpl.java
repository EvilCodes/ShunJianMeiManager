package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserMyhairstyleDao;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleSearchModel;
import com.wenyuankeji.spring.service.IUserMyhairstyleService;

@Service
public class UserMyhairstyleServiceImpl implements IUserMyhairstyleService {

	@Autowired
	private IUserMyhairstyleDao userMyhairstyleDao;

	@Override
	public int searchUserMyhairstyle(String userid, String tel, 
			String startTime, String endTime,String checkstate,String txtNickname) {

		return userMyhairstyleDao.searchUserMyhairstyle(userid, tel, startTime, endTime, checkstate,txtNickname);
	}

	@Override
	public List<UserMyhairstyleSearchModel> searchUserMyhairstyle(
			String userid, String tel, 
			String startTime, String endTime,String checkstate,String txtNickname, int page, int rows) {

		return userMyhairstyleDao.searchUserMyhairstyle(userid, tel, startTime, endTime, checkstate,txtNickname, page, rows);
	}

	@Override
	public boolean updateUserMyhairstyle(UserMyhairstyleModel userMyhairstyle) {
		
		return userMyhairstyleDao.updateUserMyhairstyle(userMyhairstyle);
	}

	@Override
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid) {
		
		return userMyhairstyleDao.searchUserMyhairstyle(mystyleid);
	}

}
