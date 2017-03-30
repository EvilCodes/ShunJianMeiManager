package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUsersubinfoService {

	/************管理端************/
	/**
	 * 根据美发师id查询美发师信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public UsersubinfoModel searchUsersubinfo(int userid)throws BaseException;
	/**
	 * 查询美发师
	 * @param cityid 城市ID
	 * @param userid 美发师ID
	 * @param tel    电话
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param checkstate 审核状态
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List<UsersubinfoModel> searchUserinfo(String cityid,String userid,String tel,String startTime,String endTime,String checkstate,String txtNickname,int page, int rows) throws BaseException;
	/**
	 * 查询美发师
	 * @param cityid 城市ID
	 * @param userid 美发师ID
	 * @param tel    电话
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param checkstate 审核状态
	 * @return
	 * @throws BaseException
	 */
	public int searchUserinfoCount(String cityid,String userid,String tel,String startTime,String endTime,String checkstate,String txtNickname) throws BaseException;

	
	/**
	 * 查询所有的职称等级
	 * @return
	 */
	public List<UserProfessionLevelModel> searchUserProfessionLevel()throws BaseException;
	
	/**
	 * 修改用户扩展信息
	 * @param usersubinfoModel
	 * @param type 1：职称等级，2：审核状态
	 * @return
	 */
	public boolean updateUsersubinfo(UsersubinfoModel usersubinfoModel,int type)throws BaseException;
	
	/**
	 * 根据userId查询美发师扩展信息
	 * @param userId
	 * @return
	 */
	public UsersubinfoModel searchUserSubInfoByUserId(int userId);
	
	/**
	 * 新增美发师扩展信息
	 * @param userSubInfo
	 * @return
	 */
	public int addUserSubInfo(UsersubinfoModel userSubInfo);
	
	/**
	 * 根据userId更新美发师扩展信息
	 * @param userSubInfo
	 * @return
	 */
	public boolean updateUserSubInfoByUserId(UsersubinfoModel userSubInfo) throws BaseException;
	
	/**
	 * 查询基础设置表	
	 * @param configcode 配置编码
	 * @return
	 * @throws BaseException
	 */
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException;
}
