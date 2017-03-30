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
import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.service.IBaseSuggestionService;
import com.wenyuankeji.spring.util.BaseException;

@Controller
public class BaseSuggestionController {

	
	@Autowired
	private IBaseSuggestionService baseSuggestionDaoService;

	

	/************ 管理端 ************/
	@RequestMapping("goBaseSuggestion")
	/**
	 * 跳转美发店登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goBaseSuggestion(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			return "baseSuggestion_manage";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("BaseSuggestionManage")
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
	public @ResponseBody Map<String, Object> BaseSuggestionManage(HttpServletRequest request, Model model,
			String startTime, String endTime, String page, String rows)
					throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);

		List<BaseSuggestionModel> baseSuggestionModels = baseSuggestionDaoService.searchBaseSuggestion(startTime, endTime, intPage, intRows);
		
		// 存放总记录数，必须的
		jsonMap.put("total", baseSuggestionDaoService.searchBaseSuggestionCount(startTime, endTime));
		// rows键 存放每页记录 list
	    jsonMap.put("rows", baseSuggestionModels);

		return jsonMap;
	}
}