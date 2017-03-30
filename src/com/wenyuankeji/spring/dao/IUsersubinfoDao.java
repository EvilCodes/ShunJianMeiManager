package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUsersubinfoDao {
	
	/************管理端************/
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
	public List<UsersubinfoModel> searchUsersubinfo(String cityid,String userid,String tel,String startTime,String endTime,String checkstate,String txtNickname,int page, int rows) throws BaseException;
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
	
	
	public boolean updateUsersubinfo(UsersubinfoModel usersubinfoModel)throws BaseException;
	
	/**
	 * 根据美发师id查询美发师信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public UsersubinfoModel searchUsersubinfo(int userid)throws BaseException;

	/**
	 * 修改用户扩展信息
	 * @param usersubinfoModel
	 * @param type 1：职称等级，2：审核状态
	 * @return
	 */
	public boolean updateUsersubinfo(UsersubinfoModel usersubinfoModel,int type);
	
	/**
	 * 根据userId查询美发师扩展信息
	 * @param userId
	 * @return
	 */
	public UsersubinfoModel searchUserSubInfoByUserId(int userId);
	
	/**
	 * 查询美发师定级
	 * @return
	 */
	public List<UserProfessionLevelModel> searchUserProfessionLevel();
	
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
}
