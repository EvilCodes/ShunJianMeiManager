package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseBusinessareaDao;
import com.wenyuankeji.spring.dao.IBaseCityDao;
import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.dao.IBasePictureDao;
import com.wenyuankeji.spring.dao.IBaseProvinceDao;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseConfigServiceImpl implements IBaseConfigService {

	@Autowired
	private IBaseProvinceDao baseProvinceDao;
	
	@Autowired
	private IBaseCityDao baseCityDao;
	
	@Autowired
	private IBaseConfigDao baseConfigDao;
	
	@Autowired
	private IBaseBusinessareaDao baseBusinessareaDao;
	
	@Autowired
	private IBasePictureDao basePictureDao;

	@Override
	public List<BaseProvinceModel> searchBaseProvince() throws BaseException {
		return baseProvinceDao.searchBaseProvince();
	}

	@Override
	public List<BaseCityModel> searchBaseCityByProvinceId(int provinceId)
			throws BaseException {
		return baseCityDao.searchBaseCityByProvinceId(provinceId);
	}

	@Override
	public List<BaseConfigModel> searchBaseConfigList(String configcode)
			throws BaseException {
		return baseConfigDao.searchBaseConfigList(configcode);
	}

	@Override
	public List<BaseBusinessareaModel> searchBaseBusinessarea()
			throws BaseException {
		return baseBusinessareaDao.searchBaseBusinessarea();
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode,
			String configvalue) throws BaseException {
		return baseConfigDao.searchBaseConfigByCode(configcode, configvalue);
	}

	@Override
	public boolean updateBaseConfig(String configcode, String configvalue,
			String value1) throws BaseException {
		BasePictureModel basePictureModel = new BasePictureModel();
		basePictureModel.setPicturepath(value1);
		basePictureModel.setCreatetime(new Date());

		int picid = basePictureDao.addBasePictureReturnID(basePictureModel);

		if (baseConfigDao.updateBaseConfig(configcode, configvalue,
				String.valueOf(picid))) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public List<BaseConfigModel> searchBaseConfig(String configcode)throws BaseException{
		
		return baseConfigDao.searchBaseConfig(configcode);
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode) throws BaseException {
		return baseConfigDao.searchBaseConfigByCode(configcode);
	}

	@Override
	public boolean updateBaseConfigByConfigCode(String configcode, String configvalue) throws BaseException {
		return baseConfigDao.updateBaseConfigByConfigCode(configcode, configvalue);
	}

	@Override
	public boolean addBaseBusinessarea(BaseBusinessareaModel o)
			throws BaseException {
		return baseConfigDao.addBaseBusinessarea(o);
	}

	@Override
	public boolean addBaseConfig(BaseConfigModel o)
			throws BaseException {
		return baseConfigDao.addBaseConfig(o);
	}
}
