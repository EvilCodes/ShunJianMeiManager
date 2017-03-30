package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleSearchModel;

public interface IUserMyhairstyleDao {

	public int searchUserMyhairstyle(String userid, String tel, 
			String startTime, String endTime,String checkstate,String txtNickname);

	public List<UserMyhairstyleSearchModel> searchUserMyhairstyle(
			String userid, String tel, 
			String startTime, String endTime,String checkstate,String txtNickname,int page, int rows);

	public boolean updateUserMyhairstyle(UserMyhairstyleModel userMyhairstyle);
	
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid);
	
}
