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
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogvo.title}</h1>
			<ul class="menu">
				<c:choose>
					<c:when test="${empty authUser }" >
						<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
						<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
					</c:when>
					<c:otherwise>			
						<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin">블로그 관리</a></li>
					</c:otherwise>
				</c:choose>	
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				<form action="${pageContext.request.contextPath }/${authUser.id}/admin" method="post" enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${blogvo.title }"></td>
			      		</tr>
						<tr>
						    <td class="t">로고이미지</td>
						    <td>  
							   	<c:choose>
							        <c:when test="${not empty blogvo.profile}">
							            <img src="${pageContext.request.contextPath}${blogvo.profile}">
							            <input type="hidden" name="profile" value="${blogvo.profile}">
							        </c:when>
							        <c:otherwise>
							            <img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
							            <input type="hidden" name="profile" value="/assets/images/spring-logo.jpg">
							        </c:otherwise>
							    </c:choose>
					    	</td>  
						</tr>
						<tr>
							<td class="t">&nbsp;</td>    
						    <td>
						        <input type="file" name="logo-file" value="${blogvo.profile}">
						    </td>
						</tr>
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>