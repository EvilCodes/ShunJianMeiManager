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
<title>index</title>
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	font-size: 12px;
	font-family: "Microsoft Yahei", Verdana, Arial, Helvetica, sans-serif;
	height: 600px;
	/*font: 13px Microsoft YaHei, Verdana, Geneva, sans-serif;
		 background: #E0B6E3; */
	FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0, startColorStr=#FFFFFF, endColorStr=#D1E1F1 ); /*IE 6 7 8*/
	background: -ms-linear-gradient(top, #FFFFFF, #D1E1F1); /* IE 10 */
	background: -moz-linear-gradient(top, #FFFFFF, #D1E1F1); /*火狐*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#FFFFFF), to(#D1E1F1) ); /*谷歌*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#FFFFFF), o(#D1E1F1) ); /* Safari 4-5, Chrome 1-9*/
	background: -webkit-linear-gradient(top, #FFFFFF, #D1E1F1);/*Safari5.1 Chrome 10+*/
	background: -o-linear-gradient(top, #FFFFFF, #D1E1F1); /*Opera 11.10+*/
	
}
.leftMenu {
/*
	min-width:220px;
    
   width:268px;
   */
	margin:3px auto 0 auto;
	
}
.menu {
	border: #bdd7f2 1px solid;
	/*
	border-top: #0080c4 4px solid;
	border-bottom: #0080c4 4px solid;
	*/
	background: #f4f9ff url(images/leftdhbg.jpg) repeat-y right;
	margin-left: 10px;
}
.menu .ListTitle {
	border-bottom: 1px #98c9ee solid;
	display: block;
	text-align: center;
	/*position: relative;*/
	height: 38px;
	line-height: 38px;
	cursor: pointer;
	/*+min-width:220px;*/

	+width:100%;
}
.ListTitlePanel {
	position: relative;
}
.leftbgbt {
	position: absolute;
	background: url(images/leftbgbt.jpg) no-repeat;
	width: 11px;
	height: 52px;
	left: -11px;
	top: -4px;
}
/*.leftbgbt {
	float:left;
	background: url(images/leftbgbt.jpg) no-repeat;
	width: 11px;
	height: 52px;
	left: 0px;
	top: 0px;
	zoom:1;
	z-index:200px;
}
*/.leftbgbt2 {
	position: absolute;
	background: url(images/leftbgbt2.jpg) no-repeat;
	width: 11px;
	height: 48px;
	left: -11px;
	top: -1px;
}
.menuList {
	display: block;
	height: auto;
}
.menuList div {
	height: 28px;
	line-height: 24px;
	border-bottom: 1px #98c9ee dotted;
}
.menuList div a {
	display: block;
	background: #fff;
	line-height: 28px;
	height: 28px;
	text-align: center;
	color: #185697;
	text-decoration: none;
}
.menuList div a:hover {
	color: #f30;
	background: #0080c4;
	color: #fff;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	var menuParent = $('.menu > .ListTitlePanel > div');//获取menu下的父层的DIV
	var menuList = $('.menuList');
	$('.menu > .menuParent > .ListTitlePanel > .ListTitle').each(function(i) {//获取列表的大标题并遍历
		$(this).click(function(){
			if($(menuList[i]).css('display') == 'none'){
				$(menuList[i]).slideDown(300);
			}
			else{
				$(menuList[i]).slideUp(300);
			}
		});
	});
});
</script>
</head>
<body>

<div class="leftMenu">
	<div class="menu">
		<div class="menuParent">
			<div class="ListTitlePanel">
				<div class="ListTitle">
					<strong>菜单</strong>
					<div class="leftbgbt"> </div>
				</div>
			</div>
			<div class="menuList">
				<div><a href="<%=path%>/goUsersubinfoManage" target="home">美发师管理</a></div>
				<div><a href="<%=path%>/goStoreinfoManage" target="home">商户管理</a></div>
				<div><a href="<%=path%>/goUserinfoList" target="home">用户管理</a> </div>
				<div><a href="<%=path%>/goUserMyhairstyleList" target="home">发型管理</a> </div>
				<div><a href="<%=path%>/goOrderinfoList" target="home">订单管理</a> </div>
				<div><a href="<%=path%>/goOrderExceptionInfoManage" target="home">异常处理</a> </div>
				<div><a href="<%=path%>/goOrderCount" target="home">订单统计</a> </div>
				<div><a href="<%=path%>/goUserTradeLogManage" target="home">支付管理</a> </div>
				<div><a href="<%=path%>/goUserEvaluateManage" target="home">评价管理</a> </div>
				<div><a href="<%=path%>/goBaseConfig" target="home">配置管理</a> </div>
				<div><a href="<%=path%>/goBaseSuggestion" target="home">意见反馈</a> </div>
				<div><a href="<%=path%>/goCouponSystem" target="home">优惠券系统</a> </div>
				<div><a href="<%=path%>/goHomepageChange" target="home">首页图片更换</a> </div>
			</div>
		</div>
		
		
	</div>
</div>
<div style="text-align:center;">

</div>



</body>
</html>