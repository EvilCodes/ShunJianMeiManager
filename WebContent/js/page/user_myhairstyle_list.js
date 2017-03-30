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
	$("#txtState").val(0);
	//loadGrid();
}

//加载表格datagrid  
function loadGrid() {
	/*var userid = 0, mystyleid = 0;
	if($("#txtUserid").val() != ""){
		userid = $("#txtUserid").val();
	}
	if($("#txtMystyleid").val() != ""){
		mystyleid = $("#txtMystyleid").val();
	}*/
	
	//美发师ID
	var userid = $("#userid").val();
	//美发师手机
	var tel = $("#bindphone").val();
	//开始时间
	var startTime = $("#txtdateFrom").datebox('getValue');
	//结束时间
	var endTime = $("#txtdateTo").datebox('getValue');
	//审核状态
	var checkstate = $("#txtState").val();
	//昵称
	var txtNickname = $("#txtNickname").val();
	
	//加载数据  
	$('#cxdm').datagrid({
		view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'getUserMyhairstyleList',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"userid" : userid,
			"tel" : tel,
			"startTime" : startTime,
			"endTime" : endTime,
			"checkstate" : checkstate,
			"txtNickname":txtNickname
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
        onExpandRow: function(index, row){
        	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.panel({
                border:0,
                cache:true,
                href: projectPath + '/goUserMyhairstyleDetail/' + row.mystyleid,
                onLoad:function(){
                    $('#cxdm').datagrid('fixDetailRowHeight',index);
                    $('#cxdm').datagrid('selectRow',index);
                    $('#cxdm').datagrid('getRowDetail',index).find('form').form('load',row);
                }
            });
            $('#cxdm').datagrid('fixDetailRowHeight',index);
        },
		columns : [ [ {
			field : 'mystyleid',
			title : 'ID',
			align : 'center',
			width : 70
		},{
			field : 'name',
			title : '发型类型',
			align : 'center',
			width : 100
		},{
			field : 'nickname',
			title : '美发师',
			align : 'center',
			width : 80
		},{
			field : 'bindphone',
			title : '美发师手机号',
			align : 'center',
			width : 100
		},{
			field : 'state',
			title : '状态',
			align : 'center',
			width : 100, formatter:function(value, row, index){
                var selContext = '<select id="selstate' + row.mystyleid + '" onchange="updateData(' + row.mystyleid + ')">';
				if(value == 1){
					selContext += '<option value="1" selected >审核中</option>';
					selContext += '<option value="2">审核失败</option>';
					selContext += '<option value="3">待发布</option>';
					selContext += '<option value="4">已发布</option>';
                }else if(value == 2){
					selContext += '<option value="1">审核中</option>';
					selContext += '<option value="2" selected>审核失败</option>';
					selContext += '<option value="3">待发布</option>';
					selContext += '<option value="4">已发布</option>';
                }else if(value == 3){
					selContext += '<option value="1">审核中</option>';
					selContext += '<option value="2">审核失败</option>';
					selContext += '<option value="3" selected>待发布</option>';
					selContext += '<option value="4">已发布</option>';
                }else if(value == 4){
					selContext += '<option value="1">审核中</option>';
					selContext += '<option value="2">审核失败</option>';
					selContext += '<option value="3">待发布</option>';
					selContext += '<option value="4" selected>已发布</option>';
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

function updateData(mystyleid){
	var state = document.getElementById("selstate" + mystyleid).value;

	var editurl = projectPath + "/UserMyhairstyleUpdate";
	$.post(editurl, {
		"mystyleid" : mystyleid,
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

