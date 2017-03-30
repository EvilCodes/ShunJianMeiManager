package com.wenyuankeji.spring.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MD5Util {
	/***
	 * MD5加码 生成32位md5码
	 */
	public static String Encryption(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String Decrypt(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 测试
	public static void main(String args[]) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMdd hh:mm:ss");
		
//		Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(new Date());
//        rightNow.set(Calendar.MINUTE,rightNow.get(Calendar.MINUTE)+10);
//        Date dt1=rightNow.getTime();
//        String reStr = sdf.format(dt1);
//        System.out.println(reStr);
		
		String w = new String("admin");
		System.out.println("原    始：" + w);
		System.out.println("加密后：" + Encryption(w));
		System.out.println("解密的：" + Decrypt(Decrypt(w)));
		
		
		for (int i = 0; i < 4; i++) {
        	
        	Calendar c = Calendar.getInstance();
        	c.add(Calendar.DAY_OF_MONTH, i+1);
        	System.out.println(sdf.format(c.getTime()));
		}
		
	}
}
