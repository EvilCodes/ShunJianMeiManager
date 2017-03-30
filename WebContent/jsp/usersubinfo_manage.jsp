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
<title>美发师管理</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/usersubinfo_manage.js?<%=SECOND%>"></script>
</head>
<body>
<div class="main" style="background:none; width: 1000px; height: 560px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">美发师管理</font>
		</div>

		<div style="width:100%; margin:20px auto;">
			<form id="ff" class="easyui-form" method="post" >
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left">美发师ID:</td>
						<td align="left">
						<input id="userid" name="userid" type="text" style="width:210px;"/></td>
						<td align="left">美发师手机:</td>
						<td align="left">
						<input id="bindphone" name="bindphone" type="text" style="width:150px;"/></td>
						<td align="left">美发师昵称:</td>
						<td align="left">
						<input id="txtNickname" name="txtNickname" type="text" style="width:150px;"/></td>
					</tr>
					<tr>
						<td align="left">注册起止时间:</td>
						<td align="left">
						<input class="easyui-datebox" style="width:100px" type="text" id="txtdateFrom" name="txtdateFrom" ></input>~
						<input class="easyui-datebox" style="width:100px" type="text" id="txtdateTo" name="txtdateTo" ></input>
						</td>
						<td align="left">美发师状态:</td>
						<td align="left">
							<select id="txtState" name="txtState" style="width:150px;">
								<option value="0">全部</option>
								<option value="1">不可接单</option>
								<option value="2">可接单</option>
							</select>
						</td>
						<td>
						&nbsp;
						</td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px;height: 25px" onclick="searchInfo()">筛选</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px;height: 25px" onclick="reset()">重置</a></td>
					</tr>
				</table>
				
			</form>
			<!-- datagrid-detailview -->
			<div style="height: 350px">
				<!-- ------------------ -->
				<table id="cxdm"></table>
				<!-- ------------------- -->
			</div>
		</div>
		<div class="clear"></div>
		
	</div>

</body>
</html>