package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseBusinessareaDao {

	/**
	 * 查询所有的商圈
	 * @return
	 * @throws BaseException
	 */
	public List<BaseBusinessareaModel> searchBaseBusinessarea() throws BaseException;
	

	public List<BaseBusinessareaModel> searchBaseBusinessarea(int cityid) throws BaseException;
}
