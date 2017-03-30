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
	$("#txtPayState").val("0");
}

//异常处理
function goOrderExceptionInfo(){
	location.href= projectPath + "/goOrderExceptionInfoManage";
}

function goOrderCount(){
	location.href= projectPath + "/goOrderCount";
}

//加载表格datagrid  
function loadGrid() {
	
	var customerid = $.trim($("#txtCustomerId").val());
	var userid = $.trim($("#txtUserId").val());
	var storeid = $.trim($("#txtStoreId").val());
	var paystate = $.trim($("#txtPayState").val());
	var orderid = $.trim($("#txtOrderId").val());

	var customerTelephone = $.trim($("#txtCustomerTelephone").val());
	var userTelephone = $.trim($("#txtUserTelephone").val());
	
	//开始时间
	var startTime = $("#txtStartTime").datebox('getValue');
	//结束时间
	var endTime = $("#txtEndTime").datebox('getValue');

	if(customerid == ""){
		customerid=0;
	}

	if(userid == ""){
		userid=0;
	}

	if(storeid == ""){
		storeid=0;
	}

	if(orderid == ""){
		orderid=0;
	}
	
	if(customerTelephone == ""){
		customerTelephone="0";
	}
	
	if(userTelephone == ""){
		userTelephone="0";
	}
	
	//加载数据  
	$('#cxdm').datagrid({
		view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'getOrderinfoList',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"customerid" : customerid,
			"userid" : userid,
			"storeid" : storeid,
			"startTime" : startTime,
			"endTime" : endTime,
			"paystate" : paystate,
			"orderid" : orderid,
			"customerTelephone":customerTelephone,
			"userTelephone":userTelephone
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
        onExpandRow: function(index, row){
        	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.panel({
                border:0,
                cache:true,
                href: projectPath + '/goOrderinfoDetail/' + row.orderid,
                onLoad:function(){
                    $('#cxdm').datagrid('fixDetailRowHeight',index);
                    $('#cxdm').datagrid('selectRow',index);
                    $('#cxdm').datagrid('getRowDetail',index).find('form').form('load',row);
                }
            });
            $('#cxdm').datagrid('fixDetailRowHeight',index);
        },
		columns : [ [ {
			field : 'orderid',
			title : 'ID',
			align : 'center',
			width : 60
		},{
			field : 'amount',
			title : '价格',
			align : 'center',
			width : 60
		},{
			field : 'additionalcode',
			title : '用户名',
			align : 'left',
			width : 60
		},{
			field : 'additionalcontent',
			title : '用户手机号',
			align : 'center',
			width : 100
		},{
			field : 'usersubinfo_name',
			title : '发型师名字',
			align : 'left',
			width : 60
		},{
			field : 'usersubinfo_contactmobile',
			title : '发型师联系电话',
			align : 'center',
			width : 100
		},{
			field : 'paystate',
			title : '状态',
			align : 'left',
			width : 60, formatter:function(value, row, index){
				switch(value){				
					case 1:
						return "待支付";
						break;
					case 2:
						return "";
						break;
					case 3:
						return "已预约";
						break;
					case 4:
						return "美发师已签到";
						break;
					case 5:
						return "服务中";
						break;
					case 6:
						return "待评价";
						break;
					case 7:
						return "评价完成";
						break;
					case 8:
						return "订单完成";
						break;
					case 9:
						return "已取消";
						break;
					case 10:
						return "异常处理";
						break;
				}
			}
		},{
			field : 'createtime',
			title : '创建时间',
			align : 'center',
			width : 100
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
	/*var editurl = projectPath + "/UserinfoUpdate";
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
	}, "json");*/
}

