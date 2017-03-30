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
	$("#txtStartTime").datebox("setValue", "");
	$("#txtEndTime").datebox("setValue", "");
	$('#ff').form('clear');
}

//加载表格datagrid  
function loadGrid() {
	//开始时间
	var startTime = $("#txtStartTime").datebox('getValue');
	//结束时间
	var endTime = $("#txtEndTime").datebox('getValue');
	
	//加载数据  
	$('#cxdm').datagrid({
		//view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'UserCashrecordManage',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"startTime" : startTime,
			"endTime" : endTime
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
		columns : [ [ {
			field : 'recordid',
			title : '记录ID',
			align : 'center',
			width : 50
		},{
			field : 'userid',
			title : '用户ID',
			align : 'center',
			width : 50
		},{
			field : 'username',
			title : '名字',
			align : 'center',
			width : 70
		},{
			field : 'userphone',
			title : '用户手机号',
			align : 'center',
			width : 80
		},{
			field : 'bank',
			title : '开户行',
			align : 'center',
			width : 100
		},{
			field : 'cardnumber',
			title : '卡号',
			align : 'center',
			width : 150
		},{
			field : 'amount',
			title : '金额',
			align : 'center',
			width : 100
		},{
			field : 'state',
			title : '状态',
			align : 'center',
			width : 100, formatter:function(value, row, index){
				/*switch(value){				
					case 1:
						return "申请中";
						break;
					case 2:
						return "成功";
						break;
					case 3:
						return "失败";
						break;
				}*/
				
				var selContext = '<select id="selstate' + row.recordid + '" onchange="updateData(' + row.recordid + ')">';
				if(value == 1){
					selContext += '<option value="1" selected >申请中</option>';
					selContext += '<option value="2">成功</option>';
					selContext += '<option value="3">失败</option>';
                }else if(value == 2){
					selContext += '<option value="1">申请中</option>';
					selContext += '<option value="2" selected >成功</option>';
					selContext += '<option value="3">失败</option>';
                }else if(value == 3){
					selContext += '<option value="1">申请中</option>';
					selContext += '<option value="2">成功</option>';
					selContext += '<option value="3" selected >失败</option>';
                }
				selContext += '</select>';
                return selContext;
			}
		},{
			field : 'createtime',
			title : '创建时间',
			align : 'center',
			width : 100, formatter:function(value, row, index){
				var date = new Date(value);
				Y = date.getFullYear() + '-';
				M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
				D = date.getDate() + ' ';
				h = date.getHours() + ':';
				m = date.getMinutes() + ':';
				s = date.getSeconds(); 
				return Y + M + D + h + m + s;
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

function updateData(recordid){
	var state = document.getElementById("selstate" + recordid).value;
	//alert(userid);
	//alert(userstate);
	var editurl = projectPath + "/UserCashrecordUpdate";
	$.post(editurl, {
		"recordid" : recordid,
		"state" : state
	}, function(data) {
		if (data.editInfo == true) {
			$.messager.alert('成功', data.message);
			$('#cxdm').datagrid('reload');
		} else {
			$.messager.alert('失败', data.message, 'error');
		}
	}, "json");
}

