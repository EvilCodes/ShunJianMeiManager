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
<title>评价详情</title>
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
	<p style="font-size:14px">评价详情</p>
	<hr>
	<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
		<tr>
			<td align="left">评分信息：<br>${score}</td>
		</tr>
		<tr>
			<td align="left">评价信息：<br>${content}</td>
		</tr>
		<tr>
			<td align="left">照片：<br>
				<c:forEach var="photo" items="${photoList}">
					<img src="${photo}" width="200px" height="200px" /><br>
				</c:forEach>
			</td>
		</tr>
	</table>
</body>
</html>