//筛选
function searchInfo(){
	loadGrid();
}
//重置
function reset(){
	$("#storeid").val("");
	$("#txtdateFrom").datebox("setValue", "");
	$("#txtdateTo").datebox("setValue", "");
	$("#txtState").val("-1");
	$("#name").val("");
}

//商户生成
function initStoreInfo(){
	var registerUrl = projectPath + "/initStoreInfo";
	$.post(registerUrl,
		function(data){
			alert(data.message);
		}, "json");
}

$(function(){
	//loadGrid();
});

//加载表格datagrid  
function loadGrid() {
	//商户ID
	var storeid = $.trim($("#storeid").val());
	//开始时间
	var startTime = $("#txtdateFrom").datebox('getValue');
	//结束时间
	var endTime = $("#txtdateTo").datebox('getValue');
	//审核状态
	var state = $("#txtState").val();
	//商户名称
	var name = $.trim($("#name").val());

	//加载数据  
	$('#cxdm').datagrid({
		//view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'StoreinfoManage',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"storeid" : storeid,
			"startTime" : startTime,
			"endTime" : endTime,
			"state" : state,
			"name" : name
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
		columns : [ [ {
			field : 'storeid',
			title : 'ID',
			align : 'center',
			width : 50
		},{
			field : 'password',
			title : '密码',
			align : 'left',
			width : 200
		},{
			field : 'name',
			title : '商户名称',
			align : 'left',
			width : 150
		},{
			field : 'score',
			title : '服务星级',
			align : 'center', formatter:function(value, row, index){
                switch (value) {
                    case 1:
                        return '一星';
                        break;
                    case 2:
                        return '二星';  
                        break;  
                    case 3:
                        return '三星';
                        break;
                    case 4:
                        return '四星';
                        break;
                    case 5:
                        return '五星';
                        break;
                }  
			},
			width : 70
		},{
			field : 'state',
			title : '状态',
			align : 'center',
			width : 100, formatter:function(value, row, index){
                var selContext = '<select id="selCheckstate' + row.storeid + '" onchange="updateCheckstate(' + row.storeid + ')">';
				if(value == 1){
					selContext += '<option value="1" selected>审核中</option>';
	                selContext += '<option value="2">审核通过</option>';
	                selContext += '<option value="3">审核失败</option>';
                }else if(value == 2){
                	selContext += '<option value="1">审核中</option>';
                    selContext += '<option value="2" selected>审核通过</option>';
                    selContext += '<option value="3">审核失败</option>';
                }else if(value == 3){
                	selContext += '<option value="1">审核中</option>';
                    selContext += '<option value="2">审核通过</option>';
                    selContext += '<option value="3" selected>审核失败</option>';
                }
				selContext += '</select>';
                return selContext;
			}
		},{
			field : 'allocation',
			title : '分成',
			align : 'center',
			width : 50
		},{
			field : 'createtime',
			title : '操作',
			align : 'center',
			width : 50, formatter:function(value, row, index){
                var selContext = '<input id="storeid_' + row.storeid + '" value="查看" type="button" onclick="goStoreinfoValidateInfo(' + row.storeid + ')" />';
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

//修改状态
function updateCheckstate(storeid){
	var checkstate = document.getElementById("selCheckstate" + storeid).value;
	var editurl = projectPath + "/StoreinfoUpdateState";
	$.post(editurl, {
		"storeid" : storeid,
		"state" : checkstate
	}, function(data) {
		if (data.editInfo == true) {
			$.messager.alert('成功', data.message);
			$('#cxdm').datagrid('reload');
		} else {
			$.messager.alert('失败', data.message, 'error');
		}
	}, "json");
}

//跳转店铺详情
function goStoreinfoValidateInfo(storeid){
	
	window.location.href = projectPath + "/goStoreinfoValidateInfo/" + storeid;
	return;
}

