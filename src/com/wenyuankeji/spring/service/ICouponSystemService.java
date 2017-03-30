package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface ICouponSystemService {

	/**
	 * 查询基础设置表	
	 * @param configcode 配置编码
	 * @return
	 * @throws BaseException
	 */
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException;
	
	public List<BaseConfigModel> searchBaseConfigList(String configcode)throws BaseException;
	
	
	public boolean updateRegCoupon(int regCouponHl,int regCouponRf,int regCouponTf,int regCouponXc,int regCouponXjc,int regCouponTc)throws BaseException;
	
	/**
	 * 修改配置信息
	 * @param type 1：注册 2：分享
	 * @param minValue
	 * @param maxValue 
	 * @return
	 */
	public boolean updateBaseConfig(String code, String minValue, String maxValue,int type)throws BaseException;
	
	/**
	 * 根据手机号查询用户表
	 * @param mobile 手机号
	 * @return 1： 用户  2： 美发师
	 * @throws BaseException
	 */
	public UserinfoModel searchUserinfo(String mobile,int type)throws BaseException;
	
	/**
	 * 添加优惠券
	 * @return
	 */
	public boolean addUserCoupon(String txtUserCoupon, String mobile, String amount,int userid) throws BaseException;
	
	/**
	 * 修改配置信息
	 * @param configcode 配置编码
	 * @param cofigvalue 值
	 * @return
	 */
	public boolean updateBaseConfig(String configcode, String configvalue) throws BaseException;

	
}
