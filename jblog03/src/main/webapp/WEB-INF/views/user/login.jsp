<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<c:choose>
				<c:when test="${empty authUser }" >
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
					<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
					<li><a href="${pageContext.request.contextPath }">내블로그</a></li>
				</c:when>
				<c:otherwise>			
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}">내블로그</a></li>
				</c:otherwise>
			</c:choose>	
		</ul>
		<form id="login-form" name="loginform" method="post" action="${pageContext.request.contextPath }/user/auth">
			<label class="block-label" for="id">아이디</label> 
			<input id="id" name="id" type="text" value="${id }"> 
			<label class="block-label">패스워드</label> 
			<input name="password" type="password" value="">
			<c:if test='${"fail" == result}'>
				<p>로그인이 실패 했습니다.</p>
			</c:if>
			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
