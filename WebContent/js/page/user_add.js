
$(function(){
	
	//返回
	$("#btnBack").click(function(){
		window.history.back(-1);
		return;
	});
	
	$("#btnAdd").click(function(){
		
		var realName = $.trim($("#txtRealName").val());
		var userPwd = $.trim($("#txtUserPwd").val());
		var userPwdConfirm = $.trim($("#txtUserPwdConfirm").val());
		var telephone = $.trim($("#txtTelephone").val());
		var email = $.trim($("#txtEmail").val());

		var regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	    
		if("" == realName){
			$.messager.alert("操作提示", "请输入登录名！","error");
			$("#txtRealName").focus();
			return;
		}
				
		if("" == userPwd){
			$.messager.alert("操作提示", "请输入密码！","error");
			$("#txtRealName").focus();
			return;
		}
		
		if("" == userPwdConfirm){
			$.messager.alert("操作提示", "请输入确认密码！","error");
			$("#txtRealName").focus();
			return;
		}
		
		if(userPwd != userPwdConfirm){
			$.messager.alert("操作提示", "两次密码输入不一致！","error");
			$("#txtRealName").focus();
			return;
		}
		
		if("" == telephone){
			$.messager.alert("操作提示", "请输入电话！","error");
			$("#txtRealName").focus();
			return;
		}
		
		if("" == email){
			$.messager.alert("操作提示", "请输入邮箱！","error");
			return;
		}
		
	    if(!regEmail.test(email)){
			$.messager.alert("操作提示", "邮箱格式错误！","error");
			$("#txtRealName").focus();
			return;
		}
	    
		var addurl = projectPath + "/UserAdd";
		$.post(addurl,{
			"userId" : $("#txtUserId").val(),
			"realName" : $("#txtRealName").val(),
			"userPwd" : $("#txtUserPwd").val(),
			"telephone" : $("#txtTelephone").val(),
			"email" : $("#txtEmail").val(),
			"departmentId" : $("#txtDepartmentId").combobox('getValue'),
			"professionId" : $("#txtProfessionId").combobox('getValue'),
			"roleId" : $("#txtRoleId").combobox('getValue'),
			/*"departmentId" : $("#txtDepartmentId").val(),
			"professionId" : $("#txtProfessionId").val(),
			"roleId" : $("#txtRoleId").val(),*/
			"weixinNum" : $("#txtWeixinNum").val()
			},
			function(data){
				if(data.addInfo == true){
					$("#txtRealName").val("");
					$("#txtUserId").val("");
					$("#txtUserPwd").val("");
					$("#txtUserPwdConfirm").val("");
					$("#txtTelephone").val("");
					$("#txtEmail").val("");
					/*$("#txtDepartmentId").val("");
					$("#txtProfessionId").val("");
					$("#txtRoleId").val("");*/
					$("#txtWeixinNum").val("");
				}else{
					$("#txtRealName").focus();
				}
				$.messager.alert("操作提示", data.message, "error");
			}, "json");
		
	});
		
});
