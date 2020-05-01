<!-- 后台管理系统的首页 -->
<%-- ${USER.username}==zhaoyaun时，为超级管理员，右侧显示所有菜单。
否则只显示'新增商品','查询商品','规格参数'菜单。
design on 2019-11-17 18:02:28 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="${applicationScopt.appRoot}/images/tao.jpg"/>
    <title>淘淘商城后台管理系统</title>
    <link rel="stylesheet" type="text/css"
          href="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/themes/icon.css"/>
    <link rel="stylesheet" type="text/css"
          href="${applicationScopt.appRoot}/css/taotao.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/mycss/index.css">
    <script type="text/javascript"
            src="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/validate/jquery.validate.exp.js"></script>
    <script type="text/javascript"
            src="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="${applicationScopt.appRoot}/js/common.js"></script>
    <style type="text/css">
        .content {
            padding: 10px 10px 10px 10px;
        }

        .userTxt {
            height: 15%;
            float: right;
        }

        .userTxt p {
            margin-top: 1%;
            margin-right: 50px;
        }

        .welcomeTxt {
            width: 50%;
            height: 15%;
        }

        #indexFont {
            font-size: 20px;
            font-weight: bold;
            padding-left: 50%;
            text-align: center;
            margin-top: 2%;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'west',title:'菜单',split:true"
     style="width: 180px;">
    <ul id="menu" class="easyui-tree"
        style="margin-top: 10px; margin-left: 5px;">
        <li><span>商品管理</span>
            <ul>
                <li data-options="attributes:{'url':'item-add'}">新增商品</li>
                <li data-options="attributes:{'url':'item-list'}">查询商品</li>
                <li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
                <li data-options="attributes:{'url':'item-properties-list'}">商品属性</li>
                <c:if test="${null != USER.username}">
                    <c:if test="${'zhaoyuan' == USER.username}">
                        <li data-options="attributes:{'url':'item-category'}">分类管理</li>
                    </c:if>
                </c:if>
            </ul>
        </li>
        <c:if test="${null != USER.username && 'zhaoyuan' == USER.username}">
            <li><span>网站内容管理</span>
                <ul>
                    <li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
                    <li data-options="attributes:{'url':'content'}">内容管理</li>
                </ul>
            </li>
            <li><span>商品索引管理</span>
                <ul>
                    <li data-options="attributes:{'url':'import-index'}">商品数据导入索引库</li>
                </ul>
            </li>
        </c:if>
    </ul>
</div>
<div data-options="region:'center',title:''">
    <div id="tabs" class="easyui-tabs">
        <div title="首页" style="padding: 20px;">
            <div class="userTxt">
                <p>
                    欢迎你，${USER.username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
                        href="javascript:void(0);" style="float: right">退出</a>
                </p>
            </div>
            <div class="welcomeTxt">
                <p id="indexFont">欢迎淘淘商城后台管理系统</p>
            </div>
            <%--<div class="solid"></div>--%>
            <div class="mainBox">
                <ul class="mainBox_toporbuttom">
                    <li><div class="mainBox_toporbuttom_left" id="itemCh"></div></li>
                    <li><div class="mainBox_toporbuttom_right" id="userCh"></div></li>
                </ul>
                <ul class="mainBox_toporbuttom">
                    <li><div class="mainBox_toporbuttom_left"></div></li>
                    <li><div class="mainBox_toporbuttom_right"></div></li>
                </ul>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/theme/dark.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myjs/index.js"></script>

<script type="text/javascript">
    $(function () {
        $('#menu').tree({
            onClick: function (node) {
                if ($('#menu').tree("isLeaf", node.target)) {
                    var tabs = $("#tabs");
                    var tab = tabs.tabs("getTab", node.text);
                    if (tab) {
                        tabs.tabs("select", node.text);
                    } else {
                        tabs.tabs('add', {
                            title: node.text,
                            href: node.attributes.url,
                            closable: true,
                            bodyCls: "content"
                        });
                    }
                }
            }
        });

        /**
         * @date 2019-11-16 16:41:15
         * 退出
         **/
        $('.userTxt a').click(function () {
            //$('.userTxt a').attr('href','javascript:void(0);');
            $.ajax({
                type: 'POST',
                url: '${applicationScopt.appRoot}/login/logout',
                success: function (result) {
                    /* var managerUrl = result.MANAGER_WEB_URL;
                    alert(managerUrl);
                    window.location.href = managerUrl+"/login"; */
                    console.info(result);
                    if (200 == result.status) {
                        //$.messager.alert('提示',"session已清除");
                        var managerUrl = result.data;
                        window.location.href = managerUrl + "/login";
                        return;
                    }
                    if (500 == result.status) {
                        $.messager.alert('错误', result.msg);
                    }
                }
            });
        });
    });




</script>
</body>
</html>