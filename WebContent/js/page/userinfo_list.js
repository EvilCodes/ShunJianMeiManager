//页面加载  
$(document).ready(function(){
	//loadGrid();
});
//查询按钮
function searchInfo(){
	loadGrid();
}
//清空查询条件
function clearInfo(){
	$('#ff').form('clear');
	$("#txtSex").val("-1");
}

//加载表格datagrid  
function loadGrid() {
	
	//开始时间
	var startTime = $("#txtdateFrom").datebox('getValue');
	//结束时间
	var endTime = $("#txtdateTo").datebox('getValue');
	
	//性别
	var txtSex = $("#txtSex").val();
	//美发师昵称
	var txtNickname = $("#txtNickname").val();
	
	//加载数据  
	$('#cxdm').datagrid({
		//view : detailview,
		width : 'auto',
		height : 600,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'getUserinfoList',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"userId" : $("#txtUserid").val(),
			"realName" : $("#txtUsername").val(),
			"txtNickname" : txtNickname,
			"txtSex" : txtSex,
			"startTime" : startTime,
			"endTime" : endTime
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
		columns : [ [ {
			field : 'userid',
			title : '用户ID',
			align : 'center',
			width : 50
		},{
			field : 'username',
			title : '手机号',
			align : 'center',
			width : 80
		},{
			field : 'sex',
			title : '性别',
			align : 'center',
			width : 30, formatter:function(value, row, index){
				if (value == 1) {
					return '男';
				}else if(value == 2){
					return '女';
				}else if(value == 0){
					return '未知';
				}
			}
		},{
			field : 'nickname',
			title : '昵称',
			align : 'center',
			width : 100
		},{
			field : 'referee',
			title : '注册时间',
			align : 'center',
			width : 70
		},{
			field : 'picturepath',
			title : '头像',
			align : 'center',
			width : 100, formatter:function(value, row, index){
                var imgContext = '<img src="' + row.picturepath + '" style="width:50px;height:50px" />';
                return imgContext;
			}
		},{
			field : 'userstate',
			title : '状态',
			align : 'center',
			width : 80, formatter:function(value, row, index){
                var selContext = '<select id="selstate' + row.userid + '" onchange="updateData(' + row.userid + ')">';
				if(value == 0){
					selContext += '<option value="0" selected >停用</option>';
					selContext += '<option value="1">启用</option>';
                }else if(value == 1){
                	selContext += '<option value="0">停用</option>';
                	selContext += '<option value="1" selected>启用</option>';
                }
				selContext += '</select>';
                return selContext;
			}
		} ] ],
		onLoadError : function() {
			XW_dialog.alert('', '加载数据失败！');
		}
	});

	//设置分页控件  
	var p = $('#cxdm').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为20  
		pageList : [ 5, 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function updateData(userid){
	var userstate = document.getElementById("selstate" + userid).value;
	//alert(userid);
	//alert(userstate);
	var editurl = projectPath + "/UserinfoUpdate";
	$.post(editurl, {
		"userid" : userid,
		"userstate" : userstate
	}, function(data) {
		if (data.editInfo == true) {
			$.messager.alert('成功', data.message);
			$('#cxdm').datagrid('reload');
		} else {
			$.messager.alert('失败', data.message, 'error');
		}
	}, "json");
}

