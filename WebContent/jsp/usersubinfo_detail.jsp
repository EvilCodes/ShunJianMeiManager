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
<title>美发师信息</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/usersubinfo_detail.js?<%=date %>>"></script>
</head>
<body>
<div class="main" style="background:none; width:1000px; height:560px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">美发师管理</font>
			<input id="userid" type="hidden" value="${userId}" >
		</div>

		<div style="width:100%; margin:20px auto;">
			<form id="ff" class="easyui-form" method="post" >
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr align="left">
						<td colspan="6">基本信息：
						<input type="hidden" id="hidUserid" value="${userId}">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100px;height: 25px" onclick="showAuditInfo()">查看审核信息</a>
						</td>
						<td colspan="3">
							<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100px;height: 25px" onclick="showBusinessInfo()">查看业务信息</a>
						</td>
					</tr>
				</table>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr align="left">
						<td colspan="4">状态信息：</td>
					</tr>
					<tr>
						<td align="left">定级:</td>
						<td align="left">
							<select id="txtLevel" name="txtLevel" style="width:210px;">
								<c:forEach var="userProfessionLeve" items="${UserProfessionLevelList}">
									<option value="${userProfessionLeve.levelid}">${userProfessionLeve.levelname}</option>
								</c:forEach>
							</select>
							<input id="hidLevel" type="hidden" value="${levelid}" >
						</td>
						<td align="left">分成:</td>
						<td>
							<input id="txtAllocation" name="txtAllocation" value="${allocation }"
							onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
							 <input type="checkbox" id="defaultAllocation" name="defaultAllocation">使用默认
						</td>
					</tr>
					<tr>
						<td align="left">状态:</td>
						<td align="left" colspan="3">
							<select id="txtCheckstate" name="txtCheckstate" style="width:210px;">
								<option value="1">审核中</option>
								<option value="2">审核通过</option>
								<option value="3">审核失败</option>
							</select>
							<input id="hidCheckstate" type="hidden" value="${checkstate}" >
						</td>
					</tr>
					<tr align="center">
						<td colspan="6"><a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100px;height: 25px" onclick="addLevelAndCheckstate()">保存</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="clear"></div>
		
	</div>

</body>
</html>