package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBasePictureService {

	/**
	 * 根据picid查询图片
	 * 
	 * @return
	 */
	public BasePictureModel searchBasePictureByPicId(int picid)
			throws BaseException;

	
	public boolean updateBasePicture(BasePictureModel basePictureModel) throws BaseException;
}
