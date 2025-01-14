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
			<ul>
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/jblog03/admin/default">블로그 관리</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postvo[0].title}</h4>
					<p>
						${postvo[0].contents}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach var="post" items="${postlistvo}">
				        <li>
				            <a href="${pageContext.request.contextPath}/jblog03/${post.category_id}/${post.id }">
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
				<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
			</div>
		</div>

		<div id="navigation">
			<h2>${categoryvo[categoryvoindex].name}</h2>
				<c:forEach var="category" items="${categoryvo}">
			        <li>
			            <a href="${pageContext.request.contextPath}/jblog03/${category.blog_id}/${category.id }">
			                ${category.name}
			            </a>
			        </li>
			    </c:forEach>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>