<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="itemCategory" class="easyui-tree">
    </ul>
</div>
<div id="itemCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-edit',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#itemCategory").tree({
		url : '/item/cat/list',
		animate: true,
		method : "GET",
		onContextMenu: function(e,node){
            e.preventDefault();
            $(this).tree('select',node.target);
            $('#itemCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        onAfterEdit : function(node){
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点
        		$.post("/item/cat/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,
            				id : data.data.id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		//ADD BY ZHAOYUNA ON 2019-05-08
        		$.post(
        				"/item/cat/update",
        				{id:node.id,name:node.text},
        				function(data){
        					if(200 != data.status){
        						$.messager.alert('提示','重命名'+node.text+' 分类失败!'+data.msg);
        					}
        				}
        		);
        		//END ADD
        	}
        }
	});
});
function menuHandler(item){
	var tree = $("#itemCategory");
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: '新建分类',
                id : 0,
                parentId : node.id
            }]
        }); 
		var _node = tree.tree('find',0);
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？同时将删除该分类下的所有分类',function(r){
			if(r){
				$.post(
				"/item/cat/delete",
				{parentId:node.parentId,id:node.id},
				function(data){
					if(200 == data.status){
						$.messager.alert('提示','分类 '+node.text+ ' 删除成功');
						tree.tree("remove",node.target);
					}else{
						$.messager.alert('提示','分类 '+node.text+ '删除失败!'+data.msg);
					}
				});	
			}
		});
	}
}
</script>