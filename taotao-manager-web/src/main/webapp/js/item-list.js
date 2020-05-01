/**
 * 
 */

var url_searchModelControllerItem = "/item/list";//查询URL
var gridSfyItem;//数据表格对象
var searchSfyItem;//条件查询组件对象

$(function(){
	//初始化数据表格
	gridSfyItem = $('#itemList').datagrid( {
		url : url_searchModelControllerItem,
		fit : true,
		fitColumns : false,
		'toolbar' : toolbar,
		pagination : true,
		closable : true,
		checkOnSelect : true,
		striped : true,
		border : false,
		rownumbers : true,
		ctrlSelect : true,
		queryParams:{
			title: 'easyui',
			sellPoint: 'sellPoint'
		}

	});
	
	//初始化条件查询
	searchFarmdoc = $('#searchItemForm').searchForm( {
		gridObj : gridSfyItem
	});
});