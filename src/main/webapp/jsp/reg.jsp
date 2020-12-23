<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() 
			+ "://" + request.getServerName() 
				+ ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta charset="UTF-8">
		<title>注册</title>
		<script type="text/javascript" src="<%=basePath %>/js/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#username").keyup(function(){
					var username = $(this).val();
					if(username==null || username=="" || username == undefined){
						$(".linemessage").empty();
						$(".linemessage").attr("data","0");
					}else{
						$.post("<%=basePath %>/users/regiestCheckUsernameAjax.do",//要提交的webapi的url
								{'username':username},//以json格式提交参数
									function(data){//ajax回调，返回后端的响应数据
											if( data=="true" ){
												$(".linemessage").text("当前用户名可以使用");
												$(".linemessage").css("color","green");
												$(".linemessage").attr("data","1");
											}else if(data == "false"){
												$(".linemessage").text("当前用户名已使用");
												$(".linemessage").css("color","red");
												$(".linemessage").attr("data","0");
											}
										});
					}
				});
				$("form").submit(function(){
					var username = $("#username").val();
					var usernamecheck = $(".linemessage").attr("data");
					var password = $("input[name='password']").val();
					var rpassword = $("#rpassword").val();
					var sex=$("select[name='sex']").find("option:selected").val();
					var email = $("input[name='email']").val();
					//前端验证
					if(username==null || username=="" || username == undefined){
						$(".bodymessage").text("用户名不可为空");
						$(".bodymessage").css("color","red");
						return false;
					}
					if(usernamecheck == "0"){
						$(".bodymessage").text("用户名尚未通过验证");
						$(".bodymessage").css("color","red");
						return false;
					}
					var usernamepatten = /^[A-Z]{1}[a-zA-Z0-9]{5,17}/;
					if(!usernamepatten.test(username)){
						$(".bodymessage").text("用户名格式错误");
						$(".bodymessage").css("color","red");
						return false;
					}
					if(password==null || password=="" || password == undefined){
						$(".bodymessage").text("密码不可为空");
						$(".bodymessage").css("color","red");
						return false;
					}
					var passwordpatten = /^[a-zA-Z]{1}[a-zA-Z0-9]{4,15}[0-9]{1}$/;
					if(!passwordpatten.test(password)){
						$(".bodymessage").text("密码格式错误");
						$(".bodymessage").css("color","red");
						return false;
					}
					if(password!=rpassword){
						$(".bodymessage").text("两次密码输入不一致");
						$(".bodymessage").css("color","red");
						return false;
					}
					if(sex == "-1"){
						$(".bodymessage").text("请选择性别");
						$(".bodymessage").css("color","red");
						return false;
					}
					if(email!=null && email!="" && email != undefined){
						var emailpatten = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
						if(!emailpatten.test(email)){
							$(".bodymessage").text("邮箱格式错误");
							$(".bodymessage").css("color","red");
							return false;
						}
						return false;
					}
					return false;
				});
			});
		</script>
	</head>
	<body>
		<div class="bodymessage">${message }</div>
		<form action="<%=basePath %>/users/regiest.do" method="post">
			<table>
				<tr>
					<td>
						账号：
					</td>
					<td>
						<input id="username" type="text" name="username" value="${users.userCode }">*
						<span class="linemessage" data="0">${message }</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						账号必须大写字母开头，字母加数字，6-18位
					</td>
				</tr>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<input type="text" name="password">*
					</td>
				</tr>
				<tr>
					<td colspan="2">
						密码必须包含字母和数字，字母开头，数字结尾，6-18位
					</td>
				</tr>
				<tr>
					<td>
						重复密码：
					</td>
					<td>
						<input id="rpassword" type="text" >
					</td>
				</tr>
				<tr>
					<td>
						性别：
					</td>
					<td>
						<select name="sex">
							<option value="-1">--请选择--</option>
							<option value="1" <c:if test="${users.gender == 1 }">selected="selected"</c:if>>男</option>
							<option value="0" <c:if test="${users.gender == 0 }">selected="selected"</c:if>>女</option>
						</select>*
					</td>
				</tr>
				<tr>
					<td>
						邮箱：
					</td>
					<td>
						<input type="text" name="email" value="${users.email }">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>