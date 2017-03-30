
//获取session里的图片ID
function getSessionImageId(fileName) {
	var addurl = projectPath + "/getSessionImageId";
	$.post(addurl,{
		"fileType" : fileName
		},
		function(data){
			if(data.info){
				if(data.fileName.length > 0){
					if(fileName == "fileName1"){
						$("#imgFileName1").attr("src", imgPath + data.fileName);
						$("#imgFileName1").attr("width", "100px");
						$("#imgFileName1").attr("height", "50px");
						$("#fileName1").val(data.fileName.substring(1, data.fileName.length));
					}else if(fileName == "fileName2"){
						$("#imgFileName2").attr("src", imgPath + data.fileName);
						$("#imgFileName2").attr("width", "100px");
						$("#imgFileName2").attr("height", "50px");
						$("#fileName2").val(data.fileName.substring(1, data.fileName.length));
					}else if(fileName == "fileName3"){
						$("#imgFileName3").attr("src", imgPath + data.fileName);
						$("#imgFileName3").attr("width", "100px");
						$("#imgFileName3").attr("height", "50px");
						$("#fileName3").val(data.fileName.substring(1, data.fileName.length));
					}else if(fileName == "fileName5"){
						$("#imgFileName5").attr("src", imgPath + data.fileName);
						$("#imgFileName5").attr("width", "100px");
						$("#imgFileName5").attr("height", "50px");
						$("#fileName5").val(data.fileName.substring(1, data.fileName.length));
					}
				}
			}else{
				alert(data.msg);
			}
		}, "json");
}



$(function() {

	// 返回
	$("#btnBack").click(function() {
		window.history.back(-1);
		return;
	});
	
	// 返回
	$("#btnSave").click(function() {
		//提交表单
		document.validateinfoFrom.submit();
	});

	/*
	 * 发型图片
	 */
	$("#upload_file1").click(function() {
		var button = $('#upload_file1'), interval;
		var fileName = "fileName1"
		new AjaxUpload(button, {
			action : projectPath + '/UploadImages',
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
	 * 美发师图片
	 */
	$("#upload_file2").click(function() {
		var button = $('#upload_file2'), interval;
		var fileName = "fileName2"
			
		new AjaxUpload(button, {
			action : projectPath + '/UploadImages',
			data : {"fileType": fileName},
			name : 'fileName',
			onSubmit : function(file, ext) {
				button.text('上传中！');
				interval = window.setInterval(function() {
					var text = button.text();
					if (text.length < 14) {
						button.text(text + '.');
					} else {
						button.text('上传中.');
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
	 * 美发店图片
	 */
	$("#upload_file3").click(function() {
		var button = $('#upload_file3'), interval;
		var fileName = "fileName3"
		new AjaxUpload(button, {
			action : projectPath + '/UploadImages',
			data : {"fileType": fileName},
			name : 'fileName',
			onSubmit : function(file, ext) {
				button.text('上传中！');
				interval = window.setInterval(function() {
					var text = button.text();
					if (text.length < 14) {
						button.text(text + '.');
					} else {
						button.text('上传中.');
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
	 * 预约图片
	 */
	$("#upload_file5").click(function() {
		var button = $('#upload_file5'), interval;
		var fileName = "fileName5"
		new AjaxUpload(button, {
			action : projectPath + '/UploadImages',
			data : {"fileType": fileName},
			name : 'fileName',
			onSubmit : function(file, ext) {
				button.text('上传中！');
				interval = window.setInterval(function() {
					var text = button.text();
					if (text.length < 14) {
						button.text(text + '.');
					} else {
						button.text('上传中.');
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

	$("#upload_file1").click();
	$("#upload_file2").click();
	$("#upload_file3").click();
	$("#upload_file5").click();
	
});
