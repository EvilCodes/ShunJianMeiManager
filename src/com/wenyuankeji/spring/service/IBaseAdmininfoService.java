package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseAdmininfoService {

	public BaseAdmininfoModel searchBaseAdmininfoModel(String username, String password) throws BaseException;
	
	public boolean updateTestSQL(String sql) throws BaseException;
}
