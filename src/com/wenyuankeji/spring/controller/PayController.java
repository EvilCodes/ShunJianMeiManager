package com.wenyuankeji.spring.controller;

import java.text.SimpleDateFormat;
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
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserTradelogCountModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserTradelogSearchModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IUserCashrecordService;
import com.wenyuankeji.spring.service.IUserTradeLogService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.util.BaseException;

@Controller
public class PayController {

	@Autowired
	private IUserCashrecordService userCashrecordService;

	@Autowired
	private IUserTradeLogService userTradeLogService;

	@Autowired
	private IOrderinfoService orderinfoService;

	@Autowired
	private IUserinfoService userinfoService;
	
	@RequestMapping("goUserCashrecordManage")
	/**
	 * 跳转提现申请
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUserCashrecordManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			return "user_cashrecord_list";
		}else{
			return "login";
		}
	}

	@RequestMapping("goUserTradeLogManage")
	/**
	 * 跳转流水查看
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUserTradeLogManage(HttpServletRequest request, Model model) {

		return "user_tradelog_list";
	}

	@RequestMapping("UserCashrecordManage")
	/**
	 * 提现列表
	 * @param request
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws BaseException
	 */
	public @ResponseBody Map<String, Object> UserCashrecordManage(
			HttpServletRequest request, Model model, String startTime,
			String endTime, String page, String rows) throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);

		List<UserCashrecordModel> userCashrecordList = userCashrecordService
				.searchUserCashrecord(startTime, endTime, intPage, intRows);

		if (null != userCashrecordList && userCashrecordList.size() > 0) {
			for (UserCashrecordModel o : userCashrecordList) {
				o.setUserphone(userinfoService.searchUserinfoById(o.getUserid()).getUsername());
			}
		}
		
		// 存放总记录数，必须的
		jsonMap.put("total",
				userCashrecordService.searchUserCashrecord(startTime, endTime));
		// rows键 存放每页记录 list
		jsonMap.put("rows", userCashrecordList);

		return jsonMap;
	}
	
	@RequestMapping("/UserCashrecordUpdate")
	/**
	 * 修改用户状态
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> UserCashrecordUpdate(int recordid,
			int state, Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			if (userCashrecordService.updateUserCashrecordById(recordid, state)) {
				editInfoMap.put("editInfo", true);
				editInfoMap.put("message", "提现申请状态修改成功！");
			} else {
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "提现申请状态修改失败！");
			}
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return editInfoMap;
	}
	

	@RequestMapping("UserTradeLogManage")
	/**
	 * 流水列表
	 * @param request
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws BaseException
	 */
	public @ResponseBody Map<String, Object> UserTradeLogManage(
			HttpServletRequest request, Model model, String startTime,
			String endTime, String page, String rows) throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);

		List<UserTradelogModel> userTradelogList = userTradeLogService
				.searchUserTradelog(startTime, endTime, intPage, intRows);
		
		List<UserTradelogSearchModel> searchModelList = new ArrayList<UserTradelogSearchModel>();

		for (int i = 0; i < userTradelogList.size(); i++) {
			UserTradelogSearchModel searchModel = new UserTradelogSearchModel();
			UserTradelogModel userTradelog = userTradelogList.get(i);
			OrderinfoModel orderinfo = orderinfoService
					.searchOrderinfo(userTradelog.getOrderid());
			if (orderinfo != null) {
				searchModel.setOrdercode(orderinfo.getOrdercode());
				searchModel.setStylename(orderinfo.getStylename());
				searchModel.setAppointmenttime(orderinfo.getAppointmenttime());
				searchModel.setAmount(String.valueOf(userTradelog.getAmount()));
				
				UserinfoModel user = userinfoService.searchUserinfoById(orderinfo.getCustomerid());
				if (null != user) {
					searchModel.setUsername(user.getNickname());
					searchModel.setUserphone(user.getUsername());
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
				String str = sdf.format(userTradelog.getCreatetime());
				searchModel.setCreatetime(str);

				searchModelList.add(searchModel);
			}
			
		}

		// 存放总记录数，必须的
		jsonMap.put("total",
				userTradeLogService.searchUserTradelog(startTime, endTime).size());
		// rows键 存放每页记录 list
		jsonMap.put("rows", searchModelList);

		return jsonMap;
	}
	
	@RequestMapping("UserTradeLogTotal")
	/**
	 * 统计流水列表金额
	 * @param request
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws BaseException
	 */
	public @ResponseBody Map<String, Object> UserTradeLogTotal(
			HttpServletRequest request, Model model, String startTime,
			String endTime) throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		List<UserTradelogModel> userTradelogList = userTradeLogService
				.searchUserTradelog(startTime, endTime);
		
		float total = 0;
		for (int i = 0; i < userTradelogList.size(); i++) {
			UserTradelogModel userTradelog = userTradelogList.get(i);
			total += userTradelog.getAmount();
		}
		
		jsonMap.put("total", String.valueOf(total));

		return jsonMap;
	}
	
	@RequestMapping("goUserTradeLogCount")
	/**
	 * 跳转流水统计
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUserTradeLogCount(HttpServletRequest request, Model model) {

		return "user_tradelog_count";
	}

	@RequestMapping("UserTradeLogCount")
	/**
	 * 流水统计
	 * @param request
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws BaseException
	 */
	public @ResponseBody Map<String, Object> UserTradeLogCount(
			HttpServletRequest request, Model model, String startTime,
			String endTime) throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<UserTradelogCountModel> list = new ArrayList<UserTradelogCountModel>();
		UserTradelogCountModel userTradelogCount = new UserTradelogCountModel();

		String shouru = userTradeLogService.searchCountUserTradelog("1,2", startTime, endTime);
		String fencheng = userTradeLogService.searchCountUserTradelog("3", startTime, endTime);
		String quxiao = userTradeLogService.searchCountUserTradelog("5", startTime, endTime);
		String youhui = orderinfoService.searchOrderinfoCount(startTime, endTime);
		
		userTradelogCount.setShouru(shouru);
		userTradelogCount.setFencheng(fencheng);
		userTradelogCount.setQuxiao(quxiao);
		userTradelogCount.setYouhui(youhui);
		list.add(userTradelogCount);

		jsonMap.put("total", 1);
		// rows键 存放每页记录 list
		jsonMap.put("rows", list);

		return jsonMap;
	}
}