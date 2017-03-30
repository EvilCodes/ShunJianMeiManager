//筛选
function searchInfo(){
	loadGrid();
}
//重置
function reset(){
	$("#userid").val("");
	$("#bindphone").val("");
	$("#txtNickname").val("");
	$("#txtdateFrom").datebox("setValue", "");
	$("#txtdateTo").datebox("setValue", "");
	$("#txtState").val("0");
}

$(function(){
	//loadGrid();
});

//加载表格datagrid  
function loadGrid() {
	//城市ID
	var cityId = 1;
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
	//美发师昵称
	var txtNickname = $("#txtNickname").val();
	
	
	//加载数据  
	$('#cxdm').datagrid({
		//view : detailview,
		width : 'auto',
		height : 350,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'UsersubinfoManage',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"cityid" : cityId,
			"userid" : userid,
			"tel" : tel,
			"startTime" : startTime,
			"endTime" : endTime,
			"checkstate" : checkstate,
			"txtNickname" : txtNickname
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
        onExpandRow: function(index,row){
        	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.panel({
                border:0,
                cache:true,
                href: projectPath + '/goOrderinfoDetail/' + row.orderid + '/0',
                onLoad:function(){
                    $('#cxdm').datagrid('fixDetailRowHeight',index);
                    $('#cxdm').datagrid('selectRow',index);
                    $('#cxdm').datagrid('getRowDetail',index).find('form').form('load',row);
                }
            });
            $('#cxdm').datagrid('fixDetailRowHeight',index);
        },
		columns : [ [ {
			field : 'userid',
			title : 'ID',
			align : 'center',
			width : 70
		},{
			field : 'bindphone',
			title : '手机',
			align : 'center',
			width : 80
		},{
			field : 'nickname',
			title : '昵称',
			align : 'center',
			width : 100
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
			width : 50
		},{
			field : 'levelname',
			title : '定级',
			align : 'center',
			/*width : 100, formatter:function(value, row, index){
				var list = "${userProfessionLevelList}";
				var value = "${list.levelname}";
				
                var selContext = '<select id="selLevel' + row.userid + '" onchange="updateLevel(' + row.userid + ')">';
                
                selContext += '<c:forEach var="list" items='+list+'>';
                selContext += '"' +value+ '"';	
                selContext += '</c:forEach>';
				
				selContext += '</select>';
                return selContext;
			},*/
			width : 70
		} ,{
			field : 'checkstate',
			title : '状态',
			align : 'center',
			/*width : 100, formatter:function(value, row, index){
            var selContext = '<select id="selCheckstate' + row.userid + '" onchange="updateCheckstate(' + row.userid + ')">';
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
		},*/
			width : 70, formatter:function(value, row, index){
            var selContext = '<label id="selCheckstate' + row.userid + '">';
			if(value == 1){
				selContext += '审核中';
            }else if(value == 2){
                selContext += '审核通过';
            }else if(value == 3){
            	selContext += '审核失败';
            }else if(value == 0){
            	selContext += '未提交审核';
            }
			selContext += '</label>';
            return selContext;
		},width : 100
		} ,{
			field : 'balance',
			title : '钱包余额',
			align : 'center',
			width : 100
		} ,{
			field : 'createData',
			title : '注册时间',
			align : 'center',
			width : 80
		} ,{
			field : 'allocation',
			title : '操作',
			align : 'center',
			width : 100, formatter:function(value, row){
				var operationContext = '<input id="detailInfo'+row.userid+'" type="button" value="查看" onclick="getDetailInfo('+row.userid+')" />';
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
		pageSize : 10,//每页显示的记录条数，默认为10  
		pageList : [ 5, 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

//修改状态
function updateCheckstate(userid){
	var checkstate = document.getElementById("selCheckstate" + userid).value;
	var editurl = projectPath + "/UsersubinfoUpdateState";
	$.post(editurl, {
		"userid" : userid,
		"checkstate" : checkstate
	}, function(data) {
		if (data.editInfo == true) {
			$.messager.alert('成功', data.message);
			$('#cxdm').datagrid('reload');
		} else {
			$.messager.alert('失败', data.message, 'error');
		}
	}, "json");
}

//查看详情
function getDetailInfo(userid){
	location.href= projectPath + "/goDetailUserSubInfo?userId="+userid;
}