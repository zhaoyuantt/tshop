<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- <base href="<PF:basePath/>" /> --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页</title>
<link rel="shortcut icon"
	href="${applicationScopt.appRoot}/images/tao.jpg" />
<link rel="stylesheet" type="text/css"
	href="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${applicationScopt.appRoot}/css/login.css" />
<script type="text/javascript"
	src="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${applicationScopt.appRoot}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="login_bg1">
	<div class="login_box1">
		<img src="${applicationScopt.appRoot}/images/login_head.png"
			class="login_head" />
		<div class="login_title1">淘淘商城</div>
		<form name="loginform" id="loginFormId" method="post" target="_top"
			action="" class="form-singin">
			<table class="login_table1">
				<tr>
					<td><input class="login_ipt1" id="name" type="text"
						name="username" /></td>
				</tr>
				<tr>
					<td><input class="login_ipt1" id="passwordId" type="password"
						name="password" /></td>
				</tr>
				<tr>
					<td><input class="login_but1" type="button" id="loginButton"
						value="登 录" /></td>
				</tr>
				<tr>
					<td
						style="height: 20px; margin-top: -24px; text-align: center; color: #ff0000;"><div
							id="errorMessage">${message}</div></td>
				</tr>
			</table>
		</form>
		<!-- 2018-07-11 测试 -->
		<%-- <%= request.getParameter("name") %> --%>
	</div>
</body>
<script type="text/javascript">
	var mes = '${message}';
	$(function() {
		var msg = document.getElementById("errorMessage");
		if (mes != null && mes.length > 0) {
			msg.innerHTML = mes;
			$(msg).show();
		}
		$('#loginButton').bind('click', function() {
			var password = $('#passwordId').val();
			var name = $('#name').val();
			if (name == null || name.length <= 0) {
				msg.innerHTML = "用户名不能为空";
				$(msg).show();
				return false;
			}
			if (password == null || password.length <= 0) {
				msg.innerHTML = "密码不能为空";
				$(msg).show();
				return false;
			}

			/* if (password != null && password != '') {
				$('#passwordId').val(hex_md5(password + name));
			} */
			//$('#loginFormId').submit();
			$.ajax({
				type : 'POST',
				url : '${applicationScopt.appRoot}/login/submit',
				data : $('#loginFormId').serialize(),
				success : function(result) {
					console.info(result);
					//session没有过期，很有可能是用户重复登录
					/* if (result.SESSIONISEXPIRE) {
						window.open("http://127.0.0.1:8081/loginfail");
					} else {
						//用户名密码错误
						if (400 == result.STAT) {
							msg.innerHTML = result.MSG;
							$(msg).show();
							return false;
						} else {
							//验证成功
							window.location.href = "http://127.0.0.1:8081";
						}
					} */

					//session没有过期
					if (result.status == 401) {
						var managerUrl = result.data;
						window.open(managerUrl + "/loginfail");
					}
					//登录成功
					if (result.status == 200) {
						//alert("登录成功");
						var managerUrl = result.data;
						window.location.href = managerUrl;
					}
					//登录失败
					if (result.status == 400) {
						msg.innerHTML = result.msg;
						$(msg).show();
						return false;
					}
				}
			});

			//$('#loginButton').hide();
		});
		$('#clearButton').bind('click', function() {
			$('#passwordId').attr('value', '');
			$('#name').attr('value', '');
		});
		$('#passwordId').keydown(function(e) {
			if (e.keyCode == 13) {
				$('#loginButton').click();
			}
		});
	})
</script>
</html>