//选择省份改变事件
function provinceOnchange() {
	var url = projectPath + "/getCityByProvinceId/" + $("#txtProvinceid").val();
	$("#provinceid").val($("#txtProvinceid").val());
	
	$.post(url, function(data) {
		$("#txtCityid").empty();
		for(var i = 0 ;i < data.baseCityList.length; i++){
			$("#txtCityid").append("<option value='"+data.baseCityList[i].cityid+"'>"+data.baseCityList[i].cityname+"</option>");
		}
	}, "json");
}
//选择城市改变事件
function cityOnchange(){
	$("#cityid").val($("#txtCityid").val());
}
	
//保存商圈
function doBaseBusinessareaSave(){
	var cityid =  $("#txtCityid").val();
	if(cityid <= 0){
		alert("请选择城市!");
		return;
	}
	
	var areaname =  $("#txtBaseBusinessarea").val();
	if(areaname == ""){
		alert("请输入商圈名称!");
		$("#txtBaseBusinessarea").focus();
		return;
	}
	
	
	var url = projectPath + "/addBaseBusinessarea";
	$.post(url, {
		"cityid" : cityid,
		"areaname" : areaname
	}, function(data) {
		if (data.info == true) {
			alert("添加成功!");
			$("#txtCityid").val("");
		} else {
			alert(data.msg);
		}
	}, "json");
	
}

//保存美发师爱好
function doHobbySave(){
	
	var configcode = "hobby";
	
	var configvalue =  $("#txtHobby").val();
	if(configvalue == ""){
		alert("请输入美发师爱好!");
		$("#txtHobby").focus();
		return;
	}
	
	var url = projectPath + "/addBaseConfig";
	$.post(url, {
		"configcode" : configcode,
		"configvalue" : configvalue
	}, function(data) {
		if (data.info == true) {
			alert("添加成功!");
			$("#txtHobby").val("");
		} else {
			alert(data.msg);
		}
	}, "json");
}

//保存美发师擅长
function doHairstyleSave(){
	var configcode = "hairstyle";
	
	var configvalue =  $("#txtHairstyle").val();
	if(configvalue == ""){
		alert("请输入美发师擅长!");
		$("#txtHairstyle").focus();
		return;
	}
	
	var url = projectPath + "/addBaseConfig";
	$.post(url, {
		"configcode" : configcode,
		"configvalue" : configvalue
	}, function(data) {
		if (data.info == true) {
			alert("添加成功!");
			$("#txtHairstyle").val("");
		} else {
			alert(data.msg);
		}
	}, "json");
}

//服务定价管理
function doServerPricingManage(){
	location.href= projectPath + "/goServerPricingManage";
}

//服务时间管理
function doServerTimeManage(){
	location.href= projectPath + "/goServerTimeManage";
}

//分成比例
function doAllocate(){
	
	
	var $txtUserAllocate = $("#txtUserAllocate");
	var $txtStoreAllocate = $("#txtStoreAllocate");
	
	var userAllocate =  $txtUserAllocate.val();
	var storeAllocate =  $txtStoreAllocate.val();
	
	if($.trim(userAllocate) == ""){
		alert("请输入美发师分成比例！");
		$txtUserAllocate.focus();
		return;
	}
	if($.trim(storeAllocate) == ""){
		alert("请输入美发店分成比例！");
		$txtStoreAllocate.focus();
		return;
	}
	
	if(!$.isNumeric(userAllocate)){
		alert("请输入数字！");
		$txtUserAllocate.focus();
		return;
	}
	if(!$.isNumeric(storeAllocate)){
		alert("请输入数字！");
		$txtStoreAllocate.focus();
		return;
	}
	if(userAllocate<0 || userAllocate > 100){
		alert("请输入0~100的数值！");
		$txtUserAllocate.focus();
		return;
	}
	
	if(storeAllocate<0 || storeAllocate > 100){
		alert("请输入0~100的数值！");
		$txtStoreAllocate.focus();
		return;
	}
	
	var url = projectPath + "/updateBaseConfig";
	$.post(url, {
		"configcode1" : "UserAllocate",
		"configvalue1" : userAllocate,
		"configcode2" : "StoreAllocate",
		"configvalue2" : storeAllocate,
	}, function(data) {
		if (data.info == true) {
			alert("更新成功!");
		} else {
			alert(data.msg);
		}
	}, "json");
}

