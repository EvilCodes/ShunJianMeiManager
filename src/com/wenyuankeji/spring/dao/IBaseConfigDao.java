package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseConfigDao {

	/**
	 * 查询基础设置表	
	 * @param configcode 配置编码
	 * @return
	 * @throws BaseException
	 */
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException;
	
	
	/**
	 * 查询基础设置表	
	 * @param configcode 配置编码
	 * @return
	 * @throws BaseException
	 */
	public List<BaseConfigModel> searchBaseConfigList(String configcode)throws BaseException;
	
	/**
	 * 修改配置信息
	 * @param configcode 配置编码
	 * @param cofigvalue 值
	 * @return
	 */
	public boolean updateBaseConfig(String configcode, String configvalue);
	
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
	 * 根据编码更新配置信息
	 * @param configcode
	 * @param configvalue
	 * @return
	 * @throws BaseException
	 */
	public boolean updateBaseConfigByConfigCode(String configcode, String configvalue)throws BaseException;
	
	/**
	 * 添加商圈
	 * @return
	 * @throws BaseException
	 */
	public boolean addBaseBusinessarea(BaseBusinessareaModel o)
			throws BaseException;
	
	/**
	 * 设置配置表
	 * @return
	 * @throws BaseException
	 */
	public boolean addBaseConfig(BaseConfigModel o)
			throws BaseException;
}
