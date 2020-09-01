<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	    
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
	width:800px;
	height:500px;
	background:black;
	color:white;
	margin-top:50px;
	margin-left:auto;
	margin-right:auto;
	}
	table{
		border:1px solid white;
		text-align:center;
	}
	.table-area{
		width:650px;
		height:350px;
		margin:0 auto;
	}
	.search-area{
		width:650px;
		margin:0 auto;
	}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항</h2>
		<div class="table-area">
			<table align="center" id="listArea">
				<tr>
					<th width="100px">글번호</th>
					<th width="100px">글제목</th>
					<th width="100px">작성자</th>
					<th>조회수</th>
					<th width="100px">작성일</th>
				</tr>
				<c:forEach var="n" items="${ requestScope.list }" >
				<tr>
					<td><c:out value="${ n.nno }"/></td>
					<td><c:out value="${ n.nTitle }"/></td>
					<td><c:out value="${ n.nickName }"/></td>
					<td><c:out value="${ n.nCount }"/></td>
					<td><c:out value="${ n.nDate }"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="search-area" align="center">
			<select id="searchCondition" name="searchCondition">
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search">
			<button type="submit">검색하기</button>
			<c:if test="${ !empty sessionScope.loginUser and sessionScope.loginUser.userId.equals('admin') }">
				<button onclick="location.href='views/notice/noticeInsertForm.jsp'">작성하기</button>
			</c:if>
		</div>
		
	</div>
	<script>
		$(function(){
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"darkgray","cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"black"});
			}).click(function(){
				var num = $(this).parent().children().eq(0).text();
				
				console.log(num);
				
				location.href = "${applicationScope.contextPath}/selectOne.no?num="+num;
			});
		});
	
	</script>
</body>
</html>