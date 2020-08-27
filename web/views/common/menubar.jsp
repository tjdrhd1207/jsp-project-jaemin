<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" scope="application"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<style>
   body {
      background:url("/jsp/resources/image/city1.PNG") no-repeat;
      background-size:cover;
   }
   .wrap {
      background:black;
      width:100%;
      height:50px;
   }
   .menu {
      background:black;
      color:white;
      text-align:center;
      display:table-cell;
      vertical-align:middle;
      width:150px;
      height:50px;
   }
   .nav {
      width:600px;
      margin: 0 auto;
   }
   .menu:hover {
      backgorund:darkgray;
      color:orangered;
      font-weight:bold;
      cursor:pointer;
   }
   .btns {
      align:center;
   }
   #loginBtn, #memberJoinBtn, #logoutBtn, #changeInfo {
      display:inline-block;
      text-align:center;
      background:orangered;
      color:white;
      height:25px;
      width:100px;
      border-radius:5px;
   }
   #memberJoinBtn, #changeInfo {
      background:yellowgreen;
   }
   #loginBtn:hover, #changeInfo:hover, #logoutBtn:hover, #memberJoinBtn:hover {
      cursor:pointer;
   }
   .login-area > form, #userInfo {
      float:right;
   }
</style>
</head>
<body>
   <h1 align="center">Welcome to the JSP world</h1>
   
   <!-- login-area starts  -->
   <div class="login-area">
      <c:if test="${empty sessionScope.loginUser }">
         <form id="loginForm" action="${ applicationScope.contextPath }/login.me" method="post">
            <table>
               <tr>
                  <td><label class="text">ID :</label></td>
                  <td><input type="text" name="userId"></td>
               </tr>
               <tr>
                  <td><label class="text">PWD :</label></td>
                  <td><input type="password" name="userPwd"></td>
               </tr>
            </table>
            <div class="btns" align="center">
               <div id="memberJoinBtn" onclick="memberJoin();">회원가입</div>
               <div id="loginBtn" onclick="login();">로그인</div>
            </div>
         </form>
      </c:if>
      <c:if test="${!empty sessionScope.loginUser }">
         <div id="userInfo">
            <label><c:out value="${sessionScope.loginUser.nickName }"/>님의 방문을 환영합니다.</label>
            <div class="btn" align="right">
               <div id="changeInfo" onclick="update();">정보수정</div>
               <div id="logoutBtn" onclick="logout();">로그아웃</div>
            </div>
         </div>
      </c:if>
   </div>
   <!-- login-area ends  -->
   
   <br clear="both">
   <br>
   
  
   <!-- nav-area starts -->
      <div class="wrap">
         <div class="nav">
            <div class="menu">HOME</div>
            <div class="menu">공지사항</div>
            <div class="menu">게시판</div>
            <div class="menu">사진게시판</div>
         </div>
      </div>
   <!-- nav-area ends -->
   
   <script>
      function login() {
         $("#loginForm").submit();
      }
   </script>
   
   <script>
   	function logout(){
   		var check = window.confirm("로그아웃을 하시겠습니까?");
   		
   		if(check){
   			location.href="<%= request.getContextPath()%>/logout";
   		}
   		
   	}
   	
   	function memberJoin(){
   		var check = window.confirm("회원가입을 하시겠습니까?");
   		
   		if(check){
   			
   			location.href = "${applicationScope.contextPath}/views/member/memberJoinForm.jsp";
   		}
   	}
   	
   	function update(){
   		var check = window.confirm("내 정보 수정을 하시겠습니까?");
   		
   		if(check){
   			location.href = "${applicationScope.contextPath}/views/member/memberUpdateForm.jsp";
   		}
   		
   	}
   </script>
</body>
</html>