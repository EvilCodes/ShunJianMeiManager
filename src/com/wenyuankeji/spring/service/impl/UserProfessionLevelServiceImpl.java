package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserProfessionLevelDao;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.service.IUserProfessionLevelService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserProfessionLevelServiceImpl implements IUserProfessionLevelService {

	@Autowired
	private IUserProfessionLevelDao userProfessionLevelDao;

	@Override
	public List<UserProfessionLevelModel> searchUserProfessionLevel() throws BaseException {
		return userProfessionLevelDao.searchUserProfessionLevel();
	}
	
}
