<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/css.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/login.js"></script>
<script type="text/javascript">
if(window !=top){  
    top.location.href=location.href;  
}  
</script>
</head>	
<body>
	<div class="main" style="background:none; width: 1000px; height: 900px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		
		<div class="logo">
		</div>
		<div class="clear"></div>
		
		<div style="width:100%; margin:10px auto; text-align:center">
			<font size="4">管理员登录</font>
		</div>
		
		<div style="width:100%; margin:20px auto;">
			<form id="ff" class="easyui-form" method="post" >
				<table border="0" style="width:100%;" align="center">
					<tr>
						<td align="center">
							手机号：
							<input class="easyui-validatebox" id="username" name="username" type="text" value="13439553792" />
						</td>
					</tr>
					<tr>
						<td align="center">
							密&nbsp;&nbsp;&nbsp;&nbsp;码：
							<input class="easyui-validatebox" id="password" name="password" type="password" value="371402" />
						</td>
					</tr>
					<tr>
						<td align="center">
				            &nbsp;&nbsp;&nbsp;
				            &nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center">
				            &nbsp;&nbsp;&nbsp;
				            <a href="javascript:void(0)" id="btnOK" class="easyui-linkbutton" style="width: 80px;height: 30px">登录</a>
				            &nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>