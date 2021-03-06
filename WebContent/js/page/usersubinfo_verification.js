//查询城市列表
function getBaseCitys() {
	var url = projectPath + "/getBaseCitys/" + $("#txtProvinceid").val();
	$("#provinceid").val($("#txtProvinceid").val());
	
	$.post(url, function(data) {
		if (data.selInfo == true) {
			$("#txtCityid").empty();
			for(var i = 0 ;i < data.baseCityList.length; i++){
				$("#txtCityid").append("<option value='"+data.baseCityList[i].cityid+"'>"+data.baseCityList[i].cityname+"</option>");
			}
		}
	}, "json");
}

function getCityid(){
	$("#cityid").val($("#txtCityid").val());
}

function doSave(){
	$("#buttonFlag").val("0");//保存
	document.validateinfoFrom.submit();
}

function doNext(){
	var truename = $.trim($("#truename").val());
	var email = $.trim($("#email").val());
	var address = $.trim($("#address").val());
	var household = $.trim($("#household").val());
	
	var contact = $.trim($("#contact").val());
	var relationship = $.trim($("#relationship").val());
	var contactmobile = $.trim($("#contactmobile").val());
	var idcard = $.trim($("#idcard").val());
	
	if("" != email){
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!myreg.test(email)){
			alert('提示:请输入有效的常用邮箱！');
			$("#email").focus();
			return;        
		}
	}
	
	if("" != contactmobile){
		var telCheck = /^1[3|4|5|8][0-9]\d{8}$|^(0\d{2,3}-?|\(0\d{2,3}\))?[1-9]\d{4,7}(-\d{1,8})?$/;
		if(!(telCheck.test(contactmobile))){ 
			alert("紧急联系人电话格式不正确！");
			$("#contactmobile").focus(); 
	        return; 
	    } 
	}
	
	if("" != idcard){
		var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
	    if(reg.test(idcard) === false){
	    	alert("身份证格式不正确！");
			$("#idcard").focus();
			return;
	    }
	}
	
	$("#provinceid").val($("#txtProvinceid").val());
	$("#cityid").val($("#txtCityid").val());
	
	$("#buttonFlag").val("1");//下一页
	document.validateinfoFrom.submit();
	
}

$(function(){
	$("#txtProvinceid").val($("#provinceid").val());
	$("#txtCityid").val($("#cityid").val());
	
	
	$('input').attr("readonly",false);
	$('select').attr("disabled",false);
	$("#saveButton").attr("disabled",false);
	$("#checkStateMsg").text("");
	
	/*
	 * 身份证正面
	 */
	$("#upload_file_zm").click(function() {
		var button = $('#upload_file_zm'), interval;
		var fileName = "fileName_zm";
		new AjaxUpload(button, {
			action : projectPath + '/UploadUserInfoImages',
			data : {"fileType": fileName},
			name : 'fileName',
			onSubmit : function(file, ext) {
				button.text('文件上传中！！！');
				interval = window.setInterval(function() {
					var text = button.text();
					if (text.length < 14) {
						button.text(text + '.');
					} else {
						button.text('文件上传中....');
					}
				}, 2000);
			},
			onComplete : function(file, response) {

				getSessionImageId(fileName);
				button.text('上传');
				window.clearInterval(interval);
				this.enable();
			}
		});
	});
	/*
	 * 身份证背面
	 */
	$("#upload_file_bm").click(function() {
		var button = $('#upload_file_bm'), interval;
		var fileName = "fileName_bm";
		new AjaxUpload(button, {
			action : projectPath + '/UploadUserInfoImages',
			data : {"fileType": fileName},
			name : 'fileName',
			onSubmit : function(file, ext) {
				button.text('文件上传中！！！');
				interval = window.setInterval(function() {
					var text = button.text();
					if (text.length < 14) {
						button.text(text + '.');
					} else {
						button.text('文件上传中....');
					}
				}, 2000);
			},
			onComplete : function(file, response) {

				getSessionImageId(fileName);
				button.text('上传');
				window.clearInterval(interval);
				this.enable();
			}
		});
	});
	/*
	 * 手持身份证
	 */
	$("#upload_file_sc").click(function() {
		var button = $('#upload_file_sc'), interval;
		var fileName = "fileName_sc";
		new AjaxUpload(button, {
			action : projectPath + '/UploadUserInfoImages',
			data : {"fileType": fileName},
			name : 'fileName',
			onSubmit : function(file, ext) {
				button.text('文件上传中！！！');
				interval = window.setInterval(function() {
					var text = button.text();
					if (text.length < 14) {
						button.text(text + '.');
					} else {
						button.text('文件上传中....');
					}
				}, 2000);
			},
			onComplete : function(file, response) {

				getSessionImageId(fileName);
				button.text('上传');
				window.clearInterval(interval);
				this.enable();
			}
		});
	});
	
	$("#upload_file_zm").click();
	$("#upload_file_bm").click();
	$("#upload_file_sc").click();
	
	var errorMsg = $.trim($("#errorMsg").val());
	if("" != errorMsg){
		alert(errorMsg);
	}
});


//获取session里的图片ID
function getSessionImageId(fileName) {
	var addurl = projectPath + "/getSessionUserInfoImageId";
	$.post(addurl,{
		"fileType" : fileName
		},
		function(data){
			if(data.fileName.length > 0){
				if(fileName == "fileName_zm"){
					$("#imgFileName_zm").attr("src", imgPath + "/userImg/" +data.fileName);
					$("#imgFileName_zm").attr("width", "100px");
					$("#imgFileName_zm").attr("height", "50px");
					$("#fileName_zm").val(data.fileName);
				}else if(fileName == "fileName_bm"){
					$("#imgFileName_bm").attr("src", imgPath + "/userImg/" +data.fileName);
					$("#imgFileName_bm").attr("width", "100px");
					$("#imgFileName_bm").attr("height", "50px");
					$("#fileName_bm").val(data.fileName);
				}else if(fileName == "fileName_sc"){
					$("#imgFileName_sc").attr("src", imgPath + "/userImg/" +data.fileName);
					$("#imgFileName_sc").attr("width", "100px");
					$("#imgFileName_sc").attr("height", "50px");
					$("#fileName_sc").val(data.fileName);
				}
			}
		}, "json");
}

function doBack(){
	location.href= projectPath + "/goDetailUserSubInfo?userId="+$("#userid").val();
}
	
	
	
	
	
