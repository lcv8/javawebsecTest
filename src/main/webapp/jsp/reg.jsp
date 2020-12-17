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
			//url 参数
			$.post("<%=basePath %>/users/regiestCheckUsernameAjax.do",
					{'username':username},
						function(data){
								console.log(data);
							});
		})
	});
</script>
</head>
<body>
	${message }
	<form action="<%=basePath %>/users/regiest.do" method="post">
		<table>
			<tr>
				<td>
					账号：
				</td>
				<td>
					<input id="username" type="text" name="username" value="${users.userCode }">*
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
				<td>
					重复密码：
				</td>
				<td>
					<input type="text" >
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