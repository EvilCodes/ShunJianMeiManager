//查看审核信息
function showAuditInfo(){
	location.href= projectPath + "/goUsersubinfo_verification?userId="+$("#userid").val();
}

//查看业务信息
function showBusinessInfo(){
	location.href= projectPath + "/goUsersubinfo_information?userId="+$("#userid").val();
}

$(function(){
	
	$("#txtLevel").val($("#hidLevel").val());
	$("#txtCheckstate").val($("#hidCheckstate").val());
	
});


function addLevelAndCheckstate(){
	var hidUserid = $("#hidUserid").val();
	var txtLevel = $("#txtLevel").val();
	var txtCheckstate = $("#txtCheckstate").val();
	
	//分成比例
	var txtAllocation = $("#txtAllocation").val();
	//是否使用默认分成
	var isType = 0;
	//是否使用默认分成
	if($('#defaultAllocation').is(':checked')){
		isType = 1;
	}
	
	var url = projectPath + "/addLevelAndCheckstate";
	$.post(url, {
		"userid" : hidUserid,
		"levelid" : txtLevel,
		"checkstate" : txtCheckstate,
		"allocation" : txtAllocation,
		"isType" : isType
		
	}, function(data) {
		if (data.info == true) {
			alert("设置成功!");
		} else {
			alert(data.msg);
		}
	}, "json");
	
}
