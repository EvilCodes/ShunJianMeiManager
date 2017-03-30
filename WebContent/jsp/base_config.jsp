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
<title>美发师信息</title>
<link rel="shortcut icon" href="<%=path%>/css/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/base_config.js?<%=date %>"></script>
</head>
<body>
    <div class="box main" style="height:900px">
    	<div class="content">
        	<div class="prop" style="left:360px;height: 750px;">
        		<table width="100%">
        			<tr>
        				<td width="50%">当前位置：配置管理</td>
        				
        			</tr>
        		</table>
				<hr/>
				<form name="baseconfigFrom" method="POST" enctype="multipart/form-data">
				<!-- 商圈管理 -->
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="25%">商圈管理</td>
					</tr>
					<tr>
						<td align="left">选择省份:</td>
						<td align="left">
							<select id="txtProvinceid" name="txtProvinceid" style="width:100px;height:22px" onchange="provinceOnchange()">
								<c:forEach var="baseProvince" items="${baseProvinceList}">
									<option value="${baseProvince.provinceid}">${baseProvince.provincename}</option>
								</c:forEach>
							</select>
						</td>
						<td align="left">选择城市:</td>
						<td align="left">
							<select id="txtCityid" name="txtCityid" style="width:100px;height:22px" onchange="cityOnchange()">
								<c:forEach var="baseCity" items="${baseCityList}">
									<option value="${baseCity.cityid}">${baseCity.cityname}</option>
								</c:forEach>
								<option value="0">请选择</option>
							</select>
							<input id="provinceid" name="provinceid" type="hidden"/>
							<input id="cityid" name="cityid" type="hidden" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
						<c:forEach var="baseBusinessarea" items="${baseBusinessareaList}">
							${baseBusinessarea.areaname}
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<input id="txtBaseBusinessarea" name="txtBaseBusinessarea" maxlength="100" type="text" />
							<input id="saveButton" name="saveButton" type="button" onclick="doBaseBusinessareaSave()" value="添加" />
						</td>
					</tr>
				</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left">美发师爱好</td>
					</tr>
					<tr>
						<td align="left">
						<c:forEach var="hobby" items="${hobbyList}">
							${hobby.configvalue}
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<input id="txtHobby" name="txtHobby" maxlength="50" type="text" />
							<input id="saveButton" name="saveButton" type="button" onclick="doHobbySave()" value="添加" />
						</td>
					</tr>
				</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left">美发师擅长</td>
					</tr>
					<tr>
						<td align="left">
						<c:forEach var="hairstyle" items="${hairstyleList}">
							${hairstyle.configvalue}
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<input id="txtHairstyle" name="txtHairstyle" maxlength="50" type="text" />
							<input id="saveButton" name="saveButton" type="button" onclick="doHairstyleSave()" value="添加" />
						</td>
					</tr>
				</table>
				<hr/>
				<br/>
				<input id="serverPricingManageButton" name="serverPricingManageButton" type="button" onclick="doServerPricingManage()" value="服务定价管理" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="serverTimeManageButton" name="serverTimeManageButton" type="button" onclick="doServerTimeManage()" value="服务时间设置" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- <input id="serverTimeManageButton" name="serverTimeManageButton" type="button" onclick="doServerTimeManage()" value="物料价格设定" /> -->
				
				<br/><br/>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" colspan="2">分成比例设定（如没有特殊情况，美发师和商户使用此处设定的分成比例）</td>
					</tr>
					<tr>
						<td align="left" style="width:120px">
						美发师分成比例:
						</td>
						<td align="left">
						<input id="txtUserAllocate" name="txtUserAllocate" value="${UserAllocate}" maxlength="3" type="text" />
						</td>
					</tr>
					<tr>
						<td align="left">
						美发店分成比例:
						</td>
						<td align="left">
						<input id="txtStoreAllocate" name="txtStoreAllocate" value="${StoreAllocate}" maxlength="3" type="text" />
						&nbsp;&nbsp;
						<input type="button"  onclick="doAllocate()" value="确定" />
						</td>
					</tr>
				</table>
				
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" style="width:220px">
						服务结束之后分成到账时间:
						</td>
						<td align="left">
						<input id="txtAccout_delay_day" name="txtAccout_delay_day" value="${accout_delay_day}" maxlength="3" type="text" />
						
						&nbsp;&nbsp;
						<input type="button"  onclick="doAccoutDelayDay()" value="保存" />
						</td>
					</tr>
					
					
					<tr>
						<td align="left" >
						美发师商户提现日期:
						</td>
						<td align="left">
						<input id="txtaccout_open_day1" name="txtaccout_open_day1" value="${accout_open_day1}" maxlength="2" type="text" />
						&nbsp;
						<input id="txtaccout_open_day2" name="txtaccout_open_day2" value="${accout_open_day2}" maxlength="2" type="text" />
						&nbsp;&nbsp;
						<input type="button"  onclick="doAccoutOpenDay()" value="保存" />
						</td>
					</tr>
					
				</table>
				
				
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" style="width:220px">
						美发师和商户的预留押金:
						</td>
						<td align="left">
						<input id="txtdeposit" name="txtdeposit" value="${deposit}" maxlength="9" type="text" />
						
						&nbsp;&nbsp;
						<input type="button"  onclick="doDeposit()" value="保存" />
						</td>
					</tr>
				</table>
				
				
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" style="width:120px">
						客服电话:
						</td>
						<td align="left">
						<input id="txtservice_phone" name="txtservice_phone" value="${service_phone}" maxlength="20" type="text" />
						
						&nbsp;&nbsp;
						<input type="button" onclick="doServicePhone()" value="保存" />
						</td>
					</tr>
				</table>
				
				
				</form>
            </div>
        </div>
    </div>
    
</body>
</html>