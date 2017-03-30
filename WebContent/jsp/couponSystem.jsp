<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.wenyuankeji.spring.util.ShunJianMeiUtil"%>
<%
	String path = request.getContextPath();
	String date = ShunJianMeiUtil.getYYYYMMDDhhmmss();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>orderinfo_manage</title>
<link rel="shortcut icon" href="<%=path%>/css/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/couponSystem.js?<%=date %>"></script>
</head>
<body>
   <div class="box main" style="height:700px">
   	<div class="content">
       	<div class="prop" style="left:360px; height:600px;">
			<table width="100%">
       			<tr>
       				<td align="center">优惠券系统</td>
       			</tr>
       		</table>
        	<div style="width:100%; margin:10px auto;">
			<form id="ff" class="easyui-form" method="post" >
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">设置分享送优惠券价格</td>
						<td align="left" width="20%">
						<input id="ShareCouponMin" name="ShareCouponMin" type="text" style="width:100px;" value="${ShareCouponMin }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="left" width="20%">
						<input id="ShareCouponMax" name="ShareCouponMax" type="text" style="width:100px;" value="${ShareCouponMax }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="left" width="20%">
							<select id="txtShareCoupon" name="txtShareCoupon" style="width:100px;height:22px" onchange="shareCouponOnchange()">
								<!-- <option value="TF">烫发</option>
								<option value="RF">染发</option> -->
								<option value="XJC">设计剪发</option>
								<!-- <option value="XC">造型</option>
								<option value="HL">护理</option> -->
							</select>
						</td>
						<td align="left" width="15%">
						<input id="updateShareCouponButton" name="updateShareCouponButton" type="button" onclick="updateShareCoupon()" value="保存" />
						</td>
					</tr>
					<tr>
						<td align="right" width="25%">分享优惠券有效天数</td>
						<td align="left" width="20%">
						<input id="txtShareCouponDays" name="txtShareCouponDays" type="text" style="width:100px;" value="${ShareCouponDays }"
							onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="left" colspan="3">
						<input id="ShareCouponDaysButton" name="ShareCouponDaysButton" type="button" onclick="updateShareCouponDays()" value="保存" />
						</td>
					</tr>
				</table>
				<hr />
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">设置送用户优惠券</td>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						用户手机号
						</td>
						<td align="left" width="20%">
						<input id="mobile" name="mobile" type="text" style="width:150px;" value=""
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center" colspan="2">
						</td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						送优惠券的金额
						</td>
						<td align="left" width="20%">
						<input id="amount" name="amount" type="text" style="width:150px;" value=""
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center" colspan="2">
						</td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						送优惠券的类型
						</td>
						<td align="left" width="20%">
							<select id="txtUserCoupon" name="txtUserCoupon" style="width:150px;height:22px">
								<option value="TF">烫发</option>
								<option value="RF">染发</option>
								<option value="XJC">设计剪发</option>
								<option value="XC">造型</option>
								<option value="HL">护理</option>
								<option value="TC">套餐</option>
							</select>
						</td>
						<td align="left" colspan="2">
							<input id="addUserCouponButton" name="addUserCouponButton" type="button" onclick="addUserCoupon()" value="保存" />
						</td>
					</tr>
				</table>
				<hr />
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">设置注册送优惠券价格</td>
						<td align="left" width="20%">
						<input id="RegCouponMin" name="RegCouponMin" type="text" style="width:100px;" value="${RegCouponMin }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="left" width="20%">
						<input id="RegCouponMax" name="RegCouponMax" type="text" style="width:100px;" value="${RegCouponMax }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="left" width="20%">
							<select id="txtRegCoupon" name="txtRegCoupon" style="width:100px;height:22px" onchange="regCouponOnchange()">
								<option value="TF">烫发</option>
								<option value="RF">染发</option>
								<option value="XJC">设计剪发</option>
								<option value="XC">造型</option>
								<option value="HL">护理</option>
								<option value="TC">套餐</option>
							</select>
						</td>
						<td align="left" width="15%">
						<input id="updateRegCouponButton" name="updateRegCouponButton" type="button" onclick="updateRegCoupon()" value="保存" />
						</td>
					</tr>
					<tr>
						<td align="right" width="25%">注册优惠券有效天数</td>
						<td align="left" width="20%">
						<input id="txtRegCouponDays" name="txtRegCouponDays" type="text" style="width:100px;" value="${RegCouponDays }"
							onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="left" colspan="3">
						<input id="RegCouponDaysButton" name="RegCouponDaysButton" type="button" onclick="updateRegCouponDays()" value="保存" />
						</td>
					</tr>
				</table>
				<hr />
				
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">注册后赠送的优惠卷</td>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						护理的优惠卷数
						</td>
						<td align="left" width="20%">
						<input id="regCouponHl" maxlength="2" type="text" style="width:150px;" value="${regCouponHl }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center" colspan="2">
						</td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						染发的优惠卷数
						</td>
						<td align="left" width="20%">
						<input id="regCouponRf" maxlength="2" type="text" style="width:150px;" value="${regCouponRf }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center" colspan="2">
						</td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						烫发的优惠卷数
						</td>
						<td align="left" width="20%">
						<input id="regCouponTf" maxlength="2" type="text" style="width:150px;" value="${regCouponTf }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center" colspan="2">
						</td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						造型的优惠卷数
						</td>
						<td align="left" width="20%">
						<input id="regCouponXc" maxlength="2" type="text" style="width:150px;" value="${regCouponXc }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center" colspan="2">
						</td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						设计剪发的优惠卷数
						</td>
						<td align="left" width="20%">
						<input id="regCouponXjc" maxlength="2" type="text" style="width:150px;" value="${regCouponXjc }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center" colspan="2">
						</td>
					</tr>
					<tr>
						<td align="right" width="25%"></td>
						<td align="right" width="20%">
						套餐优惠卷数
						</td>
						<td align="left" width="20%">
						<input id="regCouponTc" maxlength="2" type="text" style="width:150px;" value="${regCouponTc }"
						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						</td>
						<td align="center">
						<input  type="button" onclick="addRegCoupon()" value="保存" />
						</td>
					</tr>
				</table>
			
			</form>
		</div>
	</div>
	</div>
</div>
</body>
</html>