/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-11-22 16:10:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class item_002dlist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 查询表单 -->\r\n");
      out.write("<!-- Add on 2019-11-18 17:00:19 -->\r\n");
      out.write("<div class=\"easyui-layout\" data-options=\"region:'north'\"\r\n");
      out.write("\tstyle=\"border-style: solid; border-width: 1px; border-color: #95B8E7;\">\r\n");
      out.write("\t<form action=\"\" id=\"searchItemForm\" class=\"itemForm\" method=\"GET\">\r\n");
      out.write("\t\t<table class=\"editTable\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"title\">商品标题:</td>\r\n");
      out.write("\t\t\t\t<td><input name=\"title\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageSearchInputValue_title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\ttype=\"text\" style=\"width: 240px;\"\r\n");
      out.write("\t\t\t\t\tdata-options=\"validType:['maxLength[20]','isString[]']\"></td>\r\n");
      out.write("\t\t\t\t<td class=\"title\" style=\"padding-left: 50px\">卖点 :</td>\r\n");
      out.write("\t\t\t\t<td><input name=\"sellPoint\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageSearchInputValue_sellPoint}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text\"\r\n");
      out.write("\t\t\t\t\tstyle=\"width: 240px;\" \r\n");
      out.write("\t\t\t\t\tdata-options=\"validType:['maxLength[20]','isString[]']\"></td>\r\n");
      out.write("\t\t\t\t<td colspan=\"8\" style=\"padding-left: 50px\"><a id=\"a_search\"\r\n");
      out.write("\t\t\t\t\thref=\"javascript:void(0)\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\ticonCls=\"icon-search\">查询</a> &nbsp;&nbsp;&nbsp;<a id=\"a_reset\"\r\n");
      out.write("\t\t\t\t\thref=\"javascript:void(0)\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\ticonCls=\"icon-reload\">清除条件</a></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("</br>\r\n");
      out.write("\r\n");
      out.write("<!-- 超级管理员 -->\r\n");
      out.write("<div class=\"easyui-layout\" data-options=\"region:'center',border:false\">\r\n");
      out.write("\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<!-- 普通管理员 -->\r\n");
      out.write("\t");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"itemEditWindow\" class=\"easyui-window\" title=\"编辑商品\"\r\n");
      out.write("\tdata-options=\"modal:true,closed:true,iconCls:'icon-save',href:'item-edit'\"\r\n");
      out.write("\tstyle=\"width: 80%; height: 80%; padding: 10px;\"></div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar url_searchModelControllerItem = \"/item/list\";//查询URL\r\n");
      out.write("\tvar gridItem;//数据表格对象\r\n");
      out.write("\tvar searchItem;//条件查询组件对象\r\n");
      out.write("\r\n");
      out.write("\tvar toolAdminBar = [ {\r\n");
      out.write("\t\tid : 'add',\r\n");
      out.write("\t\ttext : '新增',\r\n");
      out.write("\t\ticonCls : 'icon-add',\r\n");
      out.write("\t\thandler : itemAdd\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'edit',\r\n");
      out.write("\t\ttext : '编辑',\r\n");
      out.write("\t\ticonCls : 'icon-edit',\r\n");
      out.write("\t\thandler : itemEdit\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'del',\r\n");
      out.write("\t\ttext : '删除',\r\n");
      out.write("\t\ticonCls : 'icon-remove',\r\n");
      out.write("\t\thandler : itemDel\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'itemUp',\r\n");
      out.write("\t\ttext : '上架',\r\n");
      out.write("\t\ticonCls : 'icon-remove',\r\n");
      out.write("\t\thandler : itemUp\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'itemDown',\r\n");
      out.write("\t\ttext : '下架',\r\n");
      out.write("\t\ticonCls : 'icon-remove',\r\n");
      out.write("\t\thandler : itemDown\r\n");
      out.write("\t} ];\r\n");
      out.write("\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t//初始化条件查询\r\n");
      out.write("\t\t/* searchItem = $('#searchItemForm').searchForm( {\r\n");
      out.write("\t\t\tgridObj : gridItem\r\n");
      out.write("\t\t}); */\r\n");
      out.write("\r\n");
      out.write("\t\t/**\r\n");
      out.write("\t\t * todo\r\n");
      out.write("\t\t * 不知道为什么\"初始化\",\"查询\",\"清空\"三个事件，不能写成一个function，写成一个感觉好像是加载顺序不对。\r\n");
      out.write("\t\t *\r\n");
      out.write("\t\t * */\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//初始化数据表格\r\n");
      out.write("\t\tconsole.log(\"jQuery调用js方法初始化表格数据\");\r\n");
      out.write("\t\tinitDataGrid();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//为\"清空\"按钮绑定点击事件\r\n");
      out.write("\t\t$('#a_reset').click(function(){\r\n");
      out.write("\t\t\t//清空input框的value值\r\n");
      out.write("\t\t\t$(\"input[name='title']\").val(\"\");\r\n");
      out.write("\t\t\t$(\"input[name='sellPoint']\").val(\"\");\r\n");
      out.write("\t\t\tgridItem = $('#itemList').datagrid({\r\n");
      out.write("\t\t\t\turl : url_searchModelControllerItem,\r\n");
      out.write("\t\t\t\tpagination : true,\r\n");
      out.write("\t\t\t\tpageSize : 30,\r\n");
      out.write("\t\t\t\ttoolbar : toolAdminBar,\r\n");
      out.write("\t\t\t\tfitColumns : false,\r\n");
      out.write("\t\t\t\tclosable : true,\r\n");
      out.write("\t\t\t\tcheckOnSelect : true,\r\n");
      out.write("\t\t\t\tborder : true,\r\n");
      out.write("\t\t\t\trownumbers : true,\r\n");
      out.write("\t\t\t\tctrlSelect : true,\r\n");
      out.write("\t\t\t\tcache:false,\r\n");
      out.write("\t\t\t\tqueryParams : {\r\n");
      out.write("\t\t\t\t\ttitle : \"\",\r\n");
      out.write("\t\t\t\t\tsellPoint : \"\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//为\"查询\"按钮绑定点击事件\r\n");
      out.write("\t\t$('#a_search').click(function() {\r\n");
      out.write("\t\t\tvar title = $(\"input[name='title']\").val();\r\n");
      out.write("\t\t\tvar sellPoint = $(\"input[name='sellPoint']\").val();\r\n");
      out.write("\t\t\t$('#itemList').datagrid({\r\n");
      out.write("\t\t\t\turl : url_searchModelControllerItem,\r\n");
      out.write("\t\t\t\tpagination : true,\r\n");
      out.write("\t\t\t\tpageSize : 30,\r\n");
      out.write("\t\t\t\ttoolbar : toolAdminBar,\r\n");
      out.write("\t\t\t\tfitColumns : false,\r\n");
      out.write("\t\t\t\tclosable : true,\r\n");
      out.write("\t\t\t\tcheckOnSelect : true,\r\n");
      out.write("\t\t\t\tborder : true,\r\n");
      out.write("\t\t\t\trownumbers : true,\r\n");
      out.write("\t\t\t\tctrlSelect : true,\r\n");
      out.write("\t\t\t\tcache:false,\r\n");
      out.write("\t\t\t\tqueryParams : {\r\n");
      out.write("\t\t\t\t\ttitle : title,\r\n");
      out.write("\t\t\t\t\tsellPoint : sellPoint\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\tfunction initDataGrid(){\r\n");
      out.write("\t\tgridItem = $('#itemList').datagrid({\r\n");
      out.write("\t\t\turl : url_searchModelControllerItem,\r\n");
      out.write("\t\t\tpagination : true,\r\n");
      out.write("\t\t\tpageSize : 30,\r\n");
      out.write("\t\t\ttoolbar : toolAdminBar,\r\n");
      out.write("\t\t\tfitColumns : false,\r\n");
      out.write("\t\t\tclosable : true,\r\n");
      out.write("\t\t\tcheckOnSelect : true,\r\n");
      out.write("\t\t\tborder : true,\r\n");
      out.write("\t\t\trownumbers : true,\r\n");
      out.write("\t\t\tctrlSelect : true,\r\n");
      out.write("\t\t\tcache:false\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction getSelectionsIds() {\r\n");
      out.write("\t\tvar itemList = $(\"#itemList\");\r\n");
      out.write("\t\tvar sels = itemList.datagrid(\"getSelections\");\r\n");
      out.write("\t\tvar ids = [];\r\n");
      out.write("\t\tfor ( var i in sels) {\r\n");
      out.write("\t\t\tids.push(sels[i].id);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tids = ids.join(\",\");\r\n");
      out.write("\t\treturn ids;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t/* 新增商品 */\r\n");
      out.write("\tfunction itemAdd() {\r\n");
      out.write("\t\t$(\".tree-title:contains('新增商品')\").parent().click();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\tfunction getCnameVal(data){\r\n");
      out.write("\t\t//查询类目名称\r\n");
      out.write("\t\tvar cnameVal = \"\";\r\n");
      out.write("\t\t$.getJSON('/item/cat/cname/'+ data.cid, function(_data) {\r\n");
      out.write("\t\t\tif (_data.status == 200) {\r\n");
      out.write("\t\t\t\tcnameVal = _data.data;\r\n");
      out.write("\t\t\t\treturn cnameVal;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t/* 编辑商品 */\r\n");
      out.write("\tfunction itemEdit() {\r\n");
      out.write("\t\tvar ids = getSelectionsIds();\r\n");
      out.write("\t\tif (ids.length == 0) {\r\n");
      out.write("\t\t\t$.messager.alert('提示', '必须选择一个商品才能编辑!');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (ids.indexOf(',') > 0) {\r\n");
      out.write("\t\t\t$.messager.alert('提示', '只能选择一个商品进行编辑!');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t$(\"#itemEditWindow\")\r\n");
      out.write("\t\t\t\t.window(\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\tonLoad : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t//回显数据\r\n");
      out.write("\t\t\t\t\t\t\t\tvar data = $(\"#itemList\").datagrid(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\"getSelections\")[0];\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tdata.priceView = TAOTAO.formatPrice(data.price);\r\n");
      out.write("\t\t\t\t\t\t\t\t$(\"#itemeEditForm\").form(\"load\", data);\r\n");
      out.write("\t\t\t\t\t\t\t\t// 加载商品描述\r\n");
      out.write("\t\t\t\t\t\t\t\t$.getJSON('/item/desc/'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t+ data.id, function(_data) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif (_data.status == 200) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\titemEditEditor\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t.html(_data.data.itemDesc);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t//加载商品规格\r\n");
      out.write("\t\t\t\t\t\t\t\t$\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t.getJSON(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t'/item/param/item/query/'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ data.id,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tfunction(_data) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tif (_data\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t&& _data.status == 200\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t&& _data.data\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t&& _data.data.paramData) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t$(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"#itemeEditForm .params\")\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.show();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t$(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"#itemeEditForm [name=itemParams]\")\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.val(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t_data.data.paramData);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t$(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"#itemeEditForm [name=itemParamId]\")\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.val(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t_data.data.id);\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t//回显商品规格\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvar paramData = JSON\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.parse(_data.data.paramData);\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvar html = \"<ul>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfor ( var i in paramData) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tvar pd = paramData[i];\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\thtml += \"<li><table>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\thtml += \"<tr><td colspan=\\\"2\\\" class=\\\"group\\\">\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ pd.group\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \"</td></tr>\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfor ( var j in pd.params) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tvar ps = pd.params[j];\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\thtml += \"<tr><td class=\\\"param\\\"><span>\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ ps.k\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ \"</span>: </td><td><input autocomplete=\\\"off\\\" type=\\\"text\\\" value='\" + ps.v + \"'/></td></tr>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\thtml += \"</li></table>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\thtml += \"</ul>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t$(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"#itemeEditForm .params td\")\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.eq(1).html(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\thtml);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\tTAOTAO.init({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\"pics\" : data.image,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\"cid\" : data.cid,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\"cname\":data.cname,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfun : function(node) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tTAOTAO.changeItemParam(node,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\"itemeEditForm\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}).window(\"open\");\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t/* 删除商品 */\r\n");
      out.write("\tfunction itemDel() {\r\n");
      out.write("\t\tvar ids = getSelectionsIds();\r\n");
      out.write("\t\tif (ids.length == 0) {\r\n");
      out.write("\t\t\t$.messager.alert('提示', '未选中商品!');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的商品吗？', function(r) {\r\n");
      out.write("\t\t\tif (r) {\r\n");
      out.write("\t\t\t\tvar params = {\r\n");
      out.write("\t\t\t\t\t\"ids\" : ids\r\n");
      out.write("\t\t\t\t};\r\n");
      out.write("\t\t\t\t$.post(\"/item/delete\", params, function(data) {\r\n");
      out.write("\t\t\t\t\tif (data.status == 200) {\r\n");
      out.write("\t\t\t\t\t\t$.messager.alert('提示', '删除商品成功!', undefined,\r\n");
      out.write("\t\t\t\t\t\t\t\tfunction() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#itemList\").datagrid(\"reload\");\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t/* 下架商品 */\r\n");
      out.write("\tfunction itemDown() {\r\n");
      out.write("\t\tvar ids = getSelectionsIds();\r\n");
      out.write("\t\tif (ids.length == 0) {\r\n");
      out.write("\t\t\t$.messager.alert('提示', '未选中商品!');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.messager.confirm('确认', '确定下架ID为 ' + ids + ' 的商品吗？', function(r) {\r\n");
      out.write("\t\t\tif (r) {\r\n");
      out.write("\t\t\t\tvar params = {\r\n");
      out.write("\t\t\t\t\t\"ids\" : ids\r\n");
      out.write("\t\t\t\t};\r\n");
      out.write("\t\t\t\t$.post(\"item/change/2\", params, function(data) {\r\n");
      out.write("\t\t\t\t\tif (data.status == 200) {\r\n");
      out.write("\t\t\t\t\t\t$.messager.alert('提示', '下架商品成功!', undefined,\r\n");
      out.write("\t\t\t\t\t\t\t\tfunction() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#itemList\").datagrid(\"reload\");\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t/* 上架商品 */\r\n");
      out.write("\tfunction itemUp() {\r\n");
      out.write("\t\tvar ids = getSelectionsIds();\r\n");
      out.write("\t\tif (ids.length == 0) {\r\n");
      out.write("\t\t\t$.messager.alert('提示', '未选中商品!');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.messager.confirm('确认', '确定上架ID为 ' + ids + ' 的商品吗？', function(r) {\r\n");
      out.write("\t\t\tif (r) {\r\n");
      out.write("\t\t\t\tvar params = {\r\n");
      out.write("\t\t\t\t\t\"ids\" : ids\r\n");
      out.write("\t\t\t\t};\r\n");
      out.write("\t\t\t\t$.post(\"item/change/1\", params, function(data) {\r\n");
      out.write("\t\t\t\t\tif (data.status == 200) {\r\n");
      out.write("\t\t\t\t\t\t$.messager.alert('提示', '上架商品成功!', undefined,\r\n");
      out.write("\t\t\t\t\t\t\t\tfunction() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#itemList\").datagrid(\"reload\");\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/jsp/item-list.jsp(35,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${null != USER.username }", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t");
        if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write('	');
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /WEB-INF/jsp/item-list.jsp(36,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${'zhaoyuan' == USER.username}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t<table id=\"itemList\" title=\"商品列表\">\r\n");
        out.write("\t\t\t\t<thead>\r\n");
        out.write("\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t<th data-options=\"field:'ck',checkbox:true\"></th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"id\" data-options=\"sortable:true,align:'center'\">商品ID</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"title\" data-options=\"width:250,align:'center'\">商品标题</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"cid\" data-options=\"width:100,align:'center'\">叶子类目</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"cname\" data-options=\"width:100,align:'center'\">类目名称</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"sellPoint\" data-options=\"width:200\">卖点</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"price\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:70,align:'center',formatter:TAOTAO.formatPrice\">价格\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"num\" data-options=\"width:70,align:'center'\">库存数量</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"barcode\" data-options=\"width:100,align:'center'\">条形码</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"status\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:60,align:'center',formatter:TAOTAO.formatItemStatus\">状态\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"created\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:130,align:'center',formatter:TAOTAO.formatDateTime\">创建日期\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"updated\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:130,align:'center',formatter:TAOTAO.formatDateTime\">更新日期\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t</thead>\r\n");
        out.write("\t\t\t</table>\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /WEB-INF/jsp/item-list.jsp(67,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${null != USER.username }", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t");
        if (_jspx_meth_c_005fif_005f3(_jspx_th_c_005fif_005f2, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write('	');
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    // /WEB-INF/jsp/item-list.jsp(68,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${'zhaoyuan' != USER.username}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t<table class=\"easyui-datagrid\" id=\"itemList\" title=\"商品列表\">\r\n");
        out.write("\t\t\t\t<thead>\r\n");
        out.write("\t\t\t\t\t<tr style=\"text-align: center;\">\r\n");
        out.write("\t\t\t\t\t\t<th data-options=\"field:'ck',checkbox:true\"></th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"id\" data-options=\"width:60,align:'center'\">商品ID</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"title\" data-options=\"width:250,align:'center'\">商品标题</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"cid\" data-options=\"width:100,align:'center'\">叶子类目</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"cname\" data-options=\"width:100,align:'center'\">类目名称</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"sellPoint\" data-options=\"width:200,align:'center'\">卖点</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"price\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:70,formatter:TAOTAO.formatPrice,align:'center'\">价格\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"num\" data-options=\"width:70,align:'center'\">库存数量</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"barcode\" data-options=\"width:100,align:'center'\">条形码</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"status\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:60,align:'center',formatter:TAOTAO.formatItemStatus\">状态\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"created\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:130,align:'center',formatter:TAOTAO.formatDateTime\">创建日期\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t\t<th field=\"updated\"\r\n");
        out.write("\t\t\t\t\t\t\tdata-options=\"width:130,align:'center',formatter:TAOTAO.formatDateTime\">更新日期\r\n");
        out.write("\t\t\t\t\t\t</th>\r\n");
        out.write("\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t</thead>\r\n");
        out.write("\t\t\t</table>\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }
}
