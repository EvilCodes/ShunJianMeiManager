package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseSuggestionDao;
import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.service.IBaseSuggestionService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseSuggestionServiceImpl implements IBaseSuggestionService{

	@Autowired
	private IBaseSuggestionDao baseSuggestionDao;

	@Override
	public BaseSuggestionModel searchBaseSuggestion(int sid) throws BaseException {
		return baseSuggestionDao.searchBaseSuggestion(sid);
	}

	@Override
	public List<BaseSuggestionModel> searchBaseSuggestion(String startTime, String endTime, int page, int rows)
			throws BaseException {
		return baseSuggestionDao.searchBaseSuggestion(startTime, endTime, page, rows);
	}

	@Override
	public int searchBaseSuggestionCount(String startTime, String endTime) throws BaseException {
		return baseSuggestionDao.searchBaseSuggestionCount(startTime, endTime);
	}

	
}
