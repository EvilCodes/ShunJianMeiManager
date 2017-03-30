package com.wenyuankeji.spring.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ShunJianMeiUtil {
	
	private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)
	
	/**
	 * 格式化并输出json到流
	 * 
	 * @param request
	 * @param response
	 * @param jsonMap
	 *            返回输出json
	 * @return
	 */
	public static void outputJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> jsonMap) {
		try {
			response.setContentType("text/plain");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			JSONObject resultJSON = JSONObject.fromObject(jsonMap, jsonConfig); // 根据需要拼装json
			// String jsonpCallback = request.getParameter("jsonpCallback");//
			// 客户端请求参数
			String result = resultJSON.toString();
			result = result.replaceAll("\n", "");
			PrintWriter out = response.getWriter();
			out.println(result);// 返回jsonp格式数据
			// System.out.println(jsonpCallback + "(" + result + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String outputString( Map<String, Object> jsonMap) {


		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());

		JSONObject resultJSON = JSONObject.fromObject(jsonMap, jsonConfig); // 根据需要拼装json
		// String jsonpCallback = request.getParameter("jsonpCallback");//
		// 客户端请求参数
		String result = resultJSON.toString();
		result = result.replaceAll("\n", "");

		return result;
	}

	/**
	 * 创建随机数
	 * 
	 * @return
	 */
	public static String getRandomVCode() {
		String str = "";
		str += (int) (Math.random() * 9 + 1);
		for (int i = 0; i < 5; i++) {
			str += (int) (Math.random() * 10);
		}
		return str;
	}

	
	public static List<String> getFutureDays(int days) {
		 SimpleDateFormat sf = new SimpleDateFormat("yyyyMdd");
	        
	     List<String> futureDays = new ArrayList<String>();
		 
	        for (int i = 0; i < days; i++) {
	        	
	        	Calendar c = Calendar.getInstance();
	        	c.add(Calendar.DAY_OF_MONTH, i+1);
	        	futureDays.add(sf.format(c.getTime()));
			}
	        
	        return futureDays;
	}
	
	/**
	 * 判断验证码是否超时
	 * 
	 * @param expirationtime
	 * @return true：超时
	 */
	public static boolean checkVCodeDate(Date expirationtime) {

		long between = (expirationtime.getTime() - new Date().getTime()) / 1000;// 除以1000是为了转换成秒
		long minute = between % 3600 / 60;
		if (minute > 10) {
			return true;
		}

		return false;
	}

	public static String getYYYYMMDDhhmmss() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH)+1;// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
		int hour = cal.get(Calendar.HOUR);// 小时
		int minute = cal.get(Calendar.MINUTE);// 分
		int second = cal.get(Calendar.SECOND);// 秒

		return "" + year + month + day + hour + minute + second;

	}
	
	public static String getYYYYMMDD() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH)+1;// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
	

		return "" + year + month + day;

	}

	public static Date getNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        return calendar.getTime();
    }
	
	public static Date getNextMonth6() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 6);
		return calendar.getTime();
	}
	
	public static boolean checkNull(Object val) {

		if (null == val || val.toString().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static String dateConvertString(Date date){
		String sdate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
		return sdate;
	}
	
	public static String dateToString(Date date){
		String sdate = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
		return sdate;
	}
	
	/**
	 * 转化为弧度(rad)
	 * */
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}
	
	/**
	 * 基于余弦定理求两经纬度距离
	 * @param lon1 第一点的精度
	 * @param lat1 第一点的纬度
	 * @param lon2 第二点的精度
	 * @param lat3 第二点的纬度
	 * @return 返回的距离，单位km
	 * */
	public static double LantitudeLongitudeDist(double lon1, double lat1,double lon2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);

		double radLon1 = rad(lon1);
		double radLon2 = rad(lon2);

		if (radLat1 < 0)
			radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
		if (radLat1 > 0)
			radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
		if (radLon1 < 0)
			radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
		if (radLat2 < 0)
			radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
		if (radLat2 > 0)
			radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
		if (radLon2 < 0)
			radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
		double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
		double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
		double z1 = EARTH_RADIUS * Math.cos(radLat1);

		double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
		double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
		double z2 = EARTH_RADIUS * Math.cos(radLat2);

		double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
		//余弦定理求夹角
		double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
		double dist = theta * EARTH_RADIUS;
		return dist;
	}
	
	public static String intToString(int val){
		return String.valueOf(val);
	}
	public static String floatToString(float val){
		return String.valueOf(val);
	}
	
	/**
	 * 根据传入的年月日，返回固定格式的日期格式（yyyy-MM-dd）
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws Exception
	 */
	 public static String formatDateString(String year,String month,String day) throws Exception {
		if (month.length() == 1) {
			month = "0" + month;
		}
		if (day.length() == 1) {
			day = "0" + day;
		}
		return year + "-" + month + "-" + day;
		
	 }
	/**
     * 判断当前日期是星期几
     * 
     * @param pTime 需要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
	 public static String dayForWeek(String pTime) throws Exception {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar c = Calendar.getInstance();
		 c.setTime(format.parse(pTime));
		 int week = 0;
		 if(c.get(Calendar.DAY_OF_WEEK) == 1){
			 week = 7;
		 }else{
			 week = c.get(Calendar.DAY_OF_WEEK) - 1;
		 }
		 String dayForWeek = "";
		 switch (week) {
			case 1:
				dayForWeek = "周一";
				break;
			case 2:
				dayForWeek = "周二";
				break;
			case 3:
				dayForWeek = "周三";
				break;
			case 4:
				dayForWeek = "周四";
				break;
			case 5:
				dayForWeek = "周五";
				break;
			case 6:
				dayForWeek = "周六";
				break;
			case 7:
				dayForWeek = "周日";
				break;
			default:
				break;
		 }
		 return dayForWeek;
	 }
	 
	 public static String formatCouponType(String type){
		if (type.equals("TF")) {
			return "烫发";
		}
		if (type.equals("RF")) {
			return "染发";
		}
		if (type.equals("XJC")) {
			return "设计剪发";
		}
		if (type.equals("XC")) {
			return "造型";
		}
		if (type.equals("HL")) {
			return "护理";
		}
		return "";
	 }
	 
	/**
	 * 格式化并输出json到流
	 * 
	 * @param request
	 * @param response
	 * @param json
	 *            返回输出json
	 * @return
	 */
	 public static void outputResult(HttpServletRequest request,
				HttpServletResponse response, String json) {
		try {
			response.setContentType("text/plain");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			//JsonConfig jsonConfig = new JsonConfig();  
			//jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			//JSONObject resultJSON = JSONObject.fromObject(json, jsonConfig); // 根据需要拼装json
			String result = json.toString();
			result = result.replaceAll("\n", "");
			result = result.replaceAll("'", "\"");
			PrintWriter out = response.getWriter();
			out.println(result);// 返回jsonp格式数据
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 
	 @SuppressWarnings("static-access")
	public static boolean compressPic(String srcFilePath, String descFilePath)  
	    {  
	        File file = null;  
	        BufferedImage src = null;  
	        FileOutputStream out = null;  
	        ImageWriter imgWrier;  
	        ImageWriteParam imgWriteParams;  
	  
	        // 指定写图片的方式为 jpg  
	        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
	        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
	        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT  
	        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);  
	        // 这里指定压缩的程度，参数qality是取值0~1范围内，  
	        imgWriteParams.setCompressionQuality((float)0.4);  
	        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);  
	        ColorModel colorModel = ColorModel.getRGBdefault();  
	        // 指定压缩时使用的色彩模式  
	        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
	                .createCompatibleSampleModel(16, 16)));  
	  
	        try  
	        {  
	            if(StringUtils.isBlank(srcFilePath))  
	            {  
	                return false;  
	            }  
	            else  
	            {  
	                file = new File(srcFilePath);  
	                src = ImageIO.read(file);  
	                out = new FileOutputStream(descFilePath);  
	  
	                imgWrier.reset();  
	                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造  
	                imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
	                // 调用write方法，就可以向输入流写图片  
	                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);  
	                out.flush();  
	                out.close();  
	            }  
	        }  
	        catch(Exception e)  
	        {  
	            e.printStackTrace();  
	            return false;  
	        }  
	        return true;  
	    } 
	 
}
