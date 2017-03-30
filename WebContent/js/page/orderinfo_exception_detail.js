$(function(){

	
	$("#btnOK").click(function(){
		
		var txtOrderId = $.trim($("#txtOrderId").val());
		var txtStoreId = $.trim($("#txtStoreId").val());
		var txtUserId = $.trim($("#txtUserId").val());
		var txtCustomerId = $.trim($("#txtCustomerId").val());
		
		var txtStoreCompensate = $.trim($("#txtStoreCompensate").val());
		var txtStoreGetCompensate = $.trim($("#txtStoreGetCompensate").val());
		
		var txtUserCompensate = $.trim($("#txtUserCompensate").val());
		var txtUserGetCompensate = $.trim($("#txtUserGetCompensate").val());
		
		var txtCustomerCompensate = $.trim($("#txtCustomerCompensate").val());
		var txtCustomerGetCompensate = $.trim($("#txtCustomerGetCompensate").val());
	    
		if("" == txtStoreCompensate && "" == txtStoreGetCompensate){
			alert("请输入商户赔偿或商户获得赔偿！");
			$("#txtStoreCompensate").focus();
			return;
		}
		if("" != txtStoreCompensate && "" != txtStoreGetCompensate){
			alert("商户赔偿或商户获得赔偿只能输入一个！");
			$("#txtStoreCompensate").focus();
			return;
		}
		
		
		if("" == txtUserCompensate && "" == txtUserGetCompensate){
			alert("请输入美发师赔偿或美发师获得赔偿！");
			$("#txtStoreCompensate").focus();
			return;
		}
		if("" != txtUserCompensate && "" != txtUserGetCompensate){
			alert("美发师赔偿或美发师获得赔偿只能输入一个！");
			$("#txtStoreCompensate").focus();
			return;
		}
		
		if("" == txtCustomerCompensate && "" == txtCustomerGetCompensate){
			alert("请输入用户赔偿或用户获得赔偿！");
			$("#txtStoreCompensate").focus();
			return;
		}
		if("" != txtCustomerCompensate && "" != txtCustomerGetCompensate){
			alert("用户赔偿或用户获得赔偿只能输入一个！");
			$("#txtStoreCompensate").focus();
			return;
		}
		
		var addurl = projectPath + "/OrderExceptionCompensate";
		$.post(addurl,{
			"txtOrderId" : txtOrderId,
			"txtStoreId" : txtStoreId,
			"txtUserId" : txtUserId,
			"txtCustomerId" : txtCustomerId,
			"txtStoreCompensate" : txtStoreCompensate,
			"txtStoreGetCompensate" : txtStoreGetCompensate,
			"txtUserCompensate" : txtUserCompensate,
			"txtUserGetCompensate" : txtUserGetCompensate,
			"txtCustomerCompensate" : txtCustomerCompensate,
			"txtCustomerGetCompensate" : txtCustomerGetCompensate
			},
			function(data){
				if(data.info == true){
					alert(data.message);
					//location.href= projectPath + "/orderExceptionInfoManage";
				}else{
					alert(data.message);
				}
			}, "json");
		
	});
	
	
});
