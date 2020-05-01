<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
</head>
<body style="background-image: url('${applicationScopt.appRoot}/images/bgLogin.jpg');">
	<div class="easyui-dialog" title="管理员登录"
		data-options="closable:false,draggable:false"
		style="width: 400px; height: 300px; padding: 10px;">
		<form name="loginform" id="loginFormId" method="post" target="_top"
			action="login/submit.do" class="form-singin">
			<div style="margin-left: 50px; margin-top: 50px;">
				<div style="margin-bottom: 20px;">
					<div>
						用户名: <input name="username" class="easyui-textbox"
							data-options="required:true" style="width: 200px; height: 32px"
							value="admin" />
					</div>
				</div>
				<div style="margin-bottom: 20px">
					<div>
						密&nbsp;&nbsp;&nbsp;码: <input name="password"
							class="easyui-textbox" type="password"
							style="width: 200px; height: 32px" data-options="" value="admin" />
					</div>
				</div>
				<div>
					<a id="login" class="easyui-linkbutton" iconCls="icon-ok"
						style="width: 100px; height: 32px; margin-left: 50px">登录</a>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
    	$("#login").click(function(){
    		var username_p = $("[name=username]").val();
    		var password_p = $("[name=password]").val();
    		
    		/* if(username!="admin" || password!="admin"){
    			$.messager.alert('错误',"用户名密码不正确！");
    			return ;
    		}
    		window.location.href="/rest/page/index"; */
    		
    		$.ajax({
    			type:'POST',
    			url:'${applicationScope.appRoot}/login/submit',
    			data:{'username':username_p,'password':password_p},
    			success:function(result){
    				
    			}
    		});
    	});
    </script>
</body>
</html>