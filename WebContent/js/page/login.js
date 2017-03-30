$(function() {
	// 确定
	$("#btnOK").click(
		function() {
			var username = $("#username").val();
			var password = $("#password").val();

			if (username == "") {
				alert("请输入手机号！");
				$("#username").focus();
				return;
			}

			if (password == "") {
				alert("请输入密码！");
				$("#password").focus();
				return;
			}
			
			var loginurl = projectPath + "/UserinfoLogin";
			$.post(loginurl, {
				"username" : username,
				"password" : password
			}, function(data) {
				if (data.info == true) {
					window.location.href = projectPath + "/Main";
				} else {
					alert(data.message);
					$("#username").val("");
					$("#password").val("");
				}
			}, "json");
		});

});
