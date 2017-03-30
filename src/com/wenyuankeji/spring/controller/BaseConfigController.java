package com.wenyuankeji.spring.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

@Controller
public class BaseConfigController {

	@Autowired
	private IBaseConfigService baseConfigService;
	
	@RequestMapping("goBaseConfig")
	/**
	 * 跳转系统配置页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goBaseConfig(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			try {
				//1：商圈管理
				
				//查询所有的城市列表
				List<BaseProvinceModel> baseProvinceList = new ArrayList<BaseProvinceModel>();
				baseProvinceList = baseConfigService.searchBaseProvince();
				BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
				baseProvinceModel.setProvinceid(0);
				baseProvinceModel.setProvincename("请选择");
				baseProvinceList.add(0, baseProvinceModel);
				model.addAttribute("baseProvinceList", baseProvinceList);
				//查询配置表中的商圈
	//			List<BaseBusinessareaModel> baseBusinessareaList = baseConfigService.searchBaseBusinessarea();
	//			model.addAttribute("baseBusinessareaList", baseBusinessareaList);
				
				//2：美发师爱好
				List<BaseConfigModel> hobbyList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_01);
				model.addAttribute("hobbyList", hobbyList);
				
				//3:美发师擅长
				List<BaseConfigModel> hairstyleList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_02);
				model.addAttribute("hairstyleList", hairstyleList);
				
				//4.查询分成比例
				List<BaseConfigModel> userAllocateList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_14);
				if (null != userAllocateList  && userAllocateList.size() > 0) {
					BaseConfigModel o = userAllocateList.get(0);
					model.addAttribute("UserAllocate", o.getConfigvalue());
				}else {
					model.addAttribute("UserAllocate", 0);
				}
				
				List<BaseConfigModel> storeAllocateList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_15);
				if (null != storeAllocateList  && storeAllocateList.size() > 0) {
					BaseConfigModel o = storeAllocateList.get(0);
					model.addAttribute("StoreAllocate", o.getConfigvalue());
				}else {
					model.addAttribute("StoreAllocate", 0);
				}
				
				//订单分成几天之后到账
				List<BaseConfigModel> accout_delay_dayList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_16);
				if (null != accout_delay_dayList  && accout_delay_dayList.size() > 0) {
					BaseConfigModel o = accout_delay_dayList.get(0);
					model.addAttribute("accout_delay_day", o.getConfigvalue());
				}else {
					model.addAttribute("accout_delay_day", 0);
				}
				
				
				//每月几号可以提现
				List<BaseConfigModel> accout_open_dayList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_17);
				if (null != accout_open_dayList  && accout_open_dayList.size() > 0) {
					BaseConfigModel o = accout_open_dayList.get(0);
					
					String configvalue = o.getConfigvalue();
					
					if (configvalue.indexOf(",") > 0) {
						String [] configvalues =   configvalue.split(",");
						model.addAttribute("accout_open_day1", configvalues[0]);
						model.addAttribute("accout_open_day2", configvalues[1]);
					}else {
						model.addAttribute("accout_open_day1",0);
						model.addAttribute("accout_open_day2",0);
					}
					
				}else {
					model.addAttribute("accout_open_day1",0);
					model.addAttribute("accout_open_day2",0);
				}
				
				
				//美发师和商户的预留押金
				List<BaseConfigModel> depositList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_18);
				if (null != depositList  && depositList.size() > 0) {
					BaseConfigModel o = depositList.get(0);
					model.addAttribute("deposit", o.getConfigvalue());
				}else {
					model.addAttribute("deposit", 0);
				}
				
				
				//客服电话
				List<BaseConfigModel> service_phoneList = baseConfigService.searchBaseConfigList(Constant.CONFIGCODE_19);
				if (null != service_phoneList  && service_phoneList.size() > 0) {
					BaseConfigModel o = service_phoneList.get(0);
					model.addAttribute("service_phone", o.getConfigvalue());
				}else {
					model.addAttribute("service_phone", 0);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "base_config";
		}else{
			return "login";
		}
	}
	

	@RequestMapping("/getCityByProvinceId/{province_id}")
	/**
	 * 根据省份ID查询城市
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getCityByProvinceId(
			@PathVariable int province_id, Model model, HttpSession session)
			throws BaseException {
		
		//根据所属省份的ID查询城市列表
		List<BaseCityModel> baseCityList = baseConfigService.searchBaseCityByProvinceId(province_id);
		Map<String, Object> baseCityMap = new HashMap<String, Object>();
		BaseCityModel baseCityModel = new BaseCityModel();
		baseCityModel.setCityid(0);
		baseCityModel.setCityname("请选择");
		baseCityList.add(0, baseCityModel);

		baseCityMap.put("baseCityList", baseCityList);
		return baseCityMap;
	}
	
	@RequestMapping("/addBaseBusinessarea")
	/**
	 * 添加商圈
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> addBaseBusinessarea(int cityid,String areaname)
			throws BaseException {
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		BaseBusinessareaModel o = new BaseBusinessareaModel();
		o.setDistrictid(0);
		o.setCityid(cityid);
		o.setAreaname(areaname);
		o.setCreatetime(new Date());
		
		
		if (baseConfigService.addBaseBusinessarea(o)) {
			outMap.put("info", true);
		}else {
			outMap.put("info", false);
			outMap.put("msg", "添加商圈失败！");
		}
		
		return outMap;
	}
	
	
	@RequestMapping("/addBaseConfig")
	/**
	 * 设置配置表
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> addBaseConfig(String configcode,String configvalue)
			throws BaseException {
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		BaseConfigModel o = new BaseConfigModel();
		if (Constant.CONFIGCODE_01.equals(configcode)) {
			o.setRemark("个人爱好");
		}else if (Constant.CONFIGCODE_02.equals(configcode)) {
			o.setRemark("擅长发型");
		}
		o.setConfigcode(configcode);
		o.setConfigvalue(configvalue);
		o.setValue1("");
		o.setValue2("");
		o.setSortnum(0);
		o.setCreatetime(new Date());
		
		if (baseConfigService.addBaseConfig(o)) {
			outMap.put("info", true);
		}else {
			outMap.put("info", false);
			outMap.put("msg","添加"+o.getRemark()+"失败！");
		}
		
		return outMap;
	}
	
	@RequestMapping("/updateBaseConfig")
	/**
	 * 设置配置表
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> updateBaseConfig(String configcode1,String configvalue1,String configcode2,String configvalue2)
			throws BaseException {
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		
		if (baseConfigService.updateBaseConfigByConfigCode(configcode1, configvalue1)
				&& baseConfigService.updateBaseConfigByConfigCode(configcode2, configvalue2)) {
			outMap.put("info", true);
		}else {
			outMap.put("info", false);
			outMap.put("msg","更新失败！");
		}
		
		return outMap;
	}
	
	@RequestMapping("/updateBaseConfigOne")
	/**
	 * 设置配置表
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> updateBaseConfig(String configcode,String configvalue)
			throws BaseException {
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		
		if (baseConfigService.updateBaseConfigByConfigCode(configcode, configvalue)) {
			outMap.put("info", true);
		}else {
			outMap.put("info", false);
			outMap.put("msg","更新失败！");
		}
		
		return outMap;
	}
	
}