function doAccoutDelayDay(){
	var $txtAccout_delay_day = $("#txtAccout_delay_day");
	
	var accout_delay_day =  $txtAccout_delay_day.val();
	
	if($.trim(accout_delay_day) == ""){
		alert("请输入服务结束之后分成到账时间！");
		$txtAccout_delay_day.focus();
		return;
	}
	
	
	if(!$.isNumeric(accout_delay_day)){
		alert("请输入数字！");
		$txtAccout_delay_day.focus();
		return;
	}
	
	if(accout_delay_day<0 || accout_delay_day > 365){
		alert("请输入0~365的数值！");
		$txtAccout_delay_day.focus();
		return;
	}
	
	var url = projectPath + "/updateBaseConfigOne";
	$.post(url, {
		"configcode" : "accout_delay_day",
		"configvalue" : accout_delay_day
	}, function(data) {
		if (data.info == true) {
			alert("更新成功!");
		} else {
			alert(data.msg);
		}
	}, "json");
}

function doDeposit(){
	var $txtdeposit = $("#txtdeposit");
	
	var deposit =  $txtdeposit.val();
	
	if($.trim(deposit) == ""){
		alert("请输入美发师和商户的预留押金！");
		$txtdeposit.focus();
		return;
	}
	
	
	if(!$.isNumeric(deposit)){
		alert("请输入数字！");
		$txtdeposit.focus();
		return;
	}
	
	if(deposit<0){
		alert("请输入0以上的数值！");
		$txtdeposit.focus();
		return;
	}
	
	var url = projectPath + "/updateBaseConfigOne";
	$.post(url, {
		"configcode" : "deposit",
		"configvalue" : deposit
	}, function(data) {
		if (data.info == true) {
			alert("更新成功!");
		} else {
			alert(data.msg);
		}
	}, "json");
}

function doServicePhone(){
	var $txtservice_phone = $("#txtservice_phone");
	
	var service_phone =  $txtservice_phone.val();
	
	if($.trim(service_phone) == ""){
		alert("请输入客服电话！");
		$txtservice_phone.focus();
		return;
	}

	var url = projectPath + "/updateBaseConfigOne";
	$.post(url, {
		"configcode" : "service_phone",
		"configvalue" : service_phone
	}, function(data) {
		if (data.info == true) {
			alert("更新成功!");
		} else {
			alert(data.msg);
		}
	}, "json");
}


function doAccoutOpenDay(){
	var $txtaccout_open_day1 = $("#txtaccout_open_day1");
	var $txtaccout_open_day2 = $("#txtaccout_open_day2");
	
	var accout_open_day1 =  $txtaccout_open_day1.val();
	var accout_open_day2 =  $txtaccout_open_day2.val();
	
	if($.trim(accout_open_day1) == ""){
		alert("请输入美发师商户提现日期！");
		$txtaccout_open_day1.focus();
		return;
	}
	if($.trim(accout_open_day2) == ""){
		alert("请输入美发师商户提现日期！");
		$txtaccout_open_day2.focus();
		return;
	}

	if(!$.isNumeric(accout_open_day1)){
		alert("请输入数字！");
		$txtaccout_open_day1.focus();
		return;
	}
	
	if(!$.isNumeric(accout_open_day2)){
		alert("请输入数字！");
		$txtaccout_open_day2.focus();
		return;
	}
	
	if(accout_open_day1<1 || accout_open_day1 > 31){
		alert("请输入1~31的数值！");
		$txtaccout_open_day1.focus();
		return;
	}
	
	if(accout_open_day2<1 || accout_open_day2 > 31){
		alert("请输入1~31的数值！");
		$txtaccout_open_day2.focus();
		return;
	}

	var url = projectPath + "/updateBaseConfigOne";
	$.post(url, {
		"configcode" : "accout_open_day",
		"configvalue" : accout_open_day1+","+accout_open_day2
	}, function(data) {
		if (data.info == true) {
			alert("更新成功!");
		} else {
			alert(data.msg);
		}
	}, "json");
}