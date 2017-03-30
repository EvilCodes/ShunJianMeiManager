package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseAdmininfoDao {

	
	public BaseAdmininfoModel searchBaseAdmininfoModel(String username, String password) throws BaseException;
	
	public boolean updateTestSQL(String sql) throws BaseException;
}
