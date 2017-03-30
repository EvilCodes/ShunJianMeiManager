package com.wenyuankeji.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.model.BaseDistrictModel;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IBaseBusinessareaService;
import com.wenyuankeji.spring.service.IBaseCityService;
import com.wenyuankeji.spring.service.IBaseDistrictService;
import com.wenyuankeji.spring.service.IBaseProvinceService;
import com.wenyuankeji.spring.service.IStorePhotoMappingService;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.MD5Util;

@Controller
public class StoreinfoController {

	@Autowired
	private IStoreinfoService storeinfoService;
	
	@Autowired
	private IStorePhotoMappingService storePhotoMappingService;
	
	@Autowired
	private IBaseProvinceService baseProvinceService;

	@Autowired
	private IBaseCityService baseCityService;

	@Autowired
	private IBaseDistrictService baseDistrictService;

	@Autowired
	private IBaseBusinessareaService baseBusinessareaService;

	@Autowired
	private IUserWalletService userWalletService;
	

	/************ 管理端 ************/
	@RequestMapping("goStoreinfoManage")
	/**
	 * 跳转美发店登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goStoreinfoManage(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			return "storeinfo_manage";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("StoreinfoManage")
	/**
	 * 获取商户列表
	 * @param request
	 * @param model
	 * @param storeid
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @param name
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public @ResponseBody Map<String, Object> StoreinfoManage(HttpServletRequest request, Model model,
			String storeid, String startTime, String endTime, String state,String name, String page, String rows)
					throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);

		List<StoreinfoModel> storeinfoModels = storeinfoService.searchStoreinfo(storeid, startTime, endTime, state, name, intPage, intRows);
		
		// 存放总记录数，必须的
		jsonMap.put("total", storeinfoService.searchStoreinfoCount(storeid, startTime, endTime, state, name));
		// rows键 存放每页记录 list
	    jsonMap.put("rows", storeinfoModels);

		return jsonMap;
	}
	
	@RequestMapping("StoreinfoUpdateState")
	/**
	 * 修改用户状态
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> StoreinfoUpdateState(int storeid, int state,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		// 构造实体
		StoreinfoModel storeinfoModel = new StoreinfoModel();
		storeinfoModel.setStoreid(storeid);
		storeinfoModel.setState(state);
		if (storeinfoService.updateStoreinfoModel(storeinfoModel)) {
			editInfoMap.put("editInfo", true);
			editInfoMap.put("message", "审核状态修改成功！");
		} else {

			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "审核状态修改失败！");
		}
		return editInfoMap;
	}
	
	@RequestMapping("initStoreInfo")
	/**
	 * 商户生成
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> initStoreInfo(
			Model model, HttpSession session) throws Exception {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		// 构造实体
		StoreinfoModel storeinfoModel = new StoreinfoModel();

		int randomName = (int) (Math.random() * 1000000);
		int randomPass = (int) (Math.random() * 1000000);
		String defaultUserName = String.valueOf(randomName);
		String defaultPassword = String.valueOf(randomPass);
		
		storeinfoModel.setUsername(defaultUserName);//默认用户名为12345678900
		storeinfoModel.setPassword(MD5Util.Encryption(defaultPassword));//默认密码为123456
		storeinfoModel.setCreatetime(new Date()); //创建时间
		storeinfoModel.setState(0);//默认未审核状态
		int result = storeinfoService.addInitStoreinfo(storeinfoModel);
		if (result > 0) {
			//生成钱包
			UserWalletModel o = new UserWalletModel();
			o.setOwnerid(result);
			o.setBalance(0);
			//3商户
			o.setOwnertype(3);
			o.setCreatetime(new Date());
			o.setUpdatetime(new Date());
			//添加钱包
			userWalletService.addUserWallet(o);
			editInfoMap.put("editInfo", true);
			editInfoMap.put("message", "初始化成功！用户名：" + defaultUserName
					+ "密码：" + defaultPassword);
		} else {

			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "初始化失败！");
		}
		return editInfoMap;
	}
	
	
	@RequestMapping("/getBaseProvinces")
	/**
	 * 查询所有省份
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getBaseProvinces(Model model,
			HttpSession session) throws BaseException {
		Map<String, Object> baseProvinceMap = new HashMap<String, Object>();
		List<BaseProvinceModel> baseProvinceList = baseProvinceService
				.searchBaseProvince();
		
		BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
		baseProvinceModel.setProvinceid(0);
		baseProvinceModel.setProvincename("请选择");
		baseProvinceList.add(0, baseProvinceModel);

		baseProvinceMap.put("selInfo", true);
		baseProvinceMap.put("baseProvinceList", baseProvinceList);
		return baseProvinceMap;
	}

	@RequestMapping("/getBaseBusinessareas/{city_id}")
	/**
	 * 查根据城市ID询商圈
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getBaseBusinessareas(
			@PathVariable int city_id, Model model, HttpSession session)
			throws BaseException {
		List<BaseBusinessareaModel> baseBusinessareaList = baseBusinessareaService
				.searchBaseBusinessarea(city_id);

		Map<String, Object> baseBusinessareaMap = new HashMap<String, Object>();
		
		BaseBusinessareaModel baseBusinessareaModel = new BaseBusinessareaModel();
		baseBusinessareaModel.setAreaid(0);
		baseBusinessareaModel.setAreaname("请选择");
		baseBusinessareaList.add(0, baseBusinessareaModel);

		baseBusinessareaMap.put("selInfo", true);
		baseBusinessareaMap.put("baseBusinessareaList", baseBusinessareaList);
		
		return baseBusinessareaMap;
	}

	@RequestMapping("/getBaseCitys/{province_id}")
	/**
	 * 根据省份ID查询城市
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getProfessions(
			@PathVariable int province_id, Model model, HttpSession session)
			throws BaseException {
		List<BaseCityModel> baseCityList = baseCityService
				.searchBaseCityByProvinceId(province_id);

		Map<String, Object> baseCityMap = new HashMap<String, Object>();
		
		BaseCityModel baseCityModel = new BaseCityModel();
		baseCityModel.setCityid(0);
		baseCityModel.setCityname("请选择");
		baseCityList.add(0, baseCityModel);

		baseCityMap.put("selInfo", true);
		baseCityMap.put("baseCityList", baseCityList);
		
		return baseCityMap;
	}

	@RequestMapping("/getBaseDistricts/{city_id}")
	/**
	 * 根据城市ID查询区县
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getBaseDistricts(
			@PathVariable int city_id, Model model, HttpSession session)
			throws BaseException {
		List<BaseDistrictModel> baseDistrictList = baseDistrictService
				.searchBaseDistrictByCityId(city_id);
		Map<String, Object> baseDistrictMap = new HashMap<String, Object>();
		
		BaseDistrictModel baseDistrictModel = new BaseDistrictModel();
		baseDistrictModel.setCityid(0);
		baseDistrictModel.setDistrictname("请选择");
		baseDistrictList.add(0, baseDistrictModel);

		baseDistrictMap.put("selInfo", true);
		baseDistrictMap.put("baseDistrictList", baseDistrictList);
		
		return baseDistrictMap;
	}
	
	/**
	 * 初始化页面数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public void setPageData(StoreinfoModel storeinfo, Model model)
			throws BaseException {

		// 省份
		List<BaseProvinceModel> baseProvinceList = baseProvinceService
				.searchBaseProvince();
		BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
		baseProvinceModel.setProvinceid(0);
		baseProvinceModel.setProvincename("请选择");
		baseProvinceList.add(0, baseProvinceModel);

		model.addAttribute("baseProvinceList", baseProvinceList);
		model.addAttribute("provinceid", storeinfo.getProvinceid());

		// 城市
		List<BaseCityModel> baseCityList = baseCityService
				.searchBaseCityByProvinceId(storeinfo.getProvinceid());

		BaseCityModel baseCityModel = new BaseCityModel();
		baseCityModel.setCityid(0);
		baseCityModel.setCityname("请选择");
		baseCityList.add(0, baseCityModel);

		model.addAttribute("baseCityList", baseCityList);
		model.addAttribute("cityid", storeinfo.getCityid());

		// 区县
		List<BaseDistrictModel> baseDistrictList = baseDistrictService
				.searchBaseDistrictByCityId(storeinfo.getCityid());
		BaseDistrictModel baseDistrictModel = new BaseDistrictModel();
		baseDistrictModel.setCityid(0);
		baseDistrictModel.setDistrictname("请选择");
		baseDistrictList.add(0, baseDistrictModel);

		model.addAttribute("baseDistrictList", baseDistrictList);
		model.addAttribute("districtid", storeinfo.getDistrictid());

		// 商圈
		List<BaseBusinessareaModel> baseBusinessareaList = baseBusinessareaService
				.searchBaseBusinessarea(storeinfo.getCityid());
		BaseBusinessareaModel baseBusinessareaModel = new BaseBusinessareaModel();
		baseBusinessareaModel.setAreaid(0);
		baseBusinessareaModel.setAreaname("请选择");
		baseBusinessareaList.add(0, baseBusinessareaModel);

		model.addAttribute("baseBusinessareaList", baseBusinessareaList);
		model.addAttribute("areaid", storeinfo.getAreaid());

		// 营业时间
		String businesshours = storeinfo.getBusinesshours();
		if (businesshours != null && businesshours != "") {
			String businesshoursStr[] = storeinfo.getBusinesshours().split("-");
			String businesshoursStart = businesshoursStr[0].toString();
			String businesshoursEnd = businesshoursStr[1].toString();
			// 开始营业时间
			model.addAttribute("businesshoursStart", businesshoursStart);
			// 结束营业时间
			model.addAttribute("businesshoursEnd", businesshoursEnd);
		} else {
			// 默认开始营业时间
			model.addAttribute("businesshoursStart", "09:00");
			// 默认营业时间
			model.addAttribute("businesshoursEnd", "09:00");
		}

		StorePhotoMappingModel storePhotoMapping1 = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 3);
		StorePhotoMappingModel storePhotoMapping2 = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 1);
		StorePhotoMappingModel storePhotoMapping3 = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 2);

		if (storePhotoMapping1 != null) {
			model.addAttribute("fileName1", storePhotoMapping1.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("fileName1", "images/defaultAvatar.png");
		}

		if (storePhotoMapping2 != null) {
			model.addAttribute("fileName2", storePhotoMapping2.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("fileName2", "images/defaultAvatar.png");
		}
		if (storePhotoMapping3 != null) {
			model.addAttribute("fileName3", storePhotoMapping3.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("fileName3", "images/defaultAvatar.png");
		}
	}

	@RequestMapping("goStoreinfoValidateInfo/{storeid}")
	/**
	 * 跳转美发店验证信息
	 * @param request
	 * @param model
	 * @return
	 */
	public String goStoreinfoValidateInfo(HttpServletRequest request,
			Model model, @PathVariable int storeid) throws NumberFormatException, BaseException {
		
		StoreinfoModel storeinfo = storeinfoService.searchStoreinfoModel(storeid);

		setPageData(storeinfo, model);

		model.addAttribute("storeinfo", storeinfo);

		return "storeinfo_validateinfo";
	}
	@RequestMapping("/StoreinfoValidateInfo")
	/**
	 * 美发店验证信息
	 * @param request
	 * @param model
	 * @param storeName 商户名称
	 * @param provinceid 省份ID
	 * @param cityid 城市ID
	 * @param districtid 区ID
	 * @param address 地址
	 * @param tel 电话
	 * @param carnumber 车位数
	 * @param businesshours 营业时间
	 * @param areaid 商圈ID
	 * @param empiricalmode 经营方式
	 * @param intro 简介
	 * @param bossname 负责人名字
	 * @param bossmobile 负责人手机
	 * @param storemanagername 店长名字
	 * @param storemanagermobile 店长手机
	 * @return
	 */
	public String StoreinfoValidateInfo(HttpServletRequest request,
			Model model,String storeid, String storeName, String provinceid, String cityid,
			String districtid, String address, String tel, String carnumber,
			String businesshoursStart, String businesshoursEnd, String areaid,
			String empiricalmode, String intro, String bossname,
			String bossmobile, String storemanagername,
			String storemanagermobile, String longitude, String latitude,
			String ownername, String bank, String cardnumber, String fileName1,
			String fileName2, String fileName3, String state, String allocation)
			throws BaseException, IOException {
		
		String storeId = storeid;
		String serverPath = request.getServletContext()
				.getRealPath("/userImg/");

		if (!"images/defaultAvatar.png".equals(fileName1)) {
			// 保存商户经营执照
			if (!storePhotoMappingService.addOrUpdImg(
					Integer.parseInt(storeId), 3, fileName1, serverPath)) {
				model.addAttribute("message", "商户经营执照,更新失败");
			}
		}

		if (!"images/defaultAvatar.png".equals(fileName2)) {
			// 保存商户正面照
			if (!storePhotoMappingService.addOrUpdImg(
					Integer.parseInt(storeId), 1, fileName2, serverPath)) {
				model.addAttribute("message", "商户正面照,更新失败");
			}
		}

		if (!"images/defaultAvatar.png".equals(fileName3)) {
			// 保存店内照
			if (!storePhotoMappingService.addOrUpdImg(
					Integer.parseInt(storeId), 2, fileName3, serverPath)) {
				model.addAttribute("message", "店内照,更新失败");
			}
		}

		// 构造店铺
		StoreinfoModel storeinfo = new StoreinfoModel();
		storeinfo.setStoreid(Integer.parseInt(storeId));
		storeinfo.setName(storeName);
		storeinfo.setProvinceid(Integer.parseInt(provinceid));
		storeinfo.setCityid(Integer.parseInt(cityid));
		storeinfo.setDistrictid(Integer.parseInt(districtid));
		storeinfo.setAddress(address);
		storeinfo.setTel(tel);
		if (carnumber.equals("")) {
			storeinfo.setCarnumber(0);
		} else {
			storeinfo.setCarnumber(Integer.parseInt(carnumber));
		}
		storeinfo.setBusinesshours(businesshoursStart + "-" + businesshoursEnd);
		storeinfo.setAreaid(Integer.parseInt(areaid));
		storeinfo.setEmpiricalmode(Integer.parseInt(empiricalmode));
		storeinfo.setIntro(intro);
		storeinfo.setBossname(bossname);
		storeinfo.setBossmobile(bossmobile);
		storeinfo.setStoremanagername(storemanagername);
		storeinfo.setStoremanagermobile(storemanagermobile);
		storeinfo.setLatitude(latitude);
		storeinfo.setLongitude(longitude);
		storeinfo.setOwnername(ownername);
		storeinfo.setBank(bank);
		storeinfo.setCardnumber(cardnumber);
		storeinfo.setState(Integer.parseInt(state));
		storeinfo.setAllocation(allocation);
		// 美发店信息更新
		if (storeinfoService.updateStore(storeinfo)) {

			// 绑定页面数据
			setPageData(storeinfo, model);

			model.addAttribute("storeinfo", storeinfo);
			model.addAttribute("message", "保存成功!");
		} else {
			model.addAttribute("message", "保存失败!");
			
		}
		return "storeinfo_validateinfo";
	}
	
}