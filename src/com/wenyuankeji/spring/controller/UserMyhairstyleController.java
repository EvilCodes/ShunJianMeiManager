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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.model.ServiceattributeModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.model.UserMyhairstyleSearchModel;
import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IServiceattributeService;
import com.wenyuankeji.spring.service.IUserMyhairstylePhotoMappingService;
import com.wenyuankeji.spring.service.IUserMyhairstyleService;
import com.wenyuankeji.spring.service.IUserMyhairstyleTimesService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.util.BaseException;

@Controller
public class UserMyhairstyleController {

	@Autowired
	private IUserMyhairstyleService userMyhairstyleService;

	@Autowired
	private IUserMyhairstylePhotoMappingService userMyhairstylePhotoMappingService;

	@Autowired
	private IUserMyhairstyleTimesService userMyhairstyleTimesService;

	@Autowired
	private IServiceattributeService serviceattributeService;

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IUsersubinfoService usersubinfoService;
	
	@Value("#{configProperties['ipHost']}")
	private String ipHost;
	
	@Value("#{configProperties['imgIpHost']}")
	private String imgIpHost;

	@RequestMapping("/goUserMyhairstyleList")
	/**
	 * 跳转发型列表
	 * @param model
	 * @return
	 */
	public String goUserMyhairstyleList(HttpSession session, Model model) {

		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			return "user_myhairstyle_list";
		}else{
			return "login";
		}
	}

	@RequestMapping("/getUserMyhairstyleList")
	/**
	 * 发型列表
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getUserMyhairstyleList(
			HttpServletRequest request, String userid, String tel, 
			String startTime, String endTime,String checkstate,String txtNickname,
			String page,String rows, Model model) {
		// 构造实体
//		UserMyhairstyleModel userMyhairstyle = new UserMyhairstyleModel();
//		userMyhairstyle.setMystyleid(mystyleid);
//		userMyhairstyle.setUserid(userid);
//		userMyhairstyle.setState(state);
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 查询结果
		List<UserMyhairstyleSearchModel> userMyhairstyleList = userMyhairstyleService
				.searchUserMyhairstyle(userid, tel, 
					 startTime, endTime, checkstate,txtNickname ,intPage, intRows);
		// 存放总记录数，必须的
		jsonMap.put("total",
				userMyhairstyleService.searchUserMyhairstyle( userid,  tel, 
						 startTime,  endTime, checkstate,txtNickname));
		// rows键 存放每页记录 list
		jsonMap.put("rows", userMyhairstyleList);

		return jsonMap;
	}

	@RequestMapping("goUserMyhairstyleDetail/{mystyleid}")
	/**
	 * 跳转发型详细
	 * @param request
	 * @param model
	 * @param storeid
	 * @param paystate
	 * @param createtime
	 * @return
	 */
	public String goUserMyhairstyleDetail(@PathVariable int mystyleid,
			HttpServletRequest request, Model model) throws BaseException {
		//我的发型
		UserMyhairstyleModel userMyhairstyle = userMyhairstyleService
				.searchUserMyhairstyle(mystyleid);
		
		//所属美发师
		UserinfoModel userinfo = userinfoService.searchUserinfoById(userMyhairstyle.getUserid());
		UsersubinfoModel usersubinfo = usersubinfoService.searchUsersubinfo(userMyhairstyle.getUserid());

		//列表图
		UserMyhairstylePhotoMappingModel photoMapping1 = userMyhairstylePhotoMappingService
				.searchUserMyhairstylePhotoMapping(mystyleid, 1);
		//相册
		UserMyhairstylePhotoMappingModel photoMapping2 = userMyhairstylePhotoMappingService
				.searchUserMyhairstylePhotoMapping(mystyleid, 2);
		String pohot1 = imgIpHost + photoMapping1.getBasePicture().getPicturepath();
		String pohot2 = imgIpHost + photoMapping2.getBasePicture().getPicturepath();

		//服务编码集合
		List<ServiceattributeModel> serviceattributeList = serviceattributeService
				.searchServiceattribute();

		for (ServiceattributeModel object : serviceattributeList) {
			//服务时间对象
			UserMyhairstyleTimesModel userMyhairstyleTimes = userMyhairstyleTimesService
					.searchUserMyhairstyleTimes(mystyleid,
							object.getServicecode());
			//服务时间
			System.out.println("userMyhairstyleTimes:"+userMyhairstyleTimes);
			model.addAttribute(userMyhairstyleTimes.getServicecode()+"_time", userMyhairstyleTimes.getTimes());
			//服务名称
			model.addAttribute(object.getServicecode() + "_name", object.getServicename());
		}

		model.addAttribute("pohot1", pohot1);
		model.addAttribute("pohot2", pohot2);
		model.addAttribute("usersubinfo", usersubinfo);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("userMyhairstyle", userMyhairstyle);

		return "user_myhairstyle_detail";
	}

	@RequestMapping("/UserMyhairstyleUpdate")
	/**
	 * 修改状态
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> UserMyhairstyleUpdate(
			int mystyleid, int state, Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		// 构造实体
		UserMyhairstyleModel userMyhairstyle = new UserMyhairstyleModel();
		userMyhairstyle.setMystyleid(mystyleid);
		userMyhairstyle.setState(state);
		if (userMyhairstyleService.updateUserMyhairstyle(userMyhairstyle)) {
			editInfoMap.put("editInfo", true);
			editInfoMap.put("message", "发型状态修改成功！");
		} else {

			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "发型状态修改失败！");
		}
		return editInfoMap;
	}

}