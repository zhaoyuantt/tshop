<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemPropertyList" title="商品列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/property/list',method:'get',pageSize:30,toolbar:itemPropertyListToolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:80">ID</th>
        	<th data-options="field:'itemCatId',width:100">商品类目ID</th>
        	<th data-options="field:'catName',width:100">商品类目</th>
            <th data-options="field:'itemProperty',width:350,formatter:formatPropertyParamData">属性(只显示分组名称)</th>
            <th data-options="field:'created',width:150,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:150,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

	function formatPropertyParamData(value){
		var json = JSON.parse(value);
		var array = [];
		$.each(json,function(i,e){
			array.push(e.group);
		});
		return array.join(",");
	}

    function getSelectionsIds(){
    	var itemList = $("#itemPropertyList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }

    var itemPropertyListToolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	TAOTAO.createWindow({
        		url : "/item-property-add",
        	});
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	$.messager.alert('提示','该功能未实现!');
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品规格!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品规格吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/param/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除商品规格成功!',undefined,function(){
            					$("#itemPropertyList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>