package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class StoreinfoServiceImpl implements IStoreinfoService {

	@Autowired
	private IStoreinfoDao storeinfoDao;

	@Override
	public StoreinfoModel searchStoreinfoModel(int storeid)
			throws BaseException {
		return storeinfoDao.searchStoreinfo(storeid);
	}

	@Override
	public List<StoreinfoModel> searchStoreinfo(String storeid,
			String startTime, String endTime, String state, String name,
			int page, int rows) throws BaseException {
		return storeinfoDao.searchStoreinfo(storeid, startTime, endTime, state,
				name, page, rows);
	}

	@Override
	public int searchStoreinfoCount(String storeid, String startTime,
			String endTime, String state, String name) throws BaseException {
		return storeinfoDao.searchStoreinfoCount(storeid, startTime, endTime,
				state, name);
	}

	@Override
	public boolean updateStoreinfoModel(StoreinfoModel storeinfoModel) {
		return storeinfoDao.updateStoreinfo(storeinfoModel);
	}

	@Override
	public int addInitStoreinfo(StoreinfoModel storeinfoModel) throws Exception {
		return storeinfoDao.addInitStoreinfo(storeinfoModel);
	}

	@Override
	public boolean updateStore(StoreinfoModel storeinfo) throws BaseException {
		return storeinfoDao.updateStore(storeinfo);
	}

}
