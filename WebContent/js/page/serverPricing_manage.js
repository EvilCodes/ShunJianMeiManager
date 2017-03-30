
function getLevelid(){
	$("#levelid").val($("#txtLevelid").val());
	var levelid = $.trim($("#levelid").val());
	var registerUrl = projectPath + "/getServerPricingInfo";
	$.post(registerUrl,{
		"levelid" : levelid
		},
		function(data){
			$("#xjc").val(data.xjc);
			$("#xc").val(data.xc);
			$("#tf").val(data.tf);
			$("#rf").val(data.rf);
			$("#hl").val(data.hl);
		}, "json");
}

function doSaveServerPricing(){
	var levelid = $.trim($("#levelid").val());
	var xjc = $.trim($("#xjc").val());
	var xc = $.trim($("#xc").val());
	var tf = $.trim($("#tf").val());
	var rf = $.trim($("#rf").val());
	var hl = $.trim($("#hl").val());
	
	if("" == xjc){
		alert("洗剪吹价格为空！");
		$("#xjc").focus();
		return;
	}else{
		if(isNaN(xjc)){
			alert("请输入合法的数字！");
			$("#xjc").focus();
			return;
		}
	}
	
	if("" == xc){
		alert("洗吹价格不能为空！");
		$("#xc").focus();
		return;
	}else{
		if(isNaN(xc)){
			alert("请输入合法的数字！");
			$("#xc").focus();
			return;
		}
	}
	
	if("" == tf){
		alert("烫发价格不能为空！");
		$("#tf").focus();
		return;
	}else{
		if(isNaN(tf)){
			alert("请输入合法的数字！");
			$("#tf").focus();
			return;
		}
	}
	
	if("" == rf){
		alert("染发价格不能为空！");
		$("#rf").focus();
		return;
	}else{
		if(isNaN(rf)){
			alert("请输入合法的数字！");
			$("#rf").focus();
			return;
		}
	}
	
	if("" == hl){
		alert("护理价格不能为空！");
		$("#hl").focus();
		return;
	}else{
		if(isNaN(hl)){
			alert("请输入合法的数字！");
			$("#hl").focus();
			return;
		}
	}
	
	var registerUrl = projectPath + "/doSaveServerPricing";
	$.post(registerUrl,{
		"levelid" : levelid,
		"xjc" : xjc,
		"xc" : xc,
		"tf" : tf,
		"rf" : rf,
		"hl" : hl
		},
		function(data){
			alert(data.msg);
		}, "json");
}