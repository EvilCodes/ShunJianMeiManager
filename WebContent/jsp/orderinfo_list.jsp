<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	int SECOND = Calendar.getInstance().get(Calendar.SECOND);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/orderinfo_list.js?<%=SECOND%>"></script>
</head>	
<body>
	<div class="main" style="background:none; width: 1000px; height: 680px;">
		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
	
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">订单管理</font>
		</div>
		
		<div style="width:100%; margin:20px auto;">
			<form id="ff" class="easyui-form" method="post" >
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td>
							用户ID：
						</td>	
						<td>	
							<input class="easyui-validatebox" id="txtCustomerId" name="txtCustomerId" type="text" />
						</td>	
						<td>	
							用户手机：
						</td>	
						<td>	
							<input class="easyui-validatebox" id="txtCustomerTelephone" name="txtCustomerTelephone" type="text" />
						</td>
						<td>
							美发师ID：
						</td>
						<td>	
							<input class="easyui-validatebox" id="txtUserId" name="txtUserId" type="text" />
						</td>	
					</tr>
					<tr>
						<td>	
							美发师手机：
							</td>	
						<td>
							<input class="easyui-validatebox" id="txtUserTelephone" name="txtUserTelephone" type="text" />
						</td>
						<td>
							商户ID：
							</td>	
						<td>
							<input class="easyui-validatebox" id="txtStoreId" name="txtStoreId" type="text" />
						</td>
						<td>
							订单ID：
							</td>	
						<td>
							<input class="easyui-validatebox" id="txtOrderId" name="txtOrderId" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							订单起止时间：
							</td>	
						<td>
							<input class="easyui-datebox" id="txtStartTime" name="txtStartTime" type="text" />
						</td>
						<td colspan="2">
							<input class="easyui-datebox" id="txtEndTime" name="txtEndTime" type="text" />
						</td>
						<td>
							订单状态：
						</td>	
						<td>
							<select id="txtPayState" name="txtPayState">
								<option value="0">全部</option>
								<option value="1">待支付</option>
								<option value="3">已预约</option>
								<option value="4">美发师已签到</option>
								<option value="5">服务中</option>
								<option value="6">待评价</option>
								<option value="7">评价完成</option>
								<option value="8">订单完成</option>
								<option value="9">已取消</option>
								<option value="10">异常处理</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="6">	
							<div style="text-align:center;padding:5px">
					            &nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100px;height: 25px" onclick="searchInfo()">筛选</a>&nbsp;&nbsp;&nbsp;
					            <a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100px;height: 25px" onclick="clearInfo()">取消</a>&nbsp;&nbsp;&nbsp;
					        </div>
						</td>
					</tr>
				</table>
			</form>
			<!-- datagrid-detailview -->
			<div style="height: 350">
				<!-- ------------------ -->
				<table id="cxdm"></table>
				<!-- ------------------- -->
			</div>
		</div>
		<div class="clear"></div>
		
	</div>
</body>
</html>