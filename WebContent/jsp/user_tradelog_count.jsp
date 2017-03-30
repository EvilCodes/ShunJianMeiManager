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
<title>支付流水</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/user_tradelog_count.js"></script>
</head>	
<body>
	<div class="main" style="background:none; width: 1000px; height: 570px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		<div id="nav">
			<ul>
				<li><a href="<%=path%>/goUserTradeLogManage">流水查看</a></li>
				<li><a href="<%=path%>/goUserCashrecordManage">提现申请</a></li>
				<li><a href="<%=path%>/goUserTradeLogCount">流水统计</a></li>
			</ul>
		</div>
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">流水统计</font>
		</div>
		
		<div style="width:100%; margin:20px auto;">
			<form id="ff" class="easyui-form" method="post" >
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td>
							流水起止时间：
						</td>
						<td>
							<input class="easyui-datebox" id="txtStartTime" name="txtStartTime" type="text" />
							<input class="easyui-datebox" id="txtEndTime" name="txtEndTime" type="text" />
						</td>
						<td>	
							<div style="text-align:center;padding:5px">
					            &nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"  style="width: 100px;height: 25px" onclick="searchInfo()">查看</a>&nbsp;&nbsp;&nbsp;
					            <a href="javascript:void(0)" class="easyui-linkbutton"  style="width: 100px;height: 25px" onclick="clearInfo()">取消</a>&nbsp;&nbsp;&nbsp;
					        </div>
						</td>
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