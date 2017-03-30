//筛选
function searchInfo(){
	loadGrid();
}
//重置
function reset(){
	$("#txtdateFrom").datebox("setValue", "");
	$("#txtdateTo").datebox("setValue", "");
}

$(function(){
	//loadGrid();
});

//加载表格datagrid  
function loadGrid() {
	//开始时间
	var startTime = $("#txtdateFrom").datebox('getValue');
	//结束时间
	var endTime = $("#txtdateTo").datebox('getValue');

	//加载数据  
	$('#cxdm').datagrid({
		view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'BaseSuggestionManage',
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
			field : 'userid',
			title : '用户ID',
			align : 'center',
			width : 50
		},{
			field : 'email',
			title : '联系方式',
			align : 'left',
			width : 80
		},{
			field : 'content',
			title : '反馈内容',
			align : 'left',
			width : 200
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
