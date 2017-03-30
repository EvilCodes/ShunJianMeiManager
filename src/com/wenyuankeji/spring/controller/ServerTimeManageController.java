package com.wenyuankeji.spring.controller;

import java.util.HashMap;
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
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.Constant;

@Controller
public class ServerTimeManageController {

	@Autowired
	private IBaseConfigService baseConfigService;
	
	@Autowired
	private IUtilService utilService;
	
	@RequestMapping("goServerTimeManage")
	/**
	 * 跳转服务时间配置页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goServerTimeManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			try {
				//查询洗剪吹时间
				BaseConfigModel xjcBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_09);
				model.addAttribute("xjc", xjcBaseConfigModel.getConfigvalue());
				
				//查询洗吹时间
				BaseConfigModel xcBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_10);
				model.addAttribute("xc", xcBaseConfigModel.getConfigvalue());
				
				//查询烫发时间
				BaseConfigModel tfBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_11);
				model.addAttribute("tf", tfBaseConfigModel.getConfigvalue());
				
				//查询染发时间
				BaseConfigModel rfBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_12);
				model.addAttribute("rf", rfBaseConfigModel.getConfigvalue());
				
				//查询护理时间
				BaseConfigModel hlBaseConfigModel = baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_13);
				model.addAttribute("hl", hlBaseConfigModel.getConfigvalue());
				
				
			} catch (Exception e) {
				// 保存异常信息
				utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
			}
			return "serverTime_manage";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("doSaveServerTime")
	/**
	 * 保存服务时间配置信息
	 * @param request
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> doSaveServerTime(String xjc,String xc,String tf,String rf,String hl,HttpServletRequest request, Model model) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			//保存洗剪吹时间
			boolean xjcResult = baseConfigService.updateBaseConfigByConfigCode(Constant.CONFIGCODE_09,xjc);
			model.addAttribute("xjc", xjc);
			
			//保存洗吹时间
			boolean xcResult = baseConfigService.updateBaseConfigByConfigCode(Constant.CONFIGCODE_10,xc);
			model.addAttribute("xc", xc);
			
			//查询烫发时间
			boolean tfResult = baseConfigService.updateBaseConfigByConfigCode(Constant.CONFIGCODE_11,tf);
			model.addAttribute("tf", tf);
			
			//查询染发时间
			boolean rfResult = baseConfigService.updateBaseConfigByConfigCode(Constant.CONFIGCODE_12,rf);
			model.addAttribute("rf", rf);
			
			//查询护理时间
			boolean hlResult = baseConfigService.updateBaseConfigByConfigCode(Constant.CONFIGCODE_13,hl);
			model.addAttribute("hl", hl);
			
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
}