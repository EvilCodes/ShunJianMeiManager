package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBasePictureDao;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.service.IBasePictureService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BasePictureServiceImpl implements IBasePictureService {

	@Autowired
	private IBasePictureDao basePictureDao;

	@Override
	public BasePictureModel searchBasePictureByPicId(int picid)
			throws BaseException {

		return basePictureDao.searchBasePictureByPicId(picid);
	}

	@Override
	public boolean updateBasePicture(BasePictureModel basePictureModel)
			throws BaseException {
		return basePictureDao.updateBasePicture(basePictureModel);
	}

}
