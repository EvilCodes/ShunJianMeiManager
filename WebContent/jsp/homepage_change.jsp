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
<title>user_list</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/homepage_change.js"></script>
</head>	
<body>
	<div class="main" style="background:none; width: 1000px; height: 560px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">首页图片更换</font>
		</div>
		
		<div style="width:100%; margin:20px auto;">
			<form name="validateinfoFrom" method="POST" enctype="multipart/form-data" action="<%=path %>/HomepageChange">
				<table border="0">
					<tr>
						<td width="25%">
							美发师图片 ：
						</td>
						<td>
							<img id="imgFileName2" src="${imgIpHost}/${fileName2}" style="width:160px;height:90px" />
							<a id="upload_file2" class="easyui-linkbutton" style="cursor:hand" class="button pink">上传</a>
							<input id="fileName2" name="fileName2" type="hidden" value="${fileName2}" />
						</td>
					</tr>
					<tr>
						<td width="25%">
							美发店图片：
						</td>
						<td>
							<img id="imgFileName3" src="${imgIpHost}/${fileName3}" style="width:160px;height:90px" />
							<a id="upload_file3" class="easyui-linkbutton" style="cursor:hand" class="button pink">上传</a>
							<input id="fileName3" name="fileName3" type="hidden" value="${fileName3}" />
						</td>
					</tr>
					<tr>
						<td width="25%">
							发型图片：
						</td>
						<td>							
							<img id="imgFileName1" src="${imgIpHost}/${fileName1}" style="width:160px;height:90px" />
							<a id="upload_file1" class="easyui-linkbutton" style="cursor:hand" class="button pink">上传</a>
							<input id="fileName1" name="fileName1" type="hidden" value="${fileName1}" />
						</td>
					</tr>
					<tr>
						<td width="25%">
							价格图片：
						</td>
						<td>
							<img id="imgFileName5" src="${imgIpHost}/${fileName5}" style="width:160px;height:90px" />
							<a id="upload_file5" class="easyui-linkbutton" style="cursor:hand" class="button pink">上传</a>
							<input id="fileName5" name="fileName5" type="hidden" value="${fileName5}" />
						</td>
					</tr>
				</table>
				<a href="javascript:void(0)" id="btnSave" class="easyui-linkbutton" style="width: 100px;height: 25px" >保存</a>
			</form>
		</div>
		
	</div>
</body>
</html>