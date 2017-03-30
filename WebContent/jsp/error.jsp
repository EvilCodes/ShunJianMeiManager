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
<title>error</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/question_import.js"></script>
</head>
<body>
	<div class="main" style="background:none; width: 1000px; height: 900px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		
		<div class="logo">
			<a href="<%=path %>" target="_blank"><img src="<%=path %>/css/images/logo.png" width="316px" height="60px;" /></a>
		</div>
		<div class="clear"></div>
		
		<div style="width:100%; margin:10px auto; text-align:center">
			<c:choose>
				<c:when test="${fn:length(errorMsg) > 0}">
					<font size="4">${errorMsg }</font>
				</c:when>
				<c:otherwise>
					<font size="4">上传的文件大于2M，请重新上传！！！</font>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>