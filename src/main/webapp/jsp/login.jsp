<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<title>登入</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/login.css"/>
		<script type="text/javascript" src="<%=basePath %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/login.js"></script>
	</head>
	<body>
		<div class="body">
			<div>${message}</div>
			<form action="<%=basePath %>/users/login.do" method="post">
				<div class="title">
					图书借阅系统
				</div>
				<div class="middle">
					<div>
						登入账号：<input type="text" name="username" value="${users.userCode }"/>
					</div>
					<div>
						登入密码：<input type="text" name="password"/>
					</div>
				</div>
				<div class="bottom">
					<input type="submit" value="登入|Login"/>
					<a href="<%=basePath %>/jsp/reg.jsp">注册用户</a>
					<div class="clear"></div>
				</div>
			</form>
		</div>
	</body>
</html>