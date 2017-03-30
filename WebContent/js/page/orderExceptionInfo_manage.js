$(function(){
	loadGrid();
});

//加载表格datagrid  
function loadGrid() {
	var paystate = 10;
	//加载数据  
	$('#cxdm').datagrid({
		//view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'orderExceptionInfoManage',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"paystate" : paystate
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
		columns : [ [ {
			field : 'orderid',
			title : 'ID',
			align : 'center',
			width : 100
		},{
			field : 'appointmenttime',
			title : '预约时间',
			align : 'center',
			width : 100
		},{
			field : 'completetime',
			title : '申报异常时间',
			align : 'center',
			width : 100
		},{
			field : 'ordercode',
			title : '操作',
			align : 'center',
			width : 100, formatter:function(value, row){
				var operationContext = '<input id="detailInfo'+row.orderid+'" type="button" value="查看" onclick="goOrderExceptionDetailInfo('+row.orderid+')" />';
                return operationContext;
			},
		}  ] ],
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

function goOrderExceptionDetailInfo(orderid){
	window.location.href = projectPath + "/goOrderExceptionDetailInfo/" + orderid;
	return;
}

