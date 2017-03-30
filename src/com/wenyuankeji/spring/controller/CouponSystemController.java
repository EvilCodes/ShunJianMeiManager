package com.wenyuankeji.spring.controller;

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
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.ICouponSystemService;
import com.wenyuankeji.spring.util.Constant;

@Controller
public class CouponSystemController {

	
	@Autowired 
	private ICouponSystemService couponSystemService;
	
	@RequestMapping("goCouponSystem")
	/**
	 * 跳转优惠券系统
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goCouponSystem(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			try {
				//查询分享优惠券价格-洗剪吹 最小值
				BaseConfigModel ShareCouponMinModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_07+Constant.CONFIGCODE_XJC);
				if (ShareCouponMinModel != null ) {
					model.addAttribute("ShareCouponMin", ShareCouponMinModel.getConfigvalue());
				}
				//分享优惠券金额-洗剪吹 最大值
				BaseConfigModel ShareCouponMaxModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_08+Constant.CONFIGCODE_XJC);
				if (ShareCouponMaxModel != null ) {
					model.addAttribute("ShareCouponMax", ShareCouponMaxModel.getConfigvalue());
				}
				//查询分享优惠券的有效天数
				BaseConfigModel ShareCouponDaysModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_20);
				if (ShareCouponDaysModel != null ) {
					model.addAttribute("ShareCouponDays", ShareCouponDaysModel.getConfigvalue());
				}
				
				//注册优惠券金额 -烫发  最小值
				BaseConfigModel RegCouponMinModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_05+Constant.CONFIGCODE_TF);
				if (RegCouponMinModel != null ) {
					model.addAttribute("RegCouponMin", RegCouponMinModel.getConfigvalue());
				}
				//注册优惠券金额 -烫发 最大值
				BaseConfigModel RegCouponMaxModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_06+Constant.CONFIGCODE_TF);
				if (RegCouponMaxModel != null ) {
					model.addAttribute("RegCouponMax", RegCouponMaxModel.getConfigvalue());
				}
				//查询分享优惠券的有效天数
				BaseConfigModel RegCouponDaysModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_21);
				if (RegCouponDaysModel != null ) {
					model.addAttribute("RegCouponDays", RegCouponDaysModel.getConfigvalue());
				}
				
				//注册后赠送的优惠卷数
				List<BaseConfigModel> regCouponList = couponSystemService.searchBaseConfigList(Constant.CONFIGCODE_22);
				if (regCouponList == null || regCouponList.size() < 0 ) {
					model.addAttribute("regCouponHl","1");
					model.addAttribute("regCouponRf","1");
					model.addAttribute("regCouponTf","1");
					model.addAttribute("regCouponXc","1");
					model.addAttribute("regCouponXjc","1");
					model.addAttribute("regCouponTc","1");
				}else {
					for (BaseConfigModel o : regCouponList) {
						if ("hl".equals(o.getConfigvalue())) {
							model.addAttribute("regCouponHl",o.getValue1());
						}else if ("rf".equals(o.getConfigvalue())) {
							model.addAttribute("regCouponRf",o.getValue1());
						}else if ("tf".equals(o.getConfigvalue())){
							model.addAttribute("regCouponTf",o.getValue1());
						}else if ("xc".equals(o.getConfigvalue())) {
							model.addAttribute("regCouponXc",o.getValue1());
						}else if ("xjc".equals(o.getConfigvalue())) {
							model.addAttribute("regCouponXjc",o.getValue1());
						}else if ("tc".equals(o.getConfigvalue())) {
							model.addAttribute("regCouponTc",o.getValue1());
						}
					}
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "couponSystem";
		}else{
			return "login";
		}
	}
	
	
	
	@RequestMapping("/updateRegCouponCont")
	public @ResponseBody Map<String, Object> updateRegCouponCont(int regCouponHl,int regCouponRf,int regCouponTf,int regCouponXc,int regCouponXjc,int regCouponTc,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			
			
			if (couponSystemService.updateRegCoupon(regCouponHl,regCouponRf,regCouponTf,regCouponXc,regCouponXjc,regCouponTc)) {
				editInfoMap.put("editInfo", true);
				editInfoMap.put("message", "修改成功！");
			} else {
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "修改失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editInfoMap;
	}
	
	@RequestMapping("/getShareCouponValue")
	/**
	 * 根据分享分类获取最大最小值
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getShareCouponValue(String txtShareCoupon,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			BaseConfigModel ShareCouponMinModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_07+txtShareCoupon);
			if (ShareCouponMinModel != null ) {
				editInfoMap.put("ShareCouponMin", ShareCouponMinModel.getConfigvalue());
			}
			BaseConfigModel ShareCouponMaxModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_08+txtShareCoupon);
			if (ShareCouponMaxModel != null ) {
				editInfoMap.put("ShareCouponMax", ShareCouponMaxModel.getConfigvalue());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return editInfoMap;
	}


	@RequestMapping("/updateShareCoupon")
	/**
	 * 修改分享优惠券的值
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> updateShareCoupon(String txtShareCoupon, String ShareCouponMin, String ShareCouponMax,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			if (couponSystemService.updateBaseConfig(txtShareCoupon, ShareCouponMin, ShareCouponMax, 2)) {
				editInfoMap.put("editInfo", true);
				editInfoMap.put("message", "优惠券价格修改成功！");
			} else {
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "优惠券价格修改失败！");
			}
		} catch (Exception e) {
			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "优惠券价格修改失败！");
		}
		return editInfoMap;
	}

	@RequestMapping("/addUserCoupon")
	/**
	 * 平台赠送用户优惠券
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> addUserCoupon(String txtUserCoupon, String mobile, String amount, 
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		
		try {
			
			//查询赠送的用户是否存在
			UserinfoModel userinfoModel = couponSystemService.searchUserinfo(mobile, 1);
			if (userinfoModel != null) {
				
				if (couponSystemService.addUserCoupon(txtUserCoupon, mobile, amount,userinfoModel.getUserid())) {
					editInfoMap.put("editInfo", true);
					editInfoMap.put("message", "赠送用户优惠券成功！");
					
				} else {
		
					editInfoMap.put("editInfo", false);
					editInfoMap.put("message", "赠送用户优惠券失败！");
				}
			}else{
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "手机号不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editInfoMap;
	}
	
	
	@RequestMapping("/getRegCouponValue")
	/**
	 * 根据分享分类获取最大最小值
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getRegCouponValue(String txtRegCoupon,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			BaseConfigModel ShareCouponMinModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_05+txtRegCoupon);
			if (ShareCouponMinModel != null ) {
				editInfoMap.put("RegCouponMin", ShareCouponMinModel.getConfigvalue());
			}
			BaseConfigModel ShareCouponMaxModel = couponSystemService.searchBaseConfigByCode(Constant.CONFIGCODE_06+txtRegCoupon);
			if (ShareCouponMaxModel != null ) {
				editInfoMap.put("RegCouponMax", ShareCouponMaxModel.getConfigvalue());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return editInfoMap;
	}

	
	@RequestMapping("/updateRegCoupon")
	/**
	 * 修改注册优惠券的值
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> updateRegCoupon(String txtRegCoupon, String RegCouponMin, String RegCouponMax,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			if (couponSystemService.updateBaseConfig(txtRegCoupon, RegCouponMin, RegCouponMax, 1)) {
				editInfoMap.put("editInfo", true);
				editInfoMap.put("message", "优惠券价格修改成功！");
			} else {
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "优惠券价格修改失败！");
			}
		} catch (Exception e) {
			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "优惠券价格修改失败！");
		}
		return editInfoMap;
	}
	
	
	
	@RequestMapping("/updateShareCouponDays")
	/**
	 * 修改分享优惠券有效天数
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> updateShareCouponDays(String ShareCouponDays,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			if (couponSystemService.updateBaseConfig(Constant.CONFIGCODE_20, ShareCouponDays)) {
				editInfoMap.put("editInfo", true);
				editInfoMap.put("message", "分享优惠券有效天数修改成功！");
			} else {
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "分享优惠券有效天数修改失败！");
			}
		} catch (Exception e) {
			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "分享优惠券有效天数修改失败！");
		}
		return editInfoMap;
	}
	
	@RequestMapping("/updateRegCouponDays")
	/**
	 * 修改注册优惠券有效天数
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> updateRegCouponDays(String RegCouponDays,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			if (couponSystemService.updateBaseConfig(Constant.CONFIGCODE_21, RegCouponDays)) {
				editInfoMap.put("editInfo", true);
				editInfoMap.put("message", "注册优惠券有效天数修改成功！");
			} else {
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "注册优惠券有效天数修改失败！");
			}
		} catch (Exception e) {
			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "注册优惠券有效天数修改失败！");
		}
		return editInfoMap;
	}
	
}