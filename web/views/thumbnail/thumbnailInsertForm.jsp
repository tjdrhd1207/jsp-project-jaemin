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
		height:650px;
		background:black;
		color:white;
		margin-top:50px;
		margin-left:auto;
		margin-right:auto;
	}
	table{
		border:1px solid white;
	}
	.btn-area{
		width:150px;
		margin:0 auto;
	}
	#titleImgArea{
		width:350px;
		height:200px;
		border:2px dashed darkgray;
		text-align:center;
		display:table-cell;
		vertical-align:middle;
	}
	
#titleImgArea:hover, #contentImgArea:hover,
#contentImgArea2:hover, #contentImgArea3:hover{
	cursor:pointer;
}	
#contentImgArea1, #contentImgArea2. #contentImgArea3{
	width:150px;
	height:100px;
	border:2px dashed darkgray;
	text-align:center;
	display:table-cell;
	vertical-align:middle;

	}	
	
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<c:if test="${!empty sessionScope.loginUser }">
	<div class="outer">
	<br>
	<h2 align="center">사진게시판 작성</h2>
	<form action="${applicationScope.contextPath }/insert.tn" method="post" encType="multipart/form-data">
		<div class="insert-area">
			<table align="center">
				<tr>
					<td width="100px">제목</td>
					<td colspan="3"><input type="text" size="45" name="title"></td>
				</tr>
				<tr>
					<td>대표이미지</td>
					<td colspan="3">
						<div id="titleImgArea">
							<img id="titleImg" width="350" height="200">
						</div>
					</td>
				</tr>
				<tr>
					<td>내용 사진</td>
					<td>
						<div id="contentImgArea1">
							<img id="contentImg1" width="120" height="100">
						</div>
					</td>
					<td>
						<div id="contentImgArea2">
							<img id="contentImg2" width="120" height="100">
						</div>
					</td>
					<td>
						<div id="contentImgArea3">
							<img id="contentImg3" width="120" height="100">
						</div>
					</td>
				</tr>
				<tr>
					<td width="100px">사진 메모</td>
					<td colspan="3">
					<textarea name="content" rows="5" cols="50" style="resize:none;"></textarea>
					</td>
				</tr>	
			</table>
			<div id="fileArea">
				<input type="file" id="thumbnailImg1" name="thumbnailImg1" onchange="loadImg(this,1)">
				<input type="file" id="thumbnailImg2" name="thumbnailImg2" onchange="loadImg(this,2)">
				<input type="file" id="thumbnailImg3" name="thumbnailImg3" onchange="loadImg(this,3)">
				<input type="file" id="thumbnailImg4" name="thumbnailImg4" onchange="loadImg(this,4)">
				
			</div>
		</div>
		<br>
		<div class="btn-area">
			<button type="reset">취소하기</button>
			<button type="submit">작성완료</button>
		</div>
	</form>
	</div>
	</c:if>	
	
	<c:if test="${empty sessionScope.loginUser }">
		<c:set var="msg" value="잘못된 경로로 접근하셨습니다." scope="request"></c:set>
		<jsp:forward page="../common/errorPage.jsp"/>	
	</c:if>
	<script>
		$(function(){
			$("#fileArea").hide();
			
			$("#titleImgArea").click(function(){
					$("#thumbnailImg1").click();
			});
			$("#contentImgArea1").click(function(){
				$("#thumbnailImg2").click();
			});
			$("#contentImgArea2").click(function(){
				$("#thumbnailImg3").click();
			});
			$("#contentImgArea3").click(function(){
			    $("#thumbnailImg4").click();
			});	
		});
		
		function loadImg(value,num){
			if(value.files && value.files[0]){
				var reader = new FileReader();
				
				reader.onload = function(e){
												//사진을 문자열로 바꿔서 저장해놈
					switch(num){
					case 1 : $("#titleImg").attr("src", e.target.result); break;
					case 2  : $("#contentImg1").attr("src", e.target.result); break;
					case 3 : $("#contentImg2").attr("src", e.target.result); break;
					case 4 : $("#contentImg3").attr("src", e.target.result); break;
					}						
												
				
				}
				reader.readAsDataURL(value.files[0]);
			}	
		}
	
	</script>
</body>
</html>