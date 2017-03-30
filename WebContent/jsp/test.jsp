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
<title>test</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
</head>
<body>
	<div class="main" style="background:none; width: 1000px; height: 900px;">
	<form action="<%=path%>/test/sql" method="post">
		<input type="text"  style="width: 800px;height: 400px" id="txtTest" name="txtTest">
		<br><br>
		<input type="submit" style="width: 200px;" value="test" />
		<br><br>
		
	</form>
		<div>
			${msg}
		</div>
	</div>
</body>
</html>