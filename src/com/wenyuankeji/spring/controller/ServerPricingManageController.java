package com.wenyuankeji.spring.controller;

import java.util.ArrayList;
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
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IUserProfessionLevelService;
import com.wenyuankeji.spring.service.IUserProfessionLevelServiceService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.Constant;

@Controller
public class ServerPricingManageController {

	@Autowired
	private IUserProfessionLevelService userProfessionLevelService;
	
	@Autowired
	private IUserProfessionLevelServiceService userProfessionLevelServiceService;
	
	@Autowired
	private IBaseConfigService baseConfigService;
	
	@Autowired
	private IUtilService utilService;
	
	@RequestMapping("goServerPricingManage")
	/**
	 * 跳转服务价格配置页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goServerPricingManage(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			try {			
				List<UserProfessionLevelModel> userProfessionLevelList = new ArrayList<UserProfessionLevelModel>();
	
				userProfessionLevelList = userProfessionLevelService.searchUserProfessionLevel();
				model.addAttribute("userProfessionLevelList", userProfessionLevelList);
				
				if(userProfessionLevelList.size() > 0){
					UserProfessionLevelModel userProfessionLevelModel = userProfessionLevelList.get(0);
					int levelid = userProfessionLevelModel.getLevelid();
					model.addAttribute("levelid", levelid);
					List<UserProfessionLevelServiceModel> userProfessionLevelServiceModelList = userProfessionLevelServiceService.searchUserProfessionLevelService(levelid); 
					for (int i = 0; i < userProfessionLevelServiceModelList.size(); i++) {
						UserProfessionLevelServiceModel userProfessionLevelServiceModel = userProfessionLevelServiceModelList.get(i);
						if(null != userProfessionLevelServiceModel.getServicecode() && !"".equals(userProfessionLevelServiceModel.getServicecode())){
							String serviceCode = userProfessionLevelServiceModel.getServicecode();
							if(Constant.CONFIGCODE_09.equals(serviceCode)){
								model.addAttribute("xjc", userProfessionLevelServiceModel.getPrice());
							}else if(Constant.CONFIGCODE_10.equals(serviceCode)){
								model.addAttribute("xc", userProfessionLevelServiceModel.getPrice());
							}else if(Constant.CONFIGCODE_11.equals(serviceCode)){
								model.addAttribute("tf", userProfessionLevelServiceModel.getPrice());
							}else if(Constant.CONFIGCODE_12.equals(serviceCode)){
								model.addAttribute("rf", userProfessionLevelServiceModel.getPrice());
							}else if(Constant.CONFIGCODE_13.equals(serviceCode)){
								model.addAttribute("hl", userProfessionLevelServiceModel.getPrice());
							}
						}
					}
				}
			} catch (Exception e) {
				// 保存异常信息
				utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
			}
			return "serverPricing_manage";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("doSaveServerPricing")
	/**
	 * 保存服务价格配置信息
	 * @param request
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> doSaveServerPricing(int levelid,int xjc,int xc,int tf,int rf,int hl,HttpServletRequest request, Model model) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			UserProfessionLevelServiceModel xjcModel = new UserProfessionLevelServiceModel();
			xjcModel.setLevelid(levelid);
			xjcModel.setServicecode(Constant.CONFIGCODE_09);
			BaseConfigModel xjcBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_09);
			if(null != xjcBaseConfigModel && null != xjcBaseConfigModel.getConfigvalue()){
				xjcModel.setTimes(Integer.parseInt(xjcBaseConfigModel.getConfigvalue()));
			}
			xjcModel.setPrice(xjc);
			
			
			UserProfessionLevelServiceModel xcModel = new UserProfessionLevelServiceModel();
			xcModel.setLevelid(levelid);
			xcModel.setServicecode(Constant.CONFIGCODE_10);
			BaseConfigModel xcBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_10);
			if(null != xcBaseConfigModel && null != xcBaseConfigModel.getConfigvalue()){
				xcModel.setTimes(Integer.parseInt(xcBaseConfigModel.getConfigvalue()));
			}
			xcModel.setPrice(xc);
			
			UserProfessionLevelServiceModel tfModel = new UserProfessionLevelServiceModel();
			tfModel.setLevelid(levelid);
			tfModel.setServicecode(Constant.CONFIGCODE_11);
			BaseConfigModel tfBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_11);
			if(null != tfBaseConfigModel && null != tfBaseConfigModel.getConfigvalue()){
				tfModel.setTimes(Integer.parseInt(tfBaseConfigModel.getConfigvalue()));
			}
			tfModel.setPrice(tf);
			
			UserProfessionLevelServiceModel rfModel = new UserProfessionLevelServiceModel();
			rfModel.setLevelid(levelid);
			rfModel.setServicecode(Constant.CONFIGCODE_12);
			BaseConfigModel rfBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_12);
			if(null != rfBaseConfigModel && null != rfBaseConfigModel.getConfigvalue()){
				rfModel.setTimes(Integer.parseInt(rfBaseConfigModel.getConfigvalue()));
			}
			rfModel.setPrice(rf);
			
			UserProfessionLevelServiceModel hlModel = new UserProfessionLevelServiceModel();
			hlModel.setLevelid(levelid);
			hlModel.setServicecode(Constant.CONFIGCODE_12);
			BaseConfigModel hlBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_13);
			if(null != hlBaseConfigModel && null != hlBaseConfigModel.getConfigvalue()){
				hlModel.setTimes(Integer.parseInt(hlBaseConfigModel.getConfigvalue()));
			}
			hlModel.setPrice(hl);
			
			boolean xjcResult = userProfessionLevelServiceService.addOrUpdateUserProfessionLevelService(xjcModel);
			boolean xcResult = userProfessionLevelServiceService.addOrUpdateUserProfessionLevelService(xcModel);
			boolean tfResult = userProfessionLevelServiceService.addOrUpdateUserProfessionLevelService(tfModel);
			boolean rfResult = userProfessionLevelServiceService.addOrUpdateUserProfessionLevelService(rfModel);
			boolean hlResult = userProfessionLevelServiceService.addOrUpdateUserProfessionLevelService(hlModel);
			
			
			if(xjcResult && xcResult && tfResult && rfResult && hlResult){
				editInfoMap.put("msg", "保存成功！");
			}else{
				editInfoMap.put("msg", "保存失败！");
			}
			return editInfoMap;
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return editInfoMap;
	}
	
	
	@RequestMapping("getServerPricingInfo")
	/**
	 * 获取服务价格配置信息
	 * @param request
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getServerPricingInfo(int levelid,HttpServletRequest request, Model model) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			model.addAttribute("levelid", levelid);
			List<UserProfessionLevelServiceModel> userProfessionLevelServiceModelList = userProfessionLevelServiceService.searchUserProfessionLevelService(levelid); 
			for (int i = 0; i < userProfessionLevelServiceModelList.size(); i++) {
				UserProfessionLevelServiceModel userProfessionLevelServiceModel = userProfessionLevelServiceModelList.get(i);
				if(null != userProfessionLevelServiceModel.getServicecode() && !"".equals(userProfessionLevelServiceModel.getServicecode())){
					String serviceCode = userProfessionLevelServiceModel.getServicecode();
					if(Constant.CONFIGCODE_09.equals(serviceCode)){
						model.addAttribute("xjc", userProfessionLevelServiceModel.getPrice());
						editInfoMap.put("xjc", userProfessionLevelServiceModel.getPrice());
					}else if(Constant.CONFIGCODE_10.equals(serviceCode)){
						model.addAttribute("xc", userProfessionLevelServiceModel.getPrice());
						editInfoMap.put("xc", userProfessionLevelServiceModel.getPrice());
					}else if(Constant.CONFIGCODE_11.equals(serviceCode)){
						model.addAttribute("tf", userProfessionLevelServiceModel.getPrice());
						editInfoMap.put("tf", userProfessionLevelServiceModel.getPrice());
					}else if(Constant.CONFIGCODE_12.equals(serviceCode)){
						model.addAttribute("rf", userProfessionLevelServiceModel.getPrice());
						editInfoMap.put("rf", userProfessionLevelServiceModel.getPrice());
					}else if(Constant.CONFIGCODE_13.equals(serviceCode)){
						model.addAttribute("hl", userProfessionLevelServiceModel.getPrice());
						editInfoMap.put("hl", userProfessionLevelServiceModel.getPrice());
					}
				}
			}
			return editInfoMap;
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return editInfoMap;
	}
}