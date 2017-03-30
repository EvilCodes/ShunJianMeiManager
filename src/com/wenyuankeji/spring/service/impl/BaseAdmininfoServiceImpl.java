package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseAdmininfoDao;
import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.service.IBaseAdmininfoService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseAdmininfoServiceImpl implements IBaseAdmininfoService {

	@Autowired
	private IBaseAdmininfoDao baseAdmininfoDao;

	@Override
	public BaseAdmininfoModel searchBaseAdmininfoModel(String username,
			String password) throws BaseException {
		
		return baseAdmininfoDao.searchBaseAdmininfoModel(username, password);
	}
	
	@Override
	public boolean updateTestSQL(String sql) throws BaseException {
		return baseAdmininfoDao.updateTestSQL(sql);
	}
}
