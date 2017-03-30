package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserProfessionLevelService {

	/**
	 * 查询所有的职称等级
	 * @return
	 */
	public List<UserProfessionLevelModel> searchUserProfessionLevel()throws BaseException;
}
