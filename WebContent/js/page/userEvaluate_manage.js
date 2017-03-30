//筛选
function searchInfo(){
	loadGrid();
}
//重置
function reset(){
	$("#userid").val("");
	$("#bindphone").val("");
	$("#txtdateFrom").datebox("setValue", "");
	$("#txtdateTo").datebox("setValue", "");
}

$(function(){
	//loadGrid();
});

//加载表格datagrid  
function loadGrid() {
	//评价者ID
	var userid = $.trim($("#userid").val());
	//评价者手机
	var bindphone = $.trim($("#bindphone").val());
	//开始时间
	var startTime = $("#txtdateFrom").datebox('getValue');
	//结束时间
	var endTime = $("#txtdateTo").datebox('getValue');
	//开始时间验证
	if(startTime != ""){
		var d1 = new Date(startTime.replace(/\-/g, "\/"));
		var d2 = new Date(endTime.replace(/\-/g, "\/"));
		if(d1 > d2){
			alert("开始时间不能大于结束时间！");
			return;
		}
	}	
	//加载数据  
	$('#cxdm').datagrid({
		view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'UserEvaluateManage',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"userid" : userid,
			"bindphone" : bindphone,
			"startTime" : startTime,
			"endTime" : endTime
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
        onExpandRow: function(index, row){
        	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.panel({
                border:0,
                cache:true,
                href: projectPath + '/goUserEvaluateDetail/' + row.evaid,
                onLoad:function(){
                    $('#cxdm').datagrid('fixDetailRowHeight',index);
                    $('#cxdm').datagrid('selectRow',index);
                    $('#cxdm').datagrid('getRowDetail',index).find('form').form('load',row);
                }
            });
            $('#cxdm').datagrid('fixDetailRowHeight',index);
        },
		columns : [ [ {
			field : 'evaid',
			title : 'ID',
			align : 'center',
			width : 40
		},{
			field : 'userid',
			title : '评价者ID',
			align : 'center',
			hidden: true,
			width : 40
			
		},{
			field : 'pingjia',
			title : '评价者',
			align : 'center',
			width : 80
		},{
			field : 'userName',
			title : '评价者手机号',
			align : 'center',
			width : 80
		},{
			field : 'item',
			title : '被评价者ID',
			align : 'center',
			hidden: true,
			width : 40
		},{
			field : 'beipingjia',
			title : '被评价者',
			align : 'center',
			width : 80
		},{
			field : 'itemName',
			title : '被评价者手机号',
			align : 'center',
			width : 80
		},{
			field : 'content',
			title : '评价信息',
			align : 'left',
			width : 200
		},{
			field : 'deleted',
			title : '操作',
			align : 'center',
			width : 40, formatter:function(value, row){
				var operationContext = '<input id="deleteInfo'+row.evaid+'" type="button" value="删除" onclick="deleteInfo('+row.evaid+')" />';
				//var operationContext = '<input id="detailInfo'+row.evaid+'" type="button" value="查看" onclick="getDetailInfo('+row.evaid+')" />　<input id="deleteInfo'+row.evaid+'" type="button" value="删除" onclick="deleteInfo('+row.evaid+')" />';
                return operationContext;
			},
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

function getDetailInfo(evaid){
	//alert(evaid);
}

function deleteInfo(evaid){
	
	$.messager.confirm('确认删除', '删除当前评价？', function(r){
        if (r){
      	var delurl = projectPath + "doDeleteAdmininfo";

      	var registerUrl = projectPath + "/deleteUserEvaluate";
      	$.post(registerUrl,{
      		"evaid" : evaid
      		},
      		function(data){
      			alert(data.message);
      			$('#cxdm').datagrid('reload');
      		}, "json");
		}
	});
	return;
	
}
