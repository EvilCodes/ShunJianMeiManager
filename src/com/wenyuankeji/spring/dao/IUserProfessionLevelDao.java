package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserProfessionLevelDao {

	/**
	 * 查询所有的职称等级
	 * @return
	 */
	public List<UserProfessionLevelModel> searchUserProfessionLevel()throws BaseException;
}
