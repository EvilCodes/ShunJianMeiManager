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
<title>订单异常</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/orderExceptionInfo_manage.js"></script>
</head>	
<body>
	<div class="main" style="background:none; width: 1000px; height: 560px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
	
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">订单异常</font>
		</div>
		
		<div style="width:100%; margin:20px auto;">
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