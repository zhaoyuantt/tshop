<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录失败</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background: #d3d7d7;
	background-image: url('../../../images/bgLogin.jpg');
}

.main{
	/* margin:0 auto;
	border:1px solid #000; */
	margin-top:20%;
	width:100%;
	height:100%;
}

.failTxt{
	text-align: center;
}
</style>
</head>
<body>
	<div class="main">
		<h1 class = "failTxt">登录失败，你的Session没有过期哦☺</h1>
	</div>
</body>
</html>