//页面加载  
$(document).ready(function(){
	//loadGrid();
});

//查询按钮
function searchInfo(){
	loadGrid();
	getCount();
}

//清空查询条件
function clearInfo(){
	$("#txtStartTime").datebox("setValue", "");
	$("#txtEndTime").datebox("setValue", "");
	$('#ff').form('clear');
}

function getCount(){
	//开始时间
	var startTime = $("#txtStartTime").datebox('getValue');
	//结束时间
	var endTime = $("#txtEndTime").datebox('getValue');
	
	var addurl = projectPath + "/UserTradeLogTotal";
	$.post(addurl,{
		"startTime" : startTime,
		"endTime" : endTime
		},
		function(data){
			$("#lblTotal").text(data.total);
		}, "json");
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
		url : 'UserTradeLogManage',
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
			field : 'ordercode',
			title : '订单编号',
			align : 'center',
			width : 100
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
			field : 'stylename',
			title : '发型',
			align : 'center',
			width : 70
		},{
			field : 'amount',
			title : '支付金额',
			align : 'center',
			width : 80
		},{
			field : 'appointmenttime',
			title : '预约时间',
			align : 'center',
			width : 80
		},{
			field : 'createtime',
			title : '创建时间',
			align : 'center',
			width : 80
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
