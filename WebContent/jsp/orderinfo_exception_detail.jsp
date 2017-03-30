<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>异常订单详情</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/orderinfo_exception_detail.js"></script>
</head>
<body>
	<a href="<%=path%>//goOrderExceptionInfoManage" class="easyui-linkbutton" style="width: 100px;height: 25px">返回</a>
	<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">异常订单详情</font>
		</div>
	<hr>
	<table style="width:80%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
		<tr>
			<td align="left">订&nbsp;单&nbsp;号：&nbsp;${orderinfo.ordercode}</td>
		</tr>
		<tr>
			<td align="left">订单状态&nbsp;：&nbsp;${nowStatus}</td>
		</tr>
		<tr>
			<td align="left">下&nbsp;一&nbsp;步：&nbsp;${nextStatus}</td>
		</tr>
	</table>
	
	<table style="width:80%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
		<tr>
			<td align="left" colspan="2">预约时间：${orderinfo.appointmenttime}</td>
		</tr>
		<tr>
			<td align="left">
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
					<tr>
						<td rowspan="3"><img src="${imgIpHost}${userPhoto}" width="100px" height="100px"></td>
					</tr>
					<tr>
						<td width="50%">${usersubinfo.truename}&nbsp;&nbsp;&nbsp;&nbsp;${userLevel}</td>
					</tr>
					<tr>
						<td>接单量${usersubinfo.ordernum}次</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td align="left">
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
					<tr>
						<td rowspan="4"><img src="${imgIpHost}${storePhoto}" width="100px" height="100px"></td>
					</tr>
					<tr>
						<td width="50%">${storeinfo.name}</td>
					</tr>
					<tr>
						<td>地址：${storeinfo.address}</td>
					</tr>
					<tr>
						<td>接单量${storeinfo.orderquantity}次 &nbsp;&nbsp;&nbsp;&nbsp;${storeinfo.carnumber}个停车位</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">${userHairstyle}&nbsp;&nbsp;&nbsp;&nbsp;¥${orderinfo.amount}</td>
		</tr>
	</table>
	
	<table style="width:80%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
		<tr>
			<td align="left">项目：${usersubinfo.hairstyle}</td>
		</tr>
		<tr>
			<td align="left">
				<c:if test="${orderinfo.additionalstate == 0}">
					加单：无
				</c:if>
				<c:if test="${orderinfo.additionalstate != 0}">
					加单：${orderinfo.additionalcontent} &nbsp;&nbsp;&nbsp; ¥${orderinfo.additionalamount}
				</c:if>
			</td>
		</tr>
	</table>
	
	<p style="font-size:14px">支付信息</p>
	<hr>
	<table style="width:80%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
		<tr>
			<td align="left">基本支付：¥${orderinfo.amount}</td>
		</tr>
		<tr>
			<td align="left">
				<c:if test="${orderinfo.additionalstate == 0}">
					加单支付：无
				</c:if>
				<c:if test="${orderinfo.additionalstate != 0}">
					加单支付：¥${orderinfo.additionalamount}
				</c:if>
			</td>
		</tr>
		<tr>
			<td align="left">优惠：¥${orderinfo.couponamount}</td>
		</tr>
	</table>
	<br>
	
	<p style="font-size:14px">赔偿机制</p>
	<hr>
	<table style="width:80%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
		<tr>
			<td align="left">
				商户赔偿：
				<input id="txtStoreCompensate" name="txtStoreCompensate" type="text" style="width:150px"
					onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
				<input id="txtStoreId" name="txtStoreId" type="hidden" value="${orderinfo.storeid }" />
				<input id="txtOrderId" name="txtOrderId" type="hidden" value="${orderinfo.orderid }" />
			</td>
			<td align="left">
				商户获得赔偿：
				<input id="txtStoreGetCompensate" name="txtStoreGetCompensate" type="text" style="width:150px" 
					onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
			</td>
		</tr>
		<tr>
			<td align="left">
				美发师赔偿：
				<input id="txtUserCompensate" name="txtUserCompensate" type="text" style="width:150px"
					onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
				<input id="txtUserId" name="txtUserId" type="hidden" value="${orderinfo.userid }" />
			</td>
			<td align="left">
				美发师获得赔偿：
				<input id="txtUserGetCompensate" name="txtUserGetCompensate" type="text" style="width:150px"
					onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
			</td>
		</tr>
		<tr>
			<td align="left">
				用户赔偿：
				<input id="txtCustomerCompensate" name="txtCustomerCompensate" type="text" style="width:150px"
					onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
				<input id="txtCustomerId" name="txtCustomerId" type="hidden" value="${orderinfo.customerid }" />
			</td>
			<td align="left">
				用户获得赔偿：
				<input id="txtCustomerGetCompensate" name="txtCustomerGetCompensate" type="text" style="width:150px"
					onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				<a href="javascript:void(0)" id="btnOK" class="easyui-linkbutton" style="width: 60px;height: 20px">赔偿确定</a>
			</td>
		</tr>
	</table>
	<br>
	
</body>
</html>