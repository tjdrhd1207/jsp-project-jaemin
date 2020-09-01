<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
	width:1000px;
	height:700px;
	background:black;
	color:white;
	margin-top:50px;
	margin-left:50px;
	margin-right:auto;

	}
	.thumbnail-area{
		width:760px;
		height:550px;
		margin:0 auto;
	}
	.search-area{
		width:420px;
		margin:0 auto;
	}
	
	
	
</style>

</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<div class="outer">
		<br>
		<h2 align="center">사진게시판</h2>
		
		<div class="thumbnail-area">
		
		</div>
	
		<div class="search-area">
			<select id="searchCondition" name="searchCondition">
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search">
			<button type="submit">검색하기</button>
			<c:if test = "${! empty sessionScope.loginUser }">
				<button onclick="location.href='${applicationScope.contextPath}/views/thumbnail/thumbnailInsertForm.jsp'">저장하기</button>
			
			</c:if>
		</div>
	
	</div>

</body>
</html>