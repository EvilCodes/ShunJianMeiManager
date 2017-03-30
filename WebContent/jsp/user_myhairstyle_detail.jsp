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
<title>发型详情</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
</head>
<body>
	<p style="font-size:14px">发型详情</p>
	<hr>
	<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
		<tr>
			<td align="left">美发师ID：${userinfo.userid}</td>
		</tr>
		<tr>
			<td align="left">手机号：${userinfo.username}</td>
		</tr>
		<tr>
			<td align="left">审核状态：
				<c:if test="${userMyhairstyle.state == 1}">
					审核中
				</c:if>
				<c:if test="${userMyhairstyle.state == 2}">
					审核失败
				</c:if>
				<c:if test="${userMyhairstyle.state == 3}">
					待发布
				</c:if>
				<c:if test="${userMyhairstyle.state == 4}">
					已发布
				</c:if>
			</td>
		</tr>
		<tr>
			<td align="left">发型简介：<br>
				<img src="${pohot1}" width="200px" height="200px" />
			</td>
		</tr>
		<tr>
			<td align="left">发型详情：<br>
				<img src="${pohot1}" width="200px" height="200px" />
			</td>
		</tr>
		<tr>
			<td align="left">${hl_name}：${hl_time}分钟</td>
		</tr>
		<tr>
			<td align="left">${rf_name}：${rf_time}分钟</td>
		</tr>
		<tr>
			<td align="left">${tf_name}：${tf_time}分钟</td>
		</tr>
		<tr>
			<td align="left">${xc_name}：${xc_time}分钟</td>
		</tr>
		<tr>
			<td align="left">${xjc_name}：${xjc_time}分钟</td>
		</tr>
	</table>
</body>
</html>