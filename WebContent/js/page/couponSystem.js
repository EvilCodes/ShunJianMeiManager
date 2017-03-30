//分享优惠券类型改变事件
function shareCouponOnchange(){
	
	//取得分享分类
	var txtShareCoupon = $("#txtShareCoupon").val();
	
	//获取最大最小值
	var editurl = projectPath + "/getShareCouponValue";
	$.post(editurl, {
		"txtShareCoupon" : txtShareCoupon
	}, function(data) {
		$("#ShareCouponMin").val(data.ShareCouponMin);
		$("#ShareCouponMax").val(data.ShareCouponMax);
	}, "json");
	
}


//修改分享优惠券的区间
function updateShareCoupon(){
	//取得分享分类
	var txtShareCoupon = $("#txtShareCoupon").val();
	//分享优惠券
	var ShareCouponMin = $("#ShareCouponMin").val();
	var ShareCouponMax = $("#ShareCouponMax").val();
	
	var editurl = projectPath + "/updateShareCoupon";
	$.post(editurl, {
		"txtShareCoupon" : txtShareCoupon,
		"ShareCouponMin" : ShareCouponMin,
		"ShareCouponMax" : ShareCouponMax
	}, function(data) {
		if (data.editInfo == true) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}

//赠送优惠券价格
function addUserCoupon(){
	
	//手机
	var mobile = $("#mobile").val();
	//价格
	var amount = $("#amount").val();
	var txtUserCoupon = $("#txtUserCoupon").val();
	
	var editurl = projectPath + "/addUserCoupon";
	$.post(editurl, {
		"txtUserCoupon" : txtUserCoupon,
		"mobile" : mobile,
		"amount" : amount
	}, function(data) {
		if (data.editInfo == true) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}

//注册优惠券类型改变事件
function regCouponOnchange(){
	
	//取得注册分类
	var txtRegCoupon = $("#txtRegCoupon").val();
	
	//获取最大最小值
	var editurl = projectPath + "/getRegCouponValue";
	$.post(editurl, {
		"txtRegCoupon" : txtRegCoupon
	}, function(data) {
		$("#RegCouponMin").val(data.RegCouponMin);
		$("#RegCouponMax").val(data.RegCouponMax);
	}, "json");
	
}
//修改注册优惠券的区间
function updateRegCoupon(){
	//取得注册分类
	var txtRegCoupon = $("#txtRegCoupon").val();
	//注册优惠券
	var RegCouponMin = $("#RegCouponMin").val();
	var RegCouponMax = $("#RegCouponMax").val();
	
	var editurl = projectPath + "/updateRegCoupon";
	$.post(editurl, {
		"txtRegCoupon" : txtRegCoupon,
		"RegCouponMin" : RegCouponMin,
		"RegCouponMax" : RegCouponMax
	}, function(data) {
		if (data.editInfo == true) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}




//修改分享优惠券有效天数
function updateShareCouponDays(){
	//取得分享分类
	var ShareCouponDays = $("#txtShareCouponDays").val();
	
	var editurl = projectPath + "/updateShareCouponDays";
	$.post(editurl, {
		"ShareCouponDays" : ShareCouponDays
	}, function(data) {
		if (data.editInfo == true) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}


//修改注册优惠券有效天数
function updateRegCouponDays(){
	//取得分享分类
	var RegCouponDays = $("#txtRegCouponDays").val();
	
	var editurl = projectPath + "/updateRegCouponDays";
	$.post(editurl, {
		"RegCouponDays" : RegCouponDays
	}, function(data) {
		if (data.editInfo == true) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}


function addUserCoupon(){
	
	//手机
	var mobile = $("#mobile").val();
	//价格
	var amount = $("#amount").val();
	var txtUserCoupon = $("#txtUserCoupon").val();
	
	var editurl = projectPath + "/addUserCoupon";
	$.post(editurl, {
		"txtUserCoupon" : txtUserCoupon,
		"mobile" : mobile,
		"amount" : amount
	}, function(data) {
		if (data.editInfo == true) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}


function addRegCoupon(){
	
	var regCouponHl = $("#regCouponHl").val();
	var regCouponRf = $("#regCouponRf").val();
	var regCouponTf = $("#regCouponTf").val();
	var regCouponXc = $("#regCouponXc").val()
	var regCouponXjc = $("#regCouponXjc").val();
	var regCouponTc = $("#regCouponTc").val();
	
	if(regCouponHl == ""){
		alert("请输入护理劵!");
		$("#regCouponHl").focus();
		return;
	}
	if(regCouponRf == ""){
		alert("请输入染发劵!");
		$("#regCouponRf").focus();
		return;
	}
	if(regCouponTf == ""){
		alert("请输入烫发劵!");
		$("#regCouponTf").focus();
		return;
	}
	if(regCouponXc == ""){
		alert("请输入造型劵!");
		$("#regCouponXc").focus();
		return;
	}
	if(regCouponXjc == ""){
		alert("请输入设计剪发劵!");
		$("#regCouponXjc").focus();
		return;
	}
	if(regCouponXjc == ""){
		alert("请输入套餐劵!");
		$("#regCouponTc").focus();
		return;
	}
	
	
	var editurl = projectPath + "/updateRegCouponCont";
	$.post(editurl, {
		"regCouponHl" : regCouponHl,
		"regCouponRf" : regCouponRf,
		"regCouponTf" : regCouponTf,
		"regCouponXc" : regCouponXc,
		"regCouponXjc": regCouponXjc,
		"regCouponTc": regCouponTc
	}, function(data) {
		if (data.editInfo == true) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}
