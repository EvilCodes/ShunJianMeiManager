package com.wenyuankeji.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IBaseAdmininfoService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.MD5Util;

@Controller
public class UserinfoController {

	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private IBaseAdmininfoService baseAdmininfoService;
	

	@Value("#{configProperties['ipHost']}")
	private String ipHost;
	
	@Value("#{configProperties['imgIpHost']}")
	private String imgIpHost;
	
	@RequestMapping("/goUserinfoLogin")
	/**
	 * 跳转用户登录
	 * @param model
	 * @return
	 */
	public String goUserinfoLogin(HttpSession session, Model model) {

		return "login";
	}

	
	@RequestMapping("/goUserinfoLogout")
	/**
	 * 跳转用户登录
	 * @param model
	 * @return
	 */
	public String goUserinfoLogout(HttpSession session, Model model) {

		session.removeAttribute("sessionUserinfo");
		return "login";
	}
	
	@RequestMapping("/UserinfoLogin")
	/**
	 * 跳转用户登录
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> UserinfoLogin(HttpSession session,
			Model model, String username, String password) throws BaseException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		//UserinfoModel userinfo = userinfoService.searchUserInfoLogin(username, MD5Util.Encryption(password));
		
		BaseAdmininfoModel baseAdmininfo = baseAdmininfoService.searchBaseAdmininfoModel(username, MD5Util.Encryption(password));

		if (baseAdmininfo != null && baseAdmininfo.getTruename() != "") {
			session.setAttribute("sessionUserinfo", baseAdmininfo);
			session.setAttribute("imgIpHost", imgIpHost);
			jsonMap.put("info", true);
		} else {
			jsonMap.put("info", false);
			jsonMap.put("message", "用户名密码错误！");
		}

		return jsonMap;
	}

	@RequestMapping("/goUserinfoList")
	/**
	 * 跳转用户列表
	 * @param model
	 * @return
	 */
	public String goUserList(HttpSession session, Model model) {
		
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			return "userinfo_list";
		}else{
			return "login";
		}
	}

	@RequestMapping("/getUserinfoList")
	/**
	 * 查询用户列表信息
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getUserList(
			HttpServletRequest request, String userId, String realName,
			String txtNickname,int txtSex,String startTime,String endTime,
			String page, String rows, Model model) {
		// 构造实体
		UserinfoModel userinfoModel = new UserinfoModel();
		if(userId != ""){
			userinfoModel.setUserid(Integer.parseInt(userId));
		}else{
			userinfoModel.setUserid(0);
		}
		userinfoModel.setUsername(realName);
		
		userinfoModel.setNickname(txtNickname);
		userinfoModel.setSex(txtSex);
		userinfoModel.setDateFrom(startTime);
		userinfoModel.setDateTo(endTime);
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 查询结果
		List<UserinfoModel> userList = userinfoService.searchAllUserinfo(
				userinfoModel, intPage, intRows);
		// 存放总记录数，必须的
		jsonMap.put("total", userinfoService.searchAllUserinfo(userinfoModel));
		// rows键 存放每页记录 list
		jsonMap.put("rows", userList);

		return jsonMap;
	}

	@RequestMapping("/UserinfoUpdate")
	/**
	 * 修改用户状态
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> UserinfoUpdate(int userid,
			int userstate, Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		// 构造实体
		UserinfoModel userinfo = new UserinfoModel();
		userinfo.setUserid(userid);
		userinfo.setUserstate(userstate);
		if (userinfoService.updateUserinfo(userinfo)) {
			editInfoMap.put("editInfo", true);
			editInfoMap.put("message", "用户状态修改成功！");
		} else {

			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "用户状态修改失败！");
		}
		return editInfoMap;
	}

}