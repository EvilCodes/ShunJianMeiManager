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
<title>user_list</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/userinfo_list.js?<%=date %>>"></script>
</head>	
<body>
	<div class="main" style="background:none; width: 1000px; height: 800px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">用户管理</font>
		</div>
		
		<div style="width:100%; margin:20px auto;">
			<form id="ff" class="easyui-form" method="post" >
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td width="100px">
							用户ID：
							<input class="easyui-validatebox" id="txtUserid" name="txtUserid" type="text" />
						</td>
						<td width="100px">
							用户手机：
							<input class="easyui-validatebox" id="txtUsername" name="txtUsername" type="text" />
						</td>
						<td width="100px">	
							用户昵称：
							<input class="easyui-validatebox" id="txtNickname" name="txtNickname" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							用户性别：
							<select id="txtSex" name="txtSex" style="width:70px;">
								<option value="-1">全部</option>
								<option value="0">未知</option>
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</td>
						<td>
							注册时间：
							<input class="easyui-datebox" style="width:100px" type="text" id="txtdateFrom" name="txtdateFrom" ></input>~
							<input class="easyui-datebox" style="width:100px" type="text" id="txtdateTo" name="txtdateTo" ></input>
						</td>
						<td>	
							<div style="text-align:center;padding:5px">
					            &nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px;height: 25px" onclick="searchInfo()">筛选</a>&nbsp;&nbsp;&nbsp;
					            <a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px;height: 25px" onclick="clearInfo()">取消</a>
					        </div>
						</td>
					</tr>
				</table>
			</form>
			<!-- datagrid-detailview -->
			<div style="height: 600px">
				<!-- ------------------ -->
				<table id="cxdm"></table>
				<!-- ------------------- -->
			</div>
		</div>
		<div class="clear"></div>
		
	</div>
</body>
</html>