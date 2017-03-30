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
<title>header</title>

</head>
<body style="background-color: #d53326">
<table width="100%">
<tr>
	<td><img style="margin-top: -6px;margin-left: 150px" src="<%=path%>/css/images/shunjian.png"></td>
</tr>
<tr>
	<td align="right"><span>您好、${sessionUserinfo.truename }</span>&nbsp;&nbsp;<a target="_parent" href="<%=path%>/goUserinfoLogout"><span>退出</span></a></td>
</tr>
</table>
</body>
</html>