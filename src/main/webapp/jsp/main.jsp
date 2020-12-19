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
<title>主页</title>
</head>
<body>
	<c:if test="${empty loginUsers}">
		尚未<a href="<%=basePath %>/jsp/login.jsp">[登入]</a>
	</c:if>
	<c:if test="${!empty loginUsers}">
		<div>
			<form action="<%=basePath %>/books/toListBooks.do" method="post">
				图书分类：
				<select name="booktype">
					<option value="-1">--请选择--</option>
					<c:forEach items="${types }" var="type">
						<option value="${type.bookTypeId }"
							<c:if test="${bookinfoQueryPar.bookTypeId == type.bookTypeId }">
								selected="selected"
							</c:if>
						>
							${type.bookTypeName }
						</option>
					</c:forEach>
				</select>
				&nbsp;
				图书名称：
				<input type="text" name="bookname" value="${bookinfoQueryPar.bookName}"/>
				&nbsp;
				是否借阅：
				<select name="isBorrow">
					<option value="-1">--请选择--</option>
					<option value="1"
						<c:if test="${bookinfoQueryPar.isBorrow == 1 }">
							selected="selected"
						</c:if>
					>是</option>
					<option value="0" 
						<c:if test="${bookinfoQueryPar.isBorrow == 0 }">
							selected="selected"
						</c:if>
					>否</option>
				</select>
				&nbsp;
				<input type="submit" value="查询"/>
			</form>
		</div>
		<div>
			当前用户: ${loginUsers.userCode} 
			<a href="<%=basePath %>/users/logout.do">退出</a>
		</div>
		<div>
			<table style="width: 100%;" border="1">
				<tr>
					<td>编号</td>
					<td>分类</td>
					<td>名称</td>
					<td>作者</td>
					<td>出版社</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.bookCode }</td>
						<td>${book.bookType.bookTypeName }</td>
						<td>${book.bookName }</td>
						<td>${book.bookAuthor }</td>
						<td>${book.publishPress }</td>
						<td>
							<!-- 原则上这个请求用AJAX非常方便 -->
							<c:if test="${book.isBorrow == 0 }">
								<span style="color:green;">未借阅</span>
								<a href="<%=basePath %>/books/updateBooks.do?bookId=${book.bookId }&isBorrowUpdate=1&booktype=${bookinfoQueryPar.bookTypeId}&bookname=${bookinfoQueryPar.bookName}&isBorrow=${bookinfoQueryPar.isBorrow}">[借阅]</a>
							</c:if>
							<c:if test="${book.isBorrow == 1 }">
								<span style="color:red;">已借阅</span>
								<a href="<%=basePath %>/books/updateBooks.do?bookId=${book.bookId }&isBorrowUpdate=0&booktype=${bookinfoQueryPar.bookTypeId}&bookname=${bookinfoQueryPar.bookName}&isBorrow=${bookinfoQueryPar.isBorrow}">[归还]</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<a href="">首页</a>
			<a href="">上一页</a>
			当前 页/总计 页
			<a href="">下一页</a>
			<a href="">尾页</a>
		</div>
	</c:if>
</body>
</html>








