package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseConfigService {

	/**
	 * 查找省份
	 * @return
	 * @throws BaseException
	 */
	public List<BaseProvinceModel> searchBaseProvince()throws BaseException;

	/**
	 * 根据省份id查找城市信息
	 * @param provinceId 省份ID
	 * @return
	 * @throws BaseException
	 */
	public List<BaseCityModel> searchBaseCityByProvinceId(int provinceId)throws BaseException;
	
	/**
	 * 查询所有的商圈
	 * @return
	 * @throws BaseException
	 */
	public List<BaseBusinessareaModel> searchBaseBusinessarea() throws BaseException;
	
	/**
	 * 添加商圈
	 * @return
	 * @throws BaseException
	 */
	public boolean addBaseBusinessarea(BaseBusinessareaModel o) throws BaseException;
	
	/**
	 * 设置配置表
	 * @return
	 * @throws BaseException
	 */
	public boolean addBaseConfig(BaseConfigModel o)
			throws BaseException;
	
	/**
	 * 查询基础设置表	
	 * @param configcode 配置编码
	 * @return
	 * @throws BaseException
	 */
	public List<BaseConfigModel> searchBaseConfigList(String configcode)throws BaseException;
	/**
	 * 查询首页图片
	 * @param configcode 配置编码
	 * @param cofigvalue 值
	 * @return
	 */
	public BaseConfigModel searchBaseConfigByCode(String configcode, String configvalue)throws BaseException;
	
	/**
	 * 修改首页图片
	 * @param configcode 配置编码
	 * @param cofigvalue 值
	 * @param value1  picid值
	 * @return
	 */
	public boolean updateBaseConfig(String configcode, String configvalue, String value1)throws BaseException;
	
	public List<BaseConfigModel> searchBaseConfig(String configcode)throws BaseException;
	

	/**
	 * 查询基础设置表	
	 * @param configcode 配置编码
	 * @return
	 * @throws BaseException
	 */
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException;
	
	/**
	 * 根据编码更新配置信息
	 * @param configcode
	 * @param configvalue
	 * @return
	 * @throws BaseException
	 */
	public boolean updateBaseConfigByConfigCode(String configcode, String configvalue)throws BaseException;
}
