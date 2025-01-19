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
			<div id="content">
				<div class="blog-content">
					<h4>${postvo.title}</h4>
					<p>
						${postvo.contents}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach var="post" items="${postlistvo}">
				        <li>
				            <a href="${pageContext.request.contextPath}/${authUser.id}/${post.category_id}/${post.id }">
				                ${post.title}
				            </a>
				            <span>${post.reg_date}</span>
				        </li>
				    </c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>${categoryvo.name}</h2>
				<c:forEach var="category" items="${categoryvolist}">
			        <li>
			            <a href="${pageContext.request.contextPath}/${authUser.id}/${category.id }">
			                ${category.name}
			            </a>
			        </li>
			    </c:forEach>
		</div>
		
		<div id="footer">
			<p>
				<strong>${blogvo.title}</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>