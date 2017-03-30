package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBasePictureDao {

	/**
	 * 根据picid查询图片
	 * 
	 * @return
	 */
	public BasePictureModel searchBasePictureByPicId(int picid)
			throws BaseException;
	

	public Integer addBasePictureReturnID(BasePictureModel basePictureModel) throws BaseException;
	
	public boolean updateBasePicture(BasePictureModel basePictureModel) throws BaseException;
}
