//页面加载  
$(document).ready(function(){
	loadGrid();
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
		height : 250,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'UserTradeLogCount',
		loadMsg : '数据加载中请稍后……',
		//pagination : true,
		fitColumns : true,
		queryParams : {
			"startTime" : startTime,
			"endTime" : endTime
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
		columns : [ [ {
			field : 'shouru',
			title : '收入',
			align : 'center',
			width : 100
		},{
			field : 'fencheng',
			title : '分成',
			align : 'center',
			width : 100
		},{
			field : 'quxiao',
			title : '取消',
			align : 'center',
			width : 100
		},{
			field : 'youhui',
			title : '优惠',
			align : 'center',
			width : 100
		}] ],
		onLoadError : function() {
			XW_dialog.alert('', '加载数据失败！');
		}
	});

	//设置分页控件  
	/*var p = $('#cxdm').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为20  
		pageList : [ 5, 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});*/
}
