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
<title>服务定价</title>
<link rel="shortcut icon" href="<%=path%>/css/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/serverPricing_manage.js?<%=date %>"></script>
</head>
<body>
    <div class="box main" style="height:700px">
    	<div class="content">
        	<div class="prop" style="height: 300px;">
        		<table width="100%">
        			<tr>
        				<td width="50%">服务定价配置</td>
        			</tr>
        		</table>
				<hr/>
				<form name="baseconfigFrom" method="POST" enctype="multipart/form-data">
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left">服务级别:</td>
						<td align="left" colspan="3">
							<input id="levelid" name="levelid" type="hidden" value="${levelid }"/>
							<select id="txtLevelid" name="txtLevelid" style="width:100px;height:22px" onchange="getLevelid()">
								<c:forEach var="userProfessionLevel" items="${userProfessionLevelList}">
									<option value="${userProfessionLevel.levelid}">${userProfessionLevel.levelname}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="left">洗剪吹:</td>
						<td align="left">
							<input id="xjc" name="xjc" value="${xjc }">元
						</td>
						<td align="left">洗吹:</td>
						<td align="left">
							<input id="xc" name="xc" value="${xc }">元
						</td>
					</tr>
					<tr>
						<td align="left">烫发:</td>
						<td align="left">
							<input id="tf" name="tf" value="${tf }">元
						</td>
						<td align="left">染发:</td>
						<td align="left">
							<input id="rf" name="rf" value="${rf }">元
						</td>
					</tr>
					<tr>
						<td align="left">护理:</td>
						<td align="left" colspan="3">
							<input id="hl" name="hl" value="${hl }">元
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input id="saveButton" name="saveButton" type="button"  onclick="doSaveServerPricing()" value="确定" />
						</td>
					</tr>
				</table>
				</form>
            </div>
        </div>
    </div>
    
</body>
</html>