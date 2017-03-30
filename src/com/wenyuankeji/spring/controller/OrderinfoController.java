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
import com.wenyuankeji.spring.model.OrderCountModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IStorePhotoMappingService;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.IUserHairstyleService;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

@Controller
public class OrderinfoController {

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IOrderinfoService orderinfoService;

	@Autowired
	private IUsersubinfoService usersubinfoService;

	@Autowired
	private IUserPhotoMappingService userPhotoMappingService;

	@Autowired
	private IStoreinfoService storeinfoService;

	@Autowired
	private IStorePhotoMappingService storePhotoMappingService;

	@Autowired
	private IUserHairstyleService userHairstyleService;

	@Value("#{configProperties['imgIpHost']}")
	private String imgIpHost;

	@RequestMapping("/goOrderinfoList")
	/**
	 * 跳转订单列表
	 * @param model
	 * @return
	 */
	public String goOrderinfoList(HttpSession session, Model model) {
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session
				.getAttribute("sessionUserinfo");
		if (userinfo != null) {
			return "orderinfo_list";
		} else {
			return "login";
		}
	}

	@RequestMapping("/getOrderinfoList")
	/**
	 * 订单列表
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getOrderinfoList(
			HttpServletRequest request, int customerid, int userid,
			int storeid, String startTime, String endTime, int paystate,
			int orderid, String customerTelephone, String userTelephone,
			String page, String rows, Model model) {
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 查询结果
		List<OrderinfoModel> orderList = orderinfoService.searchOrderinfo(
				customerid, userid, storeid, startTime, endTime, paystate,
				orderid, customerTelephone, userTelephone, intPage, intRows);
		
		if (null != orderList && orderList.size()>0) {
			for (OrderinfoModel o : orderList) {
				o.setAdditionalcode(o.getUserinfo().getNickname());
				o.setAdditionalcontent(o.getUserinfo().getUsername());
			}
		}
		
		// 存放总记录数，必须的
		jsonMap.put("total", orderinfoService.searchOrderinfo(customerid,
				userid, storeid, startTime, endTime, paystate, orderid,
				customerTelephone, userTelephone));
		// rows键 存放每页记录 list
		jsonMap.put("rows", orderList);

		return jsonMap;
	}

	@RequestMapping("goOrderinfoDetail/{orderid}")
	/**
	 * 跳转美发店订单详细
	 * @param request
	 * @param model
	 * @param storeid
	 * @param paystate
	 * @param createtime
	 * @return
	 */
	public String goOrderinfoDetail(@PathVariable int orderid,
			HttpServletRequest request, Model model) throws BaseException {
		// 订单信息
		OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(orderid);
		// 用户
		UserinfoModel userinfo = userinfoService.searchUserinfoById(orderinfo
				.getCustomerid());
		UserPhotoMappingModel photoMapping = userPhotoMappingService
				.searchUserPhoto(userinfo.getUserid(), 1);
		
		if (null != photoMapping
				&& null != photoMapping.getBasePicture()) {
			model.addAttribute("photo", photoMapping.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("photo", "");
		}
		model.addAttribute("userinfo", userinfo);
		// 美发师
		UsersubinfoModel usersubinfo = usersubinfoService
				.searchUsersubinfo(orderinfo.getUserid());
		UserPhotoMappingModel userPhotoMapping = userPhotoMappingService
				.searchUserPhotoMapping(usersubinfo.getUserid(), 1);
		// 店铺信息
		StoreinfoModel storeinfo = storeinfoService
				.searchStoreinfoModel(orderinfo.getStoreid());
		StorePhotoMappingModel storePhotoMapping = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 1);
		// 发型信息
		UserHairstyleModel userHairstyle = userHairstyleService
				.searchUserHairstyleById(orderinfo.getStyleid());
		// 下一步状态
		String nowStatus = Constant.PAYSTATE_MAP.get(orderinfo.getPaystate());
		// 下一步状态
		String nextStatus = Constant.NEXT_STEP_MAP.get(orderinfo.getPaystate());

		model.addAttribute("orderinfo", orderinfo);

		model.addAttribute("usersubinfo", usersubinfo);

		if (null != userPhotoMapping
				&& null != userPhotoMapping.getBasePicture()) {
			model.addAttribute("userPhoto", userPhotoMapping.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("userPhoto", "");
		}

		if (null != usersubinfo.getUserProfessionLevel()) {
			model.addAttribute("userLevel", usersubinfo
					.getUserProfessionLevel().getLevelname());
		} else {
			model.addAttribute("userLevel", "");
		}

		model.addAttribute("storeinfo", storeinfo);
		if (null != storePhotoMapping
				&& null != storePhotoMapping.getBasePicture()) {
			model.addAttribute("storePhoto", storePhotoMapping.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("storePhoto", "");
		}
		
		String servicename = orderinfoService.searchOrderServicenameList(orderid);
		
		model.addAttribute("servicename", servicename);
		model.addAttribute("userHairstyle", userHairstyle.getName());
		model.addAttribute("nowStatus", nowStatus);
		model.addAttribute("nextStatus", nextStatus);

		return "orderinfo_detail";
	}

	@RequestMapping("/OrderinfoUpdate")
	/**
	 * 修改订单状态
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> OrderinfoUpdate(int orderid,
			int paystate, Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		// 构造实体
		OrderinfoModel orderinfo = new OrderinfoModel();
		orderinfo.setOrderid(orderid);
		orderinfo.setPaystate(paystate);
		if (orderinfoService.updateOrderinfo(orderinfo)) {
			editInfoMap.put("editInfo", true);
			editInfoMap.put("message", "用户状态修改成功！");
		} else {

			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "用户状态修改失败！");
		}
		return editInfoMap;
	}

	@RequestMapping("/goOrderExceptionInfoManage")
	/**
	 * 跳转订单异常列表
	 * @param model
	 * @return
	 */
	public String goOrderExceptionInfoManage(HttpSession session, Model model) {

		return "orderExceptionInfo_manage";
	}

	@RequestMapping("/orderExceptionInfoManage")
	/**
	 * 异常订单列表
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> orderExceptionInfoManage(
			HttpServletRequest request, int paystate, String page, String rows,
			Model model) {
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 查询结果
		List<OrderinfoModel> orderList = orderinfoService
				.searchOrderExceptionInfo(paystate, intPage, intRows);
		// 存放总记录数，必须的
		jsonMap.put("total",
				orderinfoService.searchOrderExceptionInfoCount(paystate));
		// rows键 存放每页记录 list
		jsonMap.put("rows", orderList);

		return jsonMap;
	}

	@RequestMapping("/goOrderCount")
	/**
	 * 跳转订单统计
	 * @param model
	 * @return
	 */
	public String goOrderCount(HttpSession session, Model model) {

		return "orderCount_manage";
	}

	@RequestMapping("/orderCountManage")
	/**
	 * 订单统计信息
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> orderCountManage(
			HttpServletRequest request, String startTime, String endTime,
			String page, String rows, Model model) {
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		OrderCountModel orderCountModel = new OrderCountModel();
		orderCountModel.setOrderNumber(orderinfoService
				.searchOrderCountInfoCount("", startTime, endTime));// 预约订单
		orderCountModel.setPrepaidNumer(orderinfoService
				.searchOrderCountInfoCount("3,4,5,6,7,8", startTime,
						endTime));// 完成支付
		/*
		 * orderCountModel.setOrderCompletedNumber(orderinfoService
		 * .searchOrderCountInfoCount("3,4,5", startTime, endTime));
		 */// 完成预约
		orderCountModel.setServiceCompletedNumer(orderinfoService
				.searchOrderCountInfoCount("6,7,8", startTime, endTime));// 完成服务

		int exceptionNum = orderinfoService.searchOrderCountInfoCount("10",
				startTime, endTime);// 异常订单数
		int total = orderinfoService.searchOrderCountInfoCount("", startTime,
				endTime);// 全部订单数

		double percentage = Double.parseDouble(String.valueOf(exceptionNum))
				/ Double.parseDouble(String.valueOf(total));

		orderCountModel.setExceptionNumber(exceptionNum);
		orderCountModel.setExceptionPercentage(percentage);

		List<OrderCountModel> orderCountModels = new ArrayList<OrderCountModel>();
		orderCountModels.add(orderCountModel);

		// 存放总记录数，必须的
		jsonMap.put("total", 1);
		// rows键 存放每页记录 list
		jsonMap.put("rows", orderCountModels);

		return jsonMap;
	}

	@RequestMapping("goOrderExceptionDetailInfo/{orderid}")
	/**
	 * 跳转异常订单详细
	 * @param request
	 * @param model
	 * @param orderid
	 * @return
	 */
	public String goOrderExceptionDetailInfo(@PathVariable int orderid,
			HttpServletRequest request, Model model) throws BaseException {
		// 订单信息
		OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(orderid);
		// 美发师
		UsersubinfoModel usersubinfo = usersubinfoService
				.searchUsersubinfo(orderinfo.getUserid());
		UserPhotoMappingModel userPhotoMapping = userPhotoMappingService
				.searchUserPhotoMapping(usersubinfo.getUserid(), 1);
		// 店铺信息
		StoreinfoModel storeinfo = storeinfoService
				.searchStoreinfoModel(orderinfo.getStoreid());
		StorePhotoMappingModel storePhotoMapping = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 1);
		// 发型信息
		UserHairstyleModel userHairstyle = userHairstyleService
				.searchUserHairstyleById(orderinfo.getStyleid());
		// 下一步状态
		String nowStatus = Constant.PAYSTATE_MAP.get(orderinfo.getPaystate());
		// 下一步状态
		String nextStatus = Constant.NEXT_STEP_MAP.get(orderinfo.getPaystate());

		model.addAttribute("orderinfo", orderinfo);

		model.addAttribute("usersubinfo", usersubinfo);

		if (null != userPhotoMapping
				&& null != userPhotoMapping.getBasePicture()) {
			model.addAttribute("userPhoto", userPhotoMapping.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("userPhoto", "");
		}

		if (null != usersubinfo.getUserProfessionLevel()) {
			model.addAttribute("userLevel", usersubinfo
					.getUserProfessionLevel().getLevelname());
		} else {
			model.addAttribute("userLevel", "");
		}

		model.addAttribute("storeinfo", storeinfo);
		if (null != storePhotoMapping
				&& null != storePhotoMapping.getBasePicture()) {
			model.addAttribute("storePhoto", storePhotoMapping.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("storePhoto", "");
		}

		model.addAttribute("userHairstyle", userHairstyle.getName());
		model.addAttribute("nowStatus", nowStatus);
		model.addAttribute("nextStatus", nextStatus);

		return "orderinfo_exception_detail";
	}

	@RequestMapping("/OrderExceptionCompensate")
	/**
	 * 异常订单赔偿
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> OrderExceptionCompensate(
			HttpServletRequest request, String txtOrderId, String txtStoreId,
			String txtUserId, String txtCustomerId, String txtStoreCompensate,
			String txtStoreGetCompensate, String txtUserCompensate,
			String txtUserGetCompensate, String txtCustomerCompensate,
			String txtCustomerGetCompensate, Model model) throws BaseException {
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 执行赔偿，并修改订单状态
		if (orderinfoService.addOrderExceptionCompensate(txtOrderId,
				txtStoreId, txtUserId, txtCustomerId, txtStoreCompensate,
				txtStoreGetCompensate, txtUserCompensate, txtUserGetCompensate,
				txtCustomerCompensate, txtCustomerGetCompensate)) {
			jsonMap.put("info", true);
			jsonMap.put("message", "赔偿成功，请不要反复点击赔偿确认！");
		} else {
			jsonMap.put("info", false);
			jsonMap.put("message", "赔偿失败！");
		}
		return jsonMap;
	}
}