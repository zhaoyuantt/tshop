<html>
<head>
<title>测试页面</title>
</head>
<body>
	基本信息<br>
	<table border="1">
		<tr>
			<td>index</td>
			<td>id</td>
			<td>name</td>
			<td>address</td>
			<td>birthday</td>
			<td>work</td>
		</tr>
		<tr>
		<#list goddessList as goddess>
		<#if goddess_index%2 == 0>
		<tr bgcolor="red">
		<#else>
		<tr bgcolor="blue">
		</#if>
			<td>${goddess_index}</td>
			<td>${goddess.id}</td>
			<td>${goddess.name}</td>
			<td>${goddess.address}</td>
			<td>${goddess.date?string("yyyy-MM-dd hh:mm:ss")}</td>
			<td>${goddess.work!"银行"}</td>
		</tr>
		</#list>
	</table>
	<br>
</boby>
</html>