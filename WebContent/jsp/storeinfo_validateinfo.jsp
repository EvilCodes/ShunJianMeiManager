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
<title>美发店信息</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/style.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset2.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/storeinfo_validateinfo.js"></script>
</head>
<body>
	<input id="hidmsg" type="hidden" value="${message }">
	<input id="hidstate" type="hidden" value="${storeinfo.state }">
    <div class="box main" style="height:1550px">
    	<div class="content">
        	<div class="prop_store" style="left:360px;height: 1350px;">
        	
        		<form name="validateinfoFrom" method="POST" enctype="multipart/form-data" action="<%=path %>/StoreinfoValidateInfo">
        		
				<input id="storeid" name="storeid" type="hidden" value="${storeinfo.storeid }">
        		
        		基本信息
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">商户名称:</td>
						<td align="left" width="75%"><input id="storeName" name="storeName" type="text" value="${storeinfo.name}" /></td>
					</tr>
					<tr>
						<td align="right">所在地:</td>
						<td align="left">
							<input id="provinceid" name="provinceid" type="hidden" value="${storeinfo.provinceid}" />
							<input id="cityid" name="cityid" type="hidden" value="${storeinfo.cityid}" />
							<input id="districtid" name="districtid" type="hidden" value="${storeinfo.districtid}" />
							<select id="txtProvinceid" name="txtProvinceid" style="width:100px;height:22px" onchange="getBaseCitys()">
								<c:forEach var="baseProvince" items="${baseProvinceList}">
									<option value="${baseProvince.provinceid}">${baseProvince.provincename}</option>
								</c:forEach>
							</select>
							<select id="txtCityid" name="txtCityid" style="width:100px;height:22px" onchange="getBaseDistricts();getBaseBusinessareas();">
								<c:forEach var="baseCity" items="${baseCityList}">
									<option value="${baseCity.cityid}">${baseCity.cityname}</option>
								</c:forEach>
							</select>
							<select id="txtDistrictid" name="txtDistrictid" style="width:100px;height:22px">
								<c:forEach var="baseDistrict" items="${baseDistrictList}">
									<option value="${baseDistrict.districtid}">${baseDistrict.districtname}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">地址:</td>
						<td align="left"><input id="address" name="address" type="text" value="${storeinfo.address}" /></td>
					</tr>
					<tr>
						<td align="right">商户电话:</td>
						<td align="left"><input id="tel" name="tel" type="text" value="${storeinfo.tel}" /></td>
					</tr>
					<tr>
						<td align="right">停车位:</td>
						<td align="left"><input id="carnumber" name="carnumber" type="text" value="${storeinfo.carnumber}" /></td>
					</tr>
					<tr>
						<td align="right">营业时间:</td>
						<td align="left">
							<input id="businesshoursStart" name="businesshoursStart" type="hidden" value="${businesshoursStart}" />
							<input id="businesshoursEnd" name="businesshoursEnd" type="hidden" value="${businesshoursEnd}" />
							<select id="selBusinesshoursStart" name="selBusinesshoursStart">
								<option value="09:00">09:00</option>
								<option value="10:00">10:00</option>
								<option value="11:00">11:00</option>
								<option value="12:00">12:00</option>
								<option value="13:00">13:00</option>
								<option value="14:00">14:00</option>
								<option value="15:00">15:00</option>
								<option value="16:00">16:00</option>
								<option value="17:00">17:00</option>
								<option value="18:00">18:00</option>
								<option value="19:00">19:00</option>
								<option value="20:00">20:00</option>
								<option value="21:00">21:00</option>
								<option value="22:00">22:00</option>
							</select>---
							<select id="selBusinesshoursEnd" name="selBusinesshoursEnd">
								<option value="09:00">09:00</option>
								<option value="10:00">10:00</option>
								<option value="11:00">11:00</option>
								<option value="12:00">12:00</option>
								<option value="13:00">13:00</option>
								<option value="14:00">14:00</option>
								<option value="15:00">15:00</option>
								<option value="16:00">16:00</option>
								<option value="17:00">17:00</option>
								<option value="18:00">18:00</option>
								<option value="19:00">19:00</option>
								<option value="20:00">20:00</option>
								<option value="21:00">21:00</option>
								<option value="22:00">22:00</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">坐落商圈:</td>
						<td align="left">
							<input id="areaid" name="areaid" type="hidden" value="${storeinfo.areaid}" />
							<select id="txtAreaid" name="txtAreaid" style="width:100px;height:22px">
								<c:forEach var="baseBusinessarea" items="${baseBusinessareaList}">
									<option value="${baseBusinessarea.areaid}">${baseBusinessarea.areaname}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">经营方式:</td>
						<td align="left">
							<input id="empiricalmode" name="empiricalmode" type="hidden" value="${storeinfo.empiricalmode}" />
							<select id="txtEmpiricalmode" name="txtEmpiricalmode">
								<option value="1">连锁</option>
								<option value="2">单店</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">经度:</td>
						<td align="left">
							<input id="longitude" name="longitude" type="text" value="${storeinfo.longitude}" />&nbsp;<font style="font-size:12px">(例：104.08296)</font>
						</td>
					</tr>
					<tr>
						<td align="right">纬度:</td>
						<td align="left">
							<input id="latitude" name="latitude" type="text" value="${storeinfo.latitude}" />&nbsp;<font style="font-size:12px">(例：38.65777)</font>
						</td>
					</tr>
					<tr>
						<td align="right">客户简介:</td>
						<td align="left">
							<textarea id="intro" name="intro" style="width:370px;height:80px">${storeinfo.intro}</textarea>
						</td>
					</tr>
				</table>
				
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">开户姓名:</td>
						<td align="left" width="75%"><input id="ownername" name="ownername" type="text" maxlength="50" value="${storeinfo.ownername}" /></td>
					</tr>
					<tr>
						<td align="right" width="25%">开户行:</td>
						<td align="left" width="75%"><input id="bank" name="bank" type="text" maxlength="50" value="${storeinfo.bank}" /></td>
					</tr>
					<tr>
						<td align="right" width="25%">卡号:</td>
						<td align="left" width="75%"><input id="cardnumber" name="cardnumber" type="text" value="${storeinfo.cardnumber}" /></td>
					</tr>					
					<tr>
						<td align="right" width="25%">接口人姓名:</td>
						<td align="left" width="75%"><input id="bossname" name="bossname" type="text" value="${storeinfo.bossname}" /></td>
					</tr>
					<tr>
						<td align="right">接口人手机号:</td>
						<td align="left"><input id="bossmobile" name="bossmobile" type="text" value="${storeinfo.bossmobile}" /></td>
					</tr>
					<tr>
						<td align="right">店主姓名:</td>
						<td align="left"><input id="storemanagername" name="storemanagername" type="text" value="${storeinfo.storemanagername}" /></td>
					</tr>
					<tr>
						<td align="right">店主手机号:</td>
						<td align="left"><input id="storemanagermobile" name="storemanagermobile" type="text" value="${storeinfo.storemanagermobile}" /></td>
					</tr>
				</table>
				
				<p>商户经营执照</p>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="60%" height="100px">
							<img id="imgFileName1" src="${imgIpHost}${fileName1}" style="width:100px;height:50px" />
							<a id="upload_file1" style="cursor:hand" class="button pink">文件</a>
							<input id="fileName1" name="fileName1" type="hidden" value="${fileName1}" />
						</td>
					</tr>
				</table>
				
				<p>商户正面照</p>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="60%" height="100px">
							<img id="imgFileName2" src="${imgIpHost}${fileName2}" style="width:100px;height:50px" />
							<a id="upload_file2" style="cursor:hand" class="button pink">文件</a>
							<input id="fileName2" name="fileName2" type="hidden" value="${fileName2}" />
						</td>
					</tr>
				</table>
				
				<p>店内照</p>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="60%" height="100px">
							<img id="imgFileName3" src="${imgIpHost}${fileName3}" style="width:100px;height:50px" />
							<a id="upload_file3" style="cursor:hand" class="button pink">文件</a>
							<input id="fileName3" name="fileName3" type="hidden" value="${fileName3}" />
						</td>
					</tr>
				</table>
				
				状态信息
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">状态:</td>
						<td align="left" width="75%">
							<select id="txtState" name="txtState">
								<option value="0">未填信息</option>
								<option value="1">审核中</option>
								<option value="2">审核通过</option>
								<option value="3">审核失败</option>
							</select>
							<input id="state" name="state" type="hidden" value="${storeinfo.state}" />
						</td>
					</tr>
					<tr>
						<td align="right" width="25%">分成:</td>
						<td align="left" width="75%">
							<input id="allocation" name="allocation" type="text" value="${storeinfo.allocation}" />
						</td>
					</tr>
				</table>
				</form>				
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td colspan="2" align="center">
							<div class="loginBtn">
								<input id="btnSave" type="button" value="保存" />
							</div>
						</td>
					</tr>
				</table>
            </div>
        </div>
    </div>
    
</body>
</html>