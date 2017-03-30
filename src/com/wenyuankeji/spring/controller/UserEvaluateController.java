package com.wenyuankeji.spring.controller;

import java.util.ArrayList;
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
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.IUserEvaluatePhotoMappingService;
import com.wenyuankeji.spring.service.IUserEvaluateService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.util.BaseException;

@Controller
public class UserEvaluateController {

	@Autowired
	private IUserEvaluateService userEvaluateDaoService;

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IUserEvaluatePhotoMappingService userEvaluatePhotoMappingService;
	
	@Autowired
	private IStoreinfoService storeinfoService;
	
	@Value("#{configProperties['ipHost']}")
	private String ipHost;
	
	@Value("#{configProperties['imgIpHost']}")
	private String imgIpHost;

	/************ 管理端 ************/
	@RequestMapping("goUserEvaluateManage")
	/**
	 * 跳转评价管理
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUserEvaluateManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			return "userEvaluate_manage";
		}
		else{
			return "login";
		}
	}

	@RequestMapping("UserEvaluateManage")
	/**
	 * 获取评价列表
	 * @param request
	 * @param model
	 * @param userid
	 * @param bindphone
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public @ResponseBody Map<String, Object> UserEvaluateManage(
			HttpServletRequest request, Model model, String userid,
			String bindphone, String startTime, String endTime, String page,
			String rows) throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);

//		if (!"".equals(bindphone)) {
//			UserinfoModel userinfoModel = userinfoService.searchUserinfo(
//					bindphone, 1);
//			if (null != userinfoModel) {
//				userid = userinfoModel.getUserid() + "";
//			}
//		}

		List<UserEvaluateModel> userEvaluateModels = userEvaluateDaoService
				.searchUserEvaluate(userid,bindphone,startTime, endTime, intPage,
						intRows);

		if (null != userEvaluateModels && userEvaluateModels.size() > 0) {
			for (UserEvaluateModel o : userEvaluateModels) {
				
				o.setUserName(userinfoService.searchUserinfoById(o.getUserid()).getUsername());
				
				if (o.getType() == 1) {
					//美发师
					o.setItemName(userinfoService.searchUserinfoById(Integer.parseInt(o.getItem())).getUsername());
				}else {
					o.setItemName(storeinfoService.searchStoreinfoModel(Integer.parseInt(o.getItem())).getName());
				}
			}
		}
		
		// 存放总记录数，必须的
		jsonMap.put("total", userEvaluateDaoService.searchUserEvaluateCount(
				userid,bindphone,startTime, endTime));
		// rows键 存放每页记录 list
		jsonMap.put("rows", userEvaluateModels);

		return jsonMap;
	}

	@RequestMapping("goUserEvaluateDetail/{evaid}")
	/**
	 * 跳转评价详细
	 * @param request
	 * @param model
	 * @param storeid
	 * @param paystate
	 * @param createtime
	 * @return
	 */
	public String goUserEvaluateDetail(@PathVariable int evaid,
			HttpServletRequest request, Model model) throws BaseException {
		// 评价信息
		UserEvaluateModel userEvaluate = userEvaluateDaoService
				.searchUserEvaluate(evaid);

		List<String> photoList = new ArrayList<String>();
		List<UserEvaluatePhotoMappingModel> list = userEvaluatePhotoMappingService
				.searchUserEvaluatePhotoMappingg(evaid);
		for (int i = 0; i < list.size(); i++) {
			String imagePath = list.get(i).getBasePicture().getPicturepath();
			photoList.add(imgIpHost + imagePath);
		}

		model.addAttribute("score", userEvaluate.getScore());
		model.addAttribute("content", userEvaluate.getContent());
		model.addAttribute("photoList", photoList);

		return "user_evaluate_detail";
	}

	@RequestMapping("deleteUserEvaluate")
	/**
	 * 修改用户状态
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> deleteUserEvaluate(int evaid,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		if (userEvaluateDaoService.deleteUserEvaluate(evaid)) {
			editInfoMap.put("editInfo", true);
			editInfoMap.put("message", "删除成功！");
		} else {
			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "删除失败！");
		}
		return editInfoMap;
	}
}