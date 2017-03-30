package com.wenyuankeji.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.model.BaseStarinfoModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.model.UsersubsearchModel;
import com.wenyuankeji.spring.service.IBaseCityService;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IBaseProvinceService;
import com.wenyuankeji.spring.service.IBaseStarInfoService;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class UsersubinfoController {

	
	@Autowired
	private IUsersubinfoService usersubinfoService;

	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private IBaseProvinceService baseProvinceService;

	@Autowired
	private IBaseCityService baseCityService;
	
	@Autowired
	private IUserPhotoMappingService userPhotoMappingService;
	
	@Autowired
	private IBaseStarInfoService baseStarInfoService;
	
	@Autowired
	private IBaseConfigService baseConfigService;
	
	@Autowired
	private IUserWalletService userWalletService;
	
	
	@Autowired
	private IUtilService utilService;

	/************ 管理端 ************/
	@RequestMapping("goUsersubinfoManage")
	/**
	 * 跳转美发店登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUsersubinfoManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			return "usersubinfo_manage";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("UsersubinfoManage")
	/**
	 * 美发师管理
	 * 
	 * @param request
	 * @param model
	 * @param storeid
	 *            店铺ID
	 * @param paystate
	 *            支付类型
	 * @param createtime
	 *            创建时间
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示条数
	 * @return
	 */
	public @ResponseBody Map<String, Object> UsersubinfoManage(HttpServletRequest request, Model model, String cityid,
			String userid, String tel, String startTime, String endTime, String checkstate, String txtNickname,String page, String rows)
					throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);

		List<UsersubinfoModel> usersubinfoModels = usersubinfoService.searchUserinfo(cityid, userid, tel, startTime, endTime,
				checkstate,txtNickname,intPage, intRows);

		List<UsersubsearchModel> usersubsearchModels = new ArrayList<UsersubsearchModel>();

		for (int i = 0; i < usersubinfoModels.size(); i++) {
			UsersubinfoModel usersubinfoModel = usersubinfoModels.get(i);

			// 重新定义页面显示的实体
			UsersubsearchModel usersubsearchModel = new UsersubsearchModel();
			// ID
			usersubsearchModel.setUserid(usersubinfoModel.getUserid());
			// 手机
			usersubsearchModel.setBindphone(usersubinfoModel.getUserinfoModel().getBindphone());
			// 昵称
			usersubsearchModel.setNickname(usersubinfoModel.getUserinfoModel().getNickname());
			// 服务等级
			usersubsearchModel.setLevelid(usersubinfoModel.getLevelid());
			usersubsearchModel.setLevelname(usersubinfoModel.getUserProfessionLevel().getLevelname());
			// 服务星级
			usersubsearchModel.setScore((int) usersubinfoModel.getScore());
			// 状态
			int checkState = usersubinfoModel.getCheckstate();
			usersubsearchModel.setCheckstate(checkState);
			
			// 美发师钱包余额
			UserWalletModel userWallet = userWalletService.searchUserWallet(usersubinfoModel.getUserid(), 2);
			String balance = "0.0";
			if(userWallet != null){
				balance = String.valueOf(userWallet.getBalance());
			}
			usersubsearchModel.setBalance(balance);

			usersubsearchModel.setCreateData(ShunJianMeiUtil.dateToString(usersubinfoModel.getUserinfoModel().getCreatetime()));
			
			usersubsearchModels.add(usersubsearchModel);
			//定义职称等级
			List<UserProfessionLevelModel> userProfessionLevelList= usersubinfoService.searchUserProfessionLevel();
			model.addAttribute("userProfessionLevelList",userProfessionLevelList);

		}
		// 存放总记录数，必须的
		jsonMap.put("total",
				usersubinfoService.searchUserinfoCount(cityid, userid, tel, startTime, endTime, checkstate,txtNickname));
		// rows键 存放每页记录 list
		jsonMap.put("rows", usersubsearchModels);
		
		return jsonMap;
	}
	
	@RequestMapping("/UsersubinfoUpdateState")
	/**
	 * 修改用户状态
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> UsersubinfoUpdateState(int userid, int checkstate,
			Model model, HttpSession session,HttpServletRequest request) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		// 构造实体
		UsersubinfoModel usersubinfoModel = new UsersubinfoModel();
		usersubinfoModel.setUserid(userid);
		usersubinfoModel.setCheckstate(checkstate);
		try {
			if (usersubinfoService.updateUsersubinfo(usersubinfoModel, 2)) {
				editInfoMap.put("editInfo", true);
				editInfoMap.put("message", "审核状态修改成功！");
			} else {

				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "审核状态修改失败！");
			}
		} catch (Exception e) {
			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "审核状态修改失败！");
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return editInfoMap;
	}
	
	@RequestMapping("goDetailUserSubInfo")
	/**
	 * 跳转美发师详情
	 * @param request
	 * @param model
	 * @return
	 */
	public String goDetailUserSubInfo(int userId,HttpServletRequest request, Model model) {
		model.addAttribute("userId", userId);
		
		try {
			//查询定级
			List<UserProfessionLevelModel> UserProfessionLevelList = usersubinfoService.searchUserProfessionLevel();
			model.addAttribute("UserProfessionLevelList", UserProfessionLevelList);
			
			UsersubinfoModel o = usersubinfoService.searchUserSubInfoByUserId(userId);
			//查询美发师等级
			model.addAttribute("levelid", o.getLevelid());
			
			//查询美发师状态
			model.addAttribute("checkstate", o.getCheckstate());
			
			//美发师分成
			model.addAttribute("allocation", o.getAllocation());
			
			model.addAttribute("userId", userId);
			
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		
		
		return "usersubinfo_detail";
	}
	
	
	@RequestMapping("/addLevelAndCheckstate")
	/**
	 * 根据省份ID查询城市
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> addLevelAndCheckstate(int userid,int levelid,int checkstate, 
			String allocation,int isType,Model model, HttpServletRequest request){
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		
		try {
			UsersubinfoModel o = usersubinfoService.searchUserSubInfoByUserId(userid);
			
			//职称等级
			o.setLevelid(levelid);
			//状态
			o.setCheckstate(checkstate);
			//分成
			if (isType == 0) {//输入框的分成
				o.setAllocation(allocation);
			}else{//默认配置表中的美发师分成
				BaseConfigModel baseConfigModel = usersubinfoService.searchBaseConfigByCode(Constant.CONFIGCODE_14);
				if (baseConfigModel != null) {
					o.setAllocation(baseConfigModel.getConfigvalue());
				}else{
					o.setAllocation("0");
				}
			}
			
			try {
				if (usersubinfoService.updateUsersubinfo(o, 0)) {
					outMap.put("info", true);
				}else {
					outMap.put("info", false);
					outMap.put("msg", "设置失败！");
				}
			} catch (Exception e) {
				outMap.put("info", false);
				outMap.put("msg", "设置失败！");
				// 保存异常信息
				utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return outMap;
	}
	
	
	@RequestMapping("goUsersubinfo_verification")
	/**
	 * 跳转美发师审核信息
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUsersubinfo_verification(int userId,HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession();
//		String userId = session.getAttribute("userId").toString();
		try {
			UserinfoModel userInfoModel = userinfoService.searchUserinfoById(userId);
			UsersubinfoModel userSubInfoModel = usersubinfoService.searchUserSubInfoByUserId(userId);
			model.addAttribute("userInfoModel", userInfoModel);
			model.addAttribute("userSubInfoModel", userSubInfoModel);
			model.addAttribute("errorMsg", "");

			List<BaseProvinceModel> baseProvinceList = new ArrayList<BaseProvinceModel>();
			List<BaseCityModel> baseCityModelList = new ArrayList<BaseCityModel>();

			//省份
			baseProvinceList = baseProvinceService.searchBaseProvince();
			//省份默认
			BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
			baseProvinceModel.setProvinceid(0);
			baseProvinceModel.setProvincename("请选择");
			baseProvinceList.add(0, baseProvinceModel);
			//城市
			if (userInfoModel.getProvinceid() == 0) {
				BaseCityModel baseCityModel = new BaseCityModel();
				baseCityModel.setCityid(0);
				baseCityModel.setCityname("请选择");
				baseCityModelList.add(0,baseCityModel);
			}else {
				baseCityModelList = baseCityService.searchBaseCityByProvinceId(userInfoModel.getProvinceid());
			}
			
			UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
			
			//身份证正面
			UserPhotoMappingModel  userPhotoMappingModel2 = userPhotoMappingService.searchUserPhoto(userInfoModelSession.getUserid(), 2);
			if(null != userPhotoMappingModel2 && userPhotoMappingModel2.getBasePicture() != null){
				String path = userPhotoMappingModel2.getBasePicture().getPicturepath();
				String imgName = path.substring(8);
				model.addAttribute("imgFileName_zm",  path);
				model.addAttribute("hidfileName_zm", imgName);
			}else{
				model.addAttribute("imgFileName_zm",  "images/defaultAvatar.png");
				
			}
			
			//身份证背面
			UserPhotoMappingModel  userPhotoMappingModel3 = userPhotoMappingService.searchUserPhoto(userInfoModelSession.getUserid(), 3);
			if(null != userPhotoMappingModel3 && userPhotoMappingModel3.getBasePicture() != null){
				String path = userPhotoMappingModel3.getBasePicture().getPicturepath();
				String imgName = path.substring(8);
				model.addAttribute("imgFileName_bm",  path);
				model.addAttribute("hidfileName_bm", imgName);
			}else{
				model.addAttribute("imgFileName_bm",  "images/defaultAvatar.png");
				
			}
			
			//手持身份证
			UserPhotoMappingModel  userPhotoMappingModel4 = userPhotoMappingService.searchUserPhoto(userInfoModelSession.getUserid(), 4);
			if(null != userPhotoMappingModel4 && userPhotoMappingModel4.getBasePicture() != null){
				String path = userPhotoMappingModel4.getBasePicture().getPicturepath();
				String imgName = path.substring(8);
				model.addAttribute("imgFileName_sc",  path);
				model.addAttribute("hidfileName_sc", imgName);
			}else{
				model.addAttribute("imgFileName_sc",  "images/defaultAvatar.png");
				
			}

			model.addAttribute("baseProvinceList", baseProvinceList);
			model.addAttribute("baseCityModelList", baseCityModelList);
			model.addAttribute("userId", userId);
		} catch (BaseException e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return "usersubinfo_verification";
	}
	
	@RequestMapping("doSaveUserSubInfo")
	public String doSaveUserSubInfo(HttpServletRequest request, Model model, String userid, String truename, String email,
			int provinceid, int cityid, String address, String household, String contact, String relationship,
			String contactmobile, String idcard,int buttonFlag, String fileName_zm,String fileName_bm,String fileName_sc)
					throws BaseException, IOException {
		//HttpSession session = request.getSession();

		int userId = 0;
		if(userid != ""){
			userId = Integer.parseInt(userid);
		}
		String result = "";
		try {
			//System.out.println("*****************"+truename+"*****************");
			//美发师扩展信息
			UsersubinfoModel userSubInfo = new UsersubinfoModel();
			userSubInfo.setTruename(truename);
			userSubInfo.setEmail(email);
			userSubInfo.setAddress(address);
			userSubInfo.setHousehold(household);
			userSubInfo.setContact(contact);
			userSubInfo.setContactmobile(contactmobile);
			userSubInfo.setRelationship(relationship);
			userSubInfo.setIdcard(idcard);
			userSubInfo.setCreatetime(new Date());
	
			userSubInfo.setLevelid(1);// 资深发型师
			BaseStarinfoModel baseStarinfoModel = new BaseStarinfoModel();
			baseStarinfoModel.setStarid(1);
			userSubInfo.setBaseStarinfoModel(baseStarinfoModel);
			userSubInfo.setStarid(1);// 摩羯座
	
			UserinfoModel userInfoModel = new UserinfoModel();
			userInfoModel.setProvinceid(provinceid);
			userInfoModel.setCityid(cityid);
			
			//判断session的用户是否为空
			if (userId > 0) {
				//userId = Integer.parseInt(session.getAttribute("userId").toString());
				userSubInfo.setUserid(userId);
				//userSubInfo.setCheckstate(0);//审核状态：未提交审核
				userInfoModel.setUserid(userId);
				UsersubinfoModel userSubInfoTemp = usersubinfoService.searchUserSubInfoByUserId(userId);
				UserinfoModel userInfoTemp = userinfoService.searchUserinfoById(userId);
				
				saveImage(fileName_zm,userId,2,0,request);//保存身份证正面
				saveImage(fileName_bm,userId,3,0,request);//保存身份证背面
				saveImage(fileName_sc,userId,4,0,request);//保存手持身份证
				
				//判断用户是否为新增用户，否则执行修改
				if (0 == userSubInfoTemp.getUserid()) {
					// 信息不存在，新增美发师扩展信息
					int addResult = usersubinfoService.addUserSubInfo(userSubInfo);
					if (addResult > 0) {
						userInfoTemp.setProvinceid(provinceid);
						userInfoTemp.setCityid(cityid);
						userInfoTemp.setUpdatetime(new Date());
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								result = "usersubinfo_verification";
							}else{
								result = "usersubinfo_information";
							}
							model.addAttribute("errorMsg", "保存成功！");
						} else {
							result = "usersubinfo_verification";
							model.addAttribute("errorMsg", "保存失败！");
						}
						
					} else {
						// 新增失败
						result = "error";
						model.addAttribute("errorMsg", "保存失败！");
					}
				} else {
					// 信息存在，更新美发师扩展信息
					userSubInfoTemp.setTruename(truename);
					userSubInfoTemp.setEmail(email);
					userSubInfoTemp.setAddress(address);
					userSubInfoTemp.setHousehold(household);
					userSubInfoTemp.setContact(contact);
					userSubInfoTemp.setContactmobile(contactmobile);
					userSubInfoTemp.setRelationship(relationship);
					userSubInfoTemp.setIdcard(idcard);
					
					/*if(userSubInfoTemp.getCheckstate() != 1 && userSubInfoTemp.getCheckstate() != 2){
						userSubInfoTemp.setCheckstate(0);//审核状态：未提交审核
					}*/
					
					boolean updateResult = usersubinfoService.updateUserSubInfoByUserId(userSubInfoTemp);
					if (updateResult) {
						// 更新成功
						// 更新userinfo表里的省市信息
						userInfoTemp.setProvinceid(provinceid);
						userInfoTemp.setCityid(cityid);
						userInfoTemp.setUpdatetime(new Date());
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								result = "usersubinfo_verification";
							}else{
								result = "usersubinfo_information";
							}
							model.addAttribute("errorMsg", "保存成功！");
						} else {
							result = "usersubinfo_verification";
							model.addAttribute("errorMsg", "保存失败！");
						}
					} else {
						// 更新失败
						result = "error";
						model.addAttribute("errorMsg", "保存失败！");
					}
				}
				
				if(buttonFlag == 0){
					//跳转第一页
					model.addAttribute("userInfoModel", userInfoTemp);
					model.addAttribute("userSubInfoModel", userSubInfo);
					List<BaseProvinceModel> baseProvinceList = new ArrayList<BaseProvinceModel>();
					List<BaseCityModel> baseCityModelList = new ArrayList<BaseCityModel>();
					
					//省份
					baseProvinceList = baseProvinceService.searchBaseProvince();
					BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
					baseProvinceModel.setProvinceid(0);
					baseProvinceModel.setProvincename("请选择");
					baseProvinceList.add(0, baseProvinceModel);
					//城市
					if (userInfoModel.getProvinceid() == 0) {
						BaseCityModel baseCityModel = new BaseCityModel();
						baseCityModel.setCityid(0);
						baseCityModel.setCityname("请选择");
						baseCityModelList.add(0,baseCityModel);
					}else {
						baseCityModelList = baseCityService.searchBaseCityByProvinceId(userInfoModel.getProvinceid());
					}
					model.addAttribute("baseProvinceList", baseProvinceList);
					model.addAttribute("baseCityModelList", baseCityModelList);
					
					
					setImageAttribute(userInfoTemp.getUserid(), 2, "imgFileName_zm","hidfileName_zm","defaultAvatar.png", model, request); //设置身份证正面到Attribute
					setImageAttribute(userInfoTemp.getUserid(), 3, "imgFileName_bm","hidfileName_bm","defaultAvatar.png", model, request); //设置身份证背面到Attribute
					setImageAttribute(userInfoTemp.getUserid(), 4, "imgFileName_sc","hidfileName_sc","defaultAvatar.png", model, request); //设置手持身份证到Attribute
					
				}else{
					
					UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
					UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);

					List<BaseStarinfoModel> baseStarInfoList = new ArrayList<BaseStarinfoModel>();

					baseStarInfoList = baseStarInfoService.searchBaseStarInfo();
					BaseStarinfoModel baseStarinfoModelSession = new BaseStarinfoModel();
					baseStarinfoModelSession.setStarid(0);
					baseStarinfoModelSession.setStarname("请选择");
					baseStarInfoList.add(0, baseStarinfoModelSession);
					
					List<BaseConfigModel> hobbyList = baseConfigService.searchBaseConfig("hobby");
					List<BaseConfigModel> hairstyleList = baseConfigService.searchBaseConfig("hairstyle");

					model.addAttribute("hobbyList", hobbyList);
					model.addAttribute("hairstyleList", hairstyleList);

					model.addAttribute("baseStarInfoList", baseStarInfoList);
					model.addAttribute("userInfoModel", userInfoModelSession);
					model.addAttribute("userSubInfoModel", userSubInfoModelSession);
					model.addAttribute("errorMsg", "");
					
					
					//页面中显示对应的图片
					setImageAttribute(userInfoModelSession.getUserid(), 1, "userPhoto","hidFileName_tx", "defaultAvatar.png",model, request); //设置我的头像到Attribute
					setImageAttribute(userInfoModelSession.getUserid(), 7, "userImage","hidFileName_xx","defaultAvatar.png", model, request); //设置我的形象到Attribute
					setImageAttribute(userInfoModelSession.getUserid(), 6, "userWork","hidFileName_zp","defaultAvatar.png", model, request); //设置我的代表作品到Attribute
					
				}
			}
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		model.addAttribute("userId", userId);
		return result;
	}
	
	/**
	 * 保存图片到数据库
	 * @param fileName
	 * @param userId
	 * @param type
	 * @param sortNum 0为单张图片,1、2、3、4为多张图片
	 * @param request
	 */
	public void saveImage(String fileName,int userId,int type,int sortNum,HttpServletRequest request){
		//Date date = new Date();
		if(!"".equals(fileName)){
			String serverPath = request.getServletContext().getRealPath("/userImg/");
			try {
				userPhotoMappingService.addOrUpdImg(userId, type, sortNum,fileName, serverPath);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置图片的Attribute
	 * @param userId
	 * @param type 图片类型
	 * @param imgFileName
	 * @param hidFileName
	 * @param defaultImagePath 默认图片路径
	 * @param model
	 * @param request
	 */
	public void setImageAttribute(int userId,int type,String imgFileName,String hidFileName,String defaultImagePath,Model model,HttpServletRequest request){
		try {
			if(type == Constant.IMAGE_TYPE_06 || type == Constant.IMAGE_TYPE_07){
				List<UserPhotoMappingModel> userPhotoMappingModels = userPhotoMappingService.searchUserPhotos(userId, type);
				int size = 0;
				if(type == Constant.IMAGE_TYPE_07){
					size = 3;//我的形象循环3次
				}else{
					size = 4;//我的作品循环4次
				}
				
				//有图片，设置图片;没有图片，设置默认图片
				for (int i = 0; i < size; i++) {
					if(null != userPhotoMappingModels){
						for (int j = 0; j < userPhotoMappingModels.size(); j++) {
							UserPhotoMappingModel userPhotoMappingModel = userPhotoMappingModels.get(j);
							if(null != userPhotoMappingModel){
								if((i+1) != userPhotoMappingModel.getSortnum()){
									model.addAttribute(imgFileName+(i+1),  "images/"+defaultImagePath);
								}else{
									if(userPhotoMappingModel.getBasePicture() != null){
										String path = userPhotoMappingModel.getBasePicture().getPicturepath();
										String imgName = path.substring(8);
										model.addAttribute(imgFileName+(i+1), path);
										model.addAttribute(hidFileName+(i+1), imgName);
										break;
									}
								}
							}
						}
					}else{
						model.addAttribute(imgFileName+(i+1),  "images/"+defaultImagePath);
					}
				}
			}else{
				UserPhotoMappingModel userPhotoMappingModel = userPhotoMappingService.searchUserPhoto(userId, type);
				if(null != userPhotoMappingModel && userPhotoMappingModel.getBasePicture() != null){
					String path = userPhotoMappingModel.getBasePicture().getPicturepath();
					String imgName = path.substring(8);
					model.addAttribute(imgFileName,  path);
					model.addAttribute(hidFileName, imgName);
				}else{
					model.addAttribute(imgFileName,  "images/"+defaultImagePath);
				}
			}
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
	}
	
	@RequestMapping("goUsersubinfo_information")
	/**
	 * 查看业务信息
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUsersubinfo_information(int userId,HttpServletRequest request, Model model) {
		try {
			UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
			UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);

			List<BaseStarinfoModel> baseStarInfoList = new ArrayList<BaseStarinfoModel>();

			baseStarInfoList = baseStarInfoService.searchBaseStarInfo();
			BaseStarinfoModel baseStarinfoModelSession = new BaseStarinfoModel();
			baseStarinfoModelSession.setStarid(0);
			baseStarinfoModelSession.setStarname("请选择");
			baseStarInfoList.add(0, baseStarinfoModelSession);
			
			List<BaseConfigModel> hobbyList = baseConfigService.searchBaseConfig("hobby");
			List<BaseConfigModel> hairstyleList = baseConfigService.searchBaseConfig("hairstyle");

			model.addAttribute("hobbyList", hobbyList);
			model.addAttribute("hairstyleList", hairstyleList);

			model.addAttribute("baseStarInfoList", baseStarInfoList);
			model.addAttribute("userInfoModel", userInfoModelSession);
			model.addAttribute("userSubInfoModel", userSubInfoModelSession);
			model.addAttribute("errorMsg", "");
			model.addAttribute("userId", userId);
			
			setImageAttribute(userInfoModelSession.getUserid(), 1, "userPhoto","hidFileName_tx", "defaultAvatar.png",model, request); //设置我的头像到Attribute
			setImageAttribute(userInfoModelSession.getUserid(), 7, "userImage","hidFileName_xx","defaultAvatar.png", model, request); //设置我的形象到Attribute
			setImageAttribute(userInfoModelSession.getUserid(), 6, "userWork","hidFileName_zp","defaultAvatar.png", model, request); //设置我的代表作品到Attribute
			
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return "usersubinfo_information";
	}

	@RequestMapping("doSaveUserInfo")
	/**
	 * 保存美发师验证信息
	 * 
	 * @param request
	 * @param model
	 * @param bindphone
	 * @param password
	 * @param verificationCode
	 * @return
	 */
	public String doSaveUserInfo(HttpServletRequest request, Model model, String userid, String nickname, int starid, int workinglife,
			String intro, String workhistory, String hairstyle, String hobbies,int buttonFlag,
			String fileName_tx,String fileName_xx1,String fileName_xx2,String fileName_xx3,String fileName_zp1,String fileName_zp2,String fileName_zp3,String fileName_zp4) {
		String result = "";
		String errorMsg = "";
		int userId = 0;
		if(userid != ""){
			userId = Integer.parseInt(userid);
		}

		try {
			UsersubinfoModel userSubInfo = new UsersubinfoModel();

			userSubInfo.setStarid(starid);
			userSubInfo.setWorkinglife(workinglife);
			userSubInfo.setIntro(intro);
			userSubInfo.setWorkhistory(workhistory);
			userSubInfo.setHairstyle(hairstyle);
			userSubInfo.setHobbies(hobbies);

			UserinfoModel userInfoModel = new UserinfoModel();
			userInfoModel.setNickname(nickname);

			//HttpSession session = request.getSession();
			if (userId > 0) {
				//userId = Integer.parseInt(session.getAttribute("userId").toString());
				
				saveImage(fileName_tx,userId,1,0,request);//保存我的头像
				saveImage(fileName_xx1,userId,7,1,request);//保存我的形象1
				saveImage(fileName_xx2,userId,7,2,request);//保存我的形象2
				saveImage(fileName_xx3,userId,7,3,request);//保存我的形象3
				saveImage(fileName_zp1,userId,6,1,request);//保存我的代表作品1
				saveImage(fileName_zp2,userId,6,2,request);//保存我的代表作品2
				saveImage(fileName_zp3,userId,6,3,request);//保存我的代表作品3
				saveImage(fileName_zp4,userId,6,4,request);//保存我的代表作品4
				
				
				userSubInfo.setUserid(userId);
				/*if(buttonFlag == 1){
					userSubInfo.setCheckstate(1);//审核状态：审核中
				}else{
					userSubInfo.setCheckstate(0);//审核状态：未提交审核
				}*/
				
				userInfoModel.setUserid(userId);
				UsersubinfoModel userSubInfoTemp = usersubinfoService.searchUserSubInfoByUserId(userId);
				UserinfoModel userInfoTemp = userinfoService.searchUserinfoById(userId);
				if (0 == userSubInfoTemp.getUserid()) {
					// 信息不存在，新增美发师扩展信息
					int addResult = usersubinfoService.addUserSubInfo(userSubInfo);
					if (addResult > 0) {
						// 新增成功
						// 更新userinfo表里的昵称信息
						userInfoTemp.setNickname(nickname);
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								errorMsg = "保存成功！";
								result = "usersubinfo_information";
							}else{
								errorMsg = "填写完毕，请等待管理员审核。";
								result = "usersubinfo_verification";
							}
						} else {
							errorMsg = "操作失败！";
							result = "usersubinfo_information";
						}

					} else {
						// 新增失败
						errorMsg = "操作失败！";
						result = "usersubinfo_information";
					}
				} else {
					// 信息存在，更新美发师扩展信息
					userSubInfoTemp.setStarid(starid);
					userSubInfoTemp.setWorkinglife(workinglife);
					userSubInfoTemp.setIntro(intro);
					userSubInfoTemp.setWorkhistory(workhistory);
					userSubInfoTemp.setHairstyle(hairstyle);
					userSubInfoTemp.setHobbies(hobbies);
					/*if(buttonFlag == 1){
						userSubInfoTemp.setCheckstate(1);//审核状态：审核中
					}else{
						userSubInfoTemp.setCheckstate(0);//审核状态：未提交审核
					}*/
					boolean updateResult = usersubinfoService.updateUserSubInfoByUserId(userSubInfoTemp);
					if (updateResult) {
						// 更新成功
						// 更新userinfo表里的昵称信息
						userInfoTemp.setNickname(nickname);
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								errorMsg = "保存成功！";
								result = "usersubinfo_information";
							}else{
								errorMsg = "填写完毕，请等待管理员审核。";
								result = "usersubinfo_verification";
							}
						} else {
							errorMsg = "操作失败！";
							result = "usersubinfo_information";
						}
					} else {
						// 更新失败
						errorMsg = "操作失败！";
						result = "usersubinfo_information";
					}
				}
			} else {
				errorMsg = "操作失败！";
				result = "usersubinfo_information";
			}
			if(buttonFlag == 0){
				//保存操作，跳转到当前页面
				UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
				UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);

				List<BaseStarinfoModel> baseStarInfoList = new ArrayList<BaseStarinfoModel>();

				baseStarInfoList = baseStarInfoService.searchBaseStarInfo();
				BaseStarinfoModel baseStarinfoModelSession = new BaseStarinfoModel();
				baseStarinfoModelSession.setStarid(0);
				baseStarinfoModelSession.setStarname("请选择");
				baseStarInfoList.add(0, baseStarinfoModelSession);
				
				List<BaseConfigModel> hobbyList = baseConfigService.searchBaseConfig("hobby");
				List<BaseConfigModel> hairstyleList = baseConfigService.searchBaseConfig("hairstyle");

				model.addAttribute("hobbyList", hobbyList);
				model.addAttribute("hairstyleList", hairstyleList);

				model.addAttribute("baseStarInfoList", baseStarInfoList);
				model.addAttribute("userInfoModel", userInfoModelSession);
				model.addAttribute("userSubInfoModel", userSubInfoModelSession);
				
				setImageAttribute(userInfoModelSession.getUserid(), 1, "userPhoto","hidFileName_tx", "defaultAvatar.png",model, request); //设置我的头像到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 7, "userImage","hidFileName_xx","defaultAvatar.png", model, request); //设置我的形象到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 6, "userWork","hidFileName_zp","defaultAvatar.png", model, request); //设置我的代表作品到Attribute
				
				model.addAttribute("errorMsg", errorMsg);
			}else{
				UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
				UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);
				model.addAttribute("userInfoModel", userInfoModelSession);
				model.addAttribute("userSubInfoModel", userSubInfoModelSession);
				model.addAttribute("errorMsg", "填写完毕，请等待管理员审核。");

				List<BaseProvinceModel> baseProvinceList = new ArrayList<BaseProvinceModel>();
				List<BaseCityModel> baseCityModelList = new ArrayList<BaseCityModel>();

				//省份
				baseProvinceList = baseProvinceService.searchBaseProvince();
				//省份默认
				BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
				baseProvinceModel.setProvinceid(0);
				baseProvinceModel.setProvincename("请选择");
				baseProvinceList.add(0, baseProvinceModel);
				//城市
				if (userInfoModelSession.getProvinceid() == 0) {
					BaseCityModel baseCityModel = new BaseCityModel();
					baseCityModel.setCityid(0);
					baseCityModel.setCityname("请选择");
					baseCityModelList.add(0,baseCityModel);
				}else {
					baseCityModelList = baseCityService.searchBaseCityByProvinceId(userInfoModelSession.getProvinceid());
				}
				
				
				setImageAttribute(userInfoModelSession.getUserid(), 2, "imgFileName_zm","hidfileName_zm","defaultAvatar.png", model, request); //设置身份证正面到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 3, "imgFileName_bm","hidfileName_bm","defaultAvatar.png", model, request); //设置身份证背面到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 4, "imgFileName_sc","hidfileName_sc","defaultAvatar.png", model, request); //设置手持身份证到Attribute
				
				model.addAttribute("baseProvinceList", baseProvinceList);
				model.addAttribute("baseCityModelList", baseCityModelList);	
			}
				
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		model.addAttribute("userId", userId);
		return result;
	}
	
}