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
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
function confirmDelete(postCount) {
    if (postCount > 0) {
        alert('삭제가 불가합니다. 먼저 해당 카테고리의 글을 삭제해주세요.');
        return false;  // 삭제 진행 중단
    }
    return confirm('정말 삭제하시겠습니까?');  // 글이 없을 때만 실제 삭제 확인
}
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogvo.title }</h1>
			<ul>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin">블로그 관리</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin">기본설정</a></li>
					<li><a href="">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      	<c:set var="count" value="${fn:length(categoryvo) }" />
				<c:forEach var="category" items="${categoryvo}" varStatus="status">
					<tr>
				        <td>${count - status.index}</td>
				        <td>${category.name}</td>
				        <td>${category.count}</td>
				        <td>${category.description}</td>
				    
 						<td>
                           <form action="${pageContext.request.contextPath}/${authUser.id}/admin/category/delete" 
                                  method="post" 
                                  onsubmit="return confirmDelete(${category.count});">
                                <input type="hidden" name="category_id" value="${category.id}">
                                <button type="submit" 
                                        style="border: none; background: none; padding: 0; cursor: pointer;">
                                    <img src="${pageContext.request.contextPath}/assets/images/delete.jpg" 
                                         alt="삭제" title="카테고리 삭제">
                                </button>
                            </form>
                        </td>
				    </tr>
			    </c:forEach>		  
				</table>
				<form action="${pageContext.request.contextPath}/${authUser.id}/admin/category" method="post">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
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