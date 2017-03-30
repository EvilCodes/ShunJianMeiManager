package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseSuggestionService {

	/**
	 * 根据商户id查询建议信息
	 * @param sid
	 * @return
	 * @throws BaseException
	 */
	public BaseSuggestionModel searchBaseSuggestion(int sid)throws BaseException;

	/**
	 * 查询建议
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List<BaseSuggestionModel> searchBaseSuggestion(String startTime,String endTime,int page, int rows) throws BaseException;

	/**
	 * 查询建议Count
	 * @param startTime 注册开始时间
	 * @param endTime 注册结束时间
	 * @return
	 * @throws BaseException
	 */
	public int searchBaseSuggestionCount(String startTime,String endTime) throws BaseException;
}
