package com.wenyuankeji.spring.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String MSG_0 = "0|操作成功";
	public static final String MSG_1 = "1|操作成功";
	public static final String MSG_0_0 = "0|";
	public static final String MSG_1_1 = "1|";
	public static final String DATA = "data";
	public static final String TOTAL = "total";

	public static final int CODE_1000 = 1000;
	public static final int CODE_1100 = 1100;
	public static final int CODE_1101 = 1101;
	public static final int CODE_1102 = 1102;
	public static final int CODE_1103 = 1103;
	public static final int CODE_1104 = 1104;
	public static final int CODE_1105 = 1105;
	public static final int CODE_1200 = 1200;
	public static final int CODE_2000 = 2000;
	public static final int CODE_2100 = 2100;

	public static final String CONFIGCODE_01 = "hobby";
	public static final String CONFIGCODE_02 = "hairstyle";
	public static final String CONFIGCODE_03 = "city";
	public static final String CONFIGCODE_04 = "homepage";

	public static final String CONFIGCODE_05 = "RegCouponMin_";
	public static final String CONFIGCODE_06 = "RegCouponMax_";
	public static final String CONFIGCODE_07 = "ShareCouponMin_";
	public static final String CONFIGCODE_08 = "ShareCouponMax_";
	
	public static final String CONFIGCODE_TF = "TF";
	public static final String CONFIGCODE_RF = "RF";
	public static final String CONFIGCODE_XJC = "XJC";
	public static final String CONFIGCODE_XC = "XC";
	public static final String CONFIGCODE_HL = "HL";
	
	public static final String CONFIGCODE_09 = "xjc";//洗剪吹
	public static final String CONFIGCODE_10 = "xc";//洗吹
	public static final String CONFIGCODE_11 = "tf";//烫发
	public static final String CONFIGCODE_12 = "rf";//染发
	public static final String CONFIGCODE_13 = "hl";//护理
	
	public static final String CONFIGCODE_14 = "UserAllocate";//美发师分成
	public static final String CONFIGCODE_15 = "StoreAllocate";//商户分成
	
	public static final String CONFIGCODE_16 = "accout_delay_day";//订单分成几天之后到账
	
	public static final String CONFIGCODE_17 = "accout_open_day";//订单分成几天之后到账
	
	public static final String CONFIGCODE_18 = "deposit";//美发师和商户的预留押金
	
	public static final String CONFIGCODE_19 = "service_phone";//客服电话
	
	public static final String CONFIGCODE_20 = "ShareCouponDays";//分享优惠券有效天数
	public static final String CONFIGCODE_21 = "RegCouponDays";//注册优惠券有效天数
	
	public static final String CONFIGCODE_22 = "RegCoupon";
	
	
	public static final int IMAGE_TYPE_07 = 7;//我的形象 
	public static final int IMAGE_TYPE_06 = 6;//我的代表作品 

	public static final Map<String, Boolean> CHECK_SESSION_MAP = new HashMap<String, Boolean>(){

		private static final long serialVersionUID = 2315647126169419915L;
		{
			put("goBaseConfig",true);
			put("goBaseSuggestion",true);
			put("goCouponSystem",true);
			put("HomepageChange",true);
			put("goOrderinfoList",true);
			put("goOrderExceptionInfoManage",true);
			put("goOrderCount",true);
			put("goUserTradeLogManage",true);
			put("goServerPricingManage",true);
			put("goServerTimeManage",true);
			put("goStoreinfoManage",true);
			put("goUserEvaluateManage",true);
			put("goUserMyhairstyleList",true);
			put("goUsersubinfoManage",true);
			put("goDetailUserSubInfo",true);
			put("Main",true);
		}
	};

	public static final String ALL_PERMISSION = "ALL";

	public static final int INDEXPAGESIZE = 9;// 每页显示几条数据

	public static final Map<Integer, String> NEXT_STEP_MAP = new HashMap<Integer, String>() {

		private static final long serialVersionUID = 2315647126169419915L;
		{
			put(1, "已预约");
			put(3, "美发师到店签到");
			put(4, "开始服务");
			put(5, "结束服务");
			put(6, "评价完成");
			put(7, "订单完成");
			put(8, "");
			put(9, "");
			put(10, "");

		}
	};

	public static final Map<Integer, String> PAYSTATE_MAP = new HashMap<Integer, String>() {

		private static final long serialVersionUID = 2315647126169419915L;
		{
			put(1, "待支付");
			put(3, "已预约");
			put(4, "美发师已签到");
			put(5, "服务中");
			put(6, "待评价");
			put(7, "评价完成");
			put(8, "订单完成");
			put(9, "已取消");
			put(10, "异常处理");
		}
	};

}