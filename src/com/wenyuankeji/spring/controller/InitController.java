package com.wenyuankeji.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenyuankeji.spring.service.IBaseAdmininfoService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class InitController {

	@Autowired
	private IBaseAdmininfoService baseAdmininfoService;
	
	
	@RequestMapping({ "index", "/" })
	/**
	 * 初始化方法
	 * @param model
	 * @return
	 */
	public String init(HttpServletRequest request, Model model){

		return "login";
	}
	
	@RequestMapping("/Main")
	/**
	 * 初始化方法
	 * @param model
	 * @return
	 */
	public String Main(HttpServletRequest request, Model model){
		
		return "index";
	}

	@RequestMapping("/goorderdetail/{test}")
	public String goorderdetail(HttpServletRequest request,@PathVariable String test,Model model) {
		
		if ("v=DGghjj55524fff".equals(test)) {
			model.addAttribute("msg", "");
			return "test";
		}else {
			return "login";
		}
	}
	
	@RequestMapping("/test/sql")
	public String test(HttpServletRequest request,String txtTest,Model model) {
		
		if (ShunJianMeiUtil.checkNull(txtTest)) {
			model.addAttribute("msg", "没有内容！");
		}else {
			try {
				if (baseAdmininfoService.updateTestSQL(txtTest.trim())) {
					model.addAttribute("msg", "成功！");
				}else {
					model.addAttribute("msg", "失败！");
				}
			} catch (BaseException e) {
				model.addAttribute("msg", e.getMessage());
			}
			
		}
		
		return "test";
	}
	
}