<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 查询表单 -->
<!-- Add on 2019-11-18 17:00:19 -->
<div class="easyui-layout" data-options="region:'north'"
	style="border-style: solid; border-width: 1px; border-color: #95B8E7;">
	<form action="" id="searchItemForm" class="itemForm" method="GET">
		<table class="editTable">
			<tr>
				<td class="title">商品标题:</td>
				<td><input name="title" value="${pageSearchInputValue_title}"
					type="text" style="width: 240px;"
					data-options="validType:['maxLength[20]','isString[]']"></td>
				<td class="title" style="padding-left: 50px">卖点 :</td>
				<td><input name="sellPoint"
					value="${pageSearchInputValue_sellPoint}" type="text"
					style="width: 240px;" 
					data-options="validType:['maxLength[20]','isString[]']"></td>
				<td colspan="8" style="padding-left: 50px"><a id="a_search"
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search">查询</a> &nbsp;&nbsp;&nbsp;<a id="a_reset"
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-reload">清除条件</a></td>
			</tr>
		</table>
	</form>
</div>
</br>

<!-- 超级管理员 -->
<div class="easyui-layout" data-options="region:'center',border:false">
	<c:if test="${null != USER.username }">
		<c:if test="${'zhaoyuan' == USER.username}">
			<table id="itemList" title="商品列表">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="id" data-options="sortable:true,align:'center'">商品ID</th>
						<th field="title" data-options="width:250,align:'center'">商品标题</th>
						<th field="cid" data-options="width:100,align:'center'">叶子类目</th>
						<th field="cname" data-options="width:100,align:'center'">类目名称</th>
						<th field="sellPoint" data-options="width:200">卖点</th>
						<th field="price"
							data-options="width:70,align:'center',formatter:TAOTAO.formatPrice">价格
						</th>
						<th field="num" data-options="width:70,align:'center'">库存数量</th>
						<th field="barcode" data-options="width:100,align:'center'">条形码</th>
						<th field="status"
							data-options="width:60,align:'center',formatter:TAOTAO.formatItemStatus">状态
						</th>
						<th field="created"
							data-options="width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期
						</th>
						<th field="updated"
							data-options="width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期
						</th>
					</tr>
				</thead>
			</table>
		</c:if>
	</c:if>

	<!-- 普通管理员 -->
	<c:if test="${null != USER.username }">
		<c:if test="${'zhaoyuan' != USER.username}">
			<table class="easyui-datagrid" id="itemList" title="商品列表">
				<thead>
					<tr style="text-align: center;">
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="id" data-options="width:60,align:'center'">商品ID</th>
						<th field="title" data-options="width:250,align:'center'">商品标题</th>
						<th field="cid" data-options="width:100,align:'center'">叶子类目</th>
						<th field="cname" data-options="width:100,align:'center'">类目名称</th>
						<th field="sellPoint" data-options="width:200,align:'center'">卖点</th>
						<th field="price"
							data-options="width:70,formatter:TAOTAO.formatPrice,align:'center'">价格
						</th>
						<th field="num" data-options="width:70,align:'center'">库存数量</th>
						<th field="barcode" data-options="width:100,align:'center'">条形码</th>
						<th field="status"
							data-options="width:60,align:'center',formatter:TAOTAO.formatItemStatus">状态
						</th>
						<th field="created"
							data-options="width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期
						</th>
						<th field="updated"
							data-options="width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期
						</th>
					</tr>
				</thead>
			</table>
		</c:if>
	</c:if>
</div>

<div id="itemEditWindow" class="easyui-window" title="编辑商品"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'item-edit'"
	style="width: 80%; height: 80%; padding: 10px;"></div>
<script type="text/javascript">
	var url_searchModelControllerItem = "/item/list";//查询URL
	var gridItem;//数据表格对象
	var searchItem;//条件查询组件对象

	var toolAdminBar = [ {
		id : 'add',
		text : '新增',
		iconCls : 'icon-add',
		handler : itemAdd
	}, {
		id : 'edit',
		text : '编辑',
		iconCls : 'icon-edit',
		handler : itemEdit
	}, {
		id : 'del',
		text : '删除',
		iconCls : 'icon-remove',
		handler : itemDel
	}, {
		id : 'itemUp',
		text : '上架',
		iconCls : 'icon-remove',
		handler : itemUp
	}, {
		id : 'itemDown',
		text : '下架',
		iconCls : 'icon-remove',
		handler : itemDown
	} ];

	$(function() {
		//初始化条件查询
		/* searchItem = $('#searchItemForm').searchForm( {
			gridObj : gridItem
		}); */

		/**
		 * todo
		 * 不知道为什么"初始化","查询","清空"三个事件，不能写成一个function，写成一个感觉好像是加载顺序不对。
		 *
		 * */
		
		
		//初始化数据表格
		console.log("jQuery调用js方法初始化表格数据");
		initDataGrid();
		
		//为"清空"按钮绑定点击事件
		$('#a_reset').click(function(){
			//清空input框的value值
			$("input[name='title']").val("");
			$("input[name='sellPoint']").val("");
			gridItem = $('#itemList').datagrid({
				url : url_searchModelControllerItem,
				pagination : true,
				pageSize : 30,
				toolbar : toolAdminBar,
				fitColumns : false,
				closable : true,
				checkOnSelect : true,
				border : true,
				rownumbers : true,
				ctrlSelect : true,
				cache:false,
				queryParams : {
					title : "",
					sellPoint : ""
				}
			});
		});
		
		//为"查询"按钮绑定点击事件
		$('#a_search').click(function() {
			var title = $("input[name='title']").val();
			var sellPoint = $("input[name='sellPoint']").val();
			$('#itemList').datagrid({
				url : url_searchModelControllerItem,
				pagination : true,
				pageSize : 30,
				toolbar : toolAdminBar,
				fitColumns : false,
				closable : true,
				checkOnSelect : true,
				border : true,
				rownumbers : true,
				ctrlSelect : true,
				cache:false,
				queryParams : {
					title : title,
					sellPoint : sellPoint
				}

			});

		});
	
	});

	function initDataGrid(){
		gridItem = $('#itemList').datagrid({
			url : url_searchModelControllerItem,
			pagination : true,
			pageSize : 30,
			toolbar : toolAdminBar,
			fitColumns : false,
			closable : true,
			checkOnSelect : true,
			border : true,
			rownumbers : true,
			ctrlSelect : true,
			cache:false
		});
	}
	
	function getSelectionsIds() {
		var itemList = $("#itemList");
		var sels = itemList.datagrid("getSelections");
		var ids = [];
		for ( var i in sels) {
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}

	/* 新增商品 */
	function itemAdd() {
		$(".tree-title:contains('新增商品')").parent().click();
	}
	

	function getCnameVal(data){
		//查询类目名称
		var cnameVal = "";
		$.getJSON('/item/cat/cname/'+ data.cid, function(_data) {
			if (_data.status == 200) {
				cnameVal = _data.data;
				return cnameVal;
			}
		});
	}

	/* 编辑商品 */
	function itemEdit() {
		var ids = getSelectionsIds();
		if (ids.length == 0) {
			$.messager.alert('提示', '必须选择一个商品才能编辑!');
			return;
		}
		if (ids.indexOf(',') > 0) {
			$.messager.alert('提示', '只能选择一个商品进行编辑!');
			return;
		}

		$("#itemEditWindow")
				.window(
						{
							onLoad : function() {
								//回显数据
								var data = $("#itemList").datagrid(
										"getSelections")[0];
								
								data.priceView = TAOTAO.formatPrice(data.price);
								$("#itemeEditForm").form("load", data);
								// 加载商品描述
								$.getJSON('/item/desc/'
										+ data.id, function(_data) {
									if (_data.status == 200) {
										//UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);
										itemEditEditor
												.html(_data.data.itemDesc);
									}
								});
								

								//加载商品规格
								$
										.getJSON(
												'/item/param/item/query/'
														+ data.id,
												function(_data) {
													if (_data
															&& _data.status == 200
															&& _data.data
															&& _data.data.paramData) {
														$(
																"#itemeEditForm .params")
																.show();
														$(
																"#itemeEditForm [name=itemParams]")
																.val(
																		_data.data.paramData);
														$(
																"#itemeEditForm [name=itemParamId]")
																.val(
																		_data.data.id);

														//回显商品规格
														var paramData = JSON
																.parse(_data.data.paramData);

														var html = "<ul>";
														for ( var i in paramData) {
															var pd = paramData[i];
															html += "<li><table>";
															html += "<tr><td colspan=\"2\" class=\"group\">"
																	+ pd.group
																	+ "</td></tr>";

															for ( var j in pd.params) {
																var ps = pd.params[j];
																html += "<tr><td class=\"param\"><span>"
																		+ ps.k
																		+ "</span>: </td><td><input autocomplete=\"off\" type=\"text\" value='" + ps.v + "'/></td></tr>";
															}

															html += "</li></table>";
														}
														html += "</ul>";
														$(
																"#itemeEditForm .params td")
																.eq(1).html(
																		html);
													}
												});
								TAOTAO.init({
									"pics" : data.image,
									"cid" : data.cid,
									"cname":data.cname,
									fun : function(node) {
										TAOTAO.changeItemParam(node,
												"itemeEditForm");
									}
								});
							}
						}).window("open");
	}

	/* 删除商品 */
	function itemDel() {
		var ids = getSelectionsIds();
		if (ids.length == 0) {
			$.messager.alert('提示', '未选中商品!');
			return;
		}
		$.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的商品吗？', function(r) {
			if (r) {
				var params = {
					"ids" : ids
				};
				$.post("/item/delete", params, function(data) {
					if (data.status == 200) {
						$.messager.alert('提示', '删除商品成功!', undefined,
								function() {
									$("#itemList").datagrid("reload");
								});
					}
				});
			}
		});
	}

	/* 下架商品 */
	function itemDown() {
		var ids = getSelectionsIds();
		if (ids.length == 0) {
			$.messager.alert('提示', '未选中商品!');
			return;
		}
		$.messager.confirm('确认', '确定下架ID为 ' + ids + ' 的商品吗？', function(r) {
			if (r) {
				var params = {
					"ids" : ids
				};
				$.post("item/change/2", params, function(data) {
					if (data.status == 200) {
						$.messager.alert('提示', '下架商品成功!', undefined,
								function() {
									$("#itemList").datagrid("reload");
								});
					}
				});
			}
		});
	}

	/* 上架商品 */
	function itemUp() {
		var ids = getSelectionsIds();
		if (ids.length == 0) {
			$.messager.alert('提示', '未选中商品!');
			return;
		}
		$.messager.confirm('确认', '确定上架ID为 ' + ids + ' 的商品吗？', function(r) {
			if (r) {
				var params = {
					"ids" : ids
				};
				$.post("item/change/1", params, function(data) {
					if (data.status == 200) {
						$.messager.alert('提示', '上架商品成功!', undefined,
								function() {
									$("#itemList").datagrid("reload");
								});
					}
				});
			}
		});
	}
</script>