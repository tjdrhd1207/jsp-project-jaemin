<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
   .outer{
      width:800px;
      height:500px;
      background:black;
      color:white;
      margin-left:auto;
      margin-right:auto;
      margin-top:50px;
   }
   table {
      border:1px solid white;
   }

   .tableArea {
      width:450px;
      height:350px;
      margin-left:auto;
      margin-right:auto;
   }
</style>
<title>Insert title here</title>
</head>
<body>
   <jsp:include page="../common/menubar.jsp"/>
   <div class="outer">
      <br>
      <h2 align="center">공지 사항 내용</h2>
      <div class="tableArea">
            <table>
               <tr>
                  <td>제목 </td>
                  <td colspan="3"><input type="text" size="50" name="title" value="<c:out value="${ requestScope.notice.nTitle }"/>" readonly></td>
               </tr>
               <tr>
                  <td>작성자 </td>
                  <td>
                     <input type="text" value="<c:out value="${ requestScope.notice.nickName }"/>" name="writer" readonly>
                  </td>
                  <td>작성일</td>
                  <td><input type="date" name="date" value="<c:out value="${ requestScope.notice.nDate }"/>" readonly></td>
               </tr>
               <tr>
                  <td>내용 </td>
               </tr>
               <tr>
                  <td colspan="4">
                     <textarea name="content" cols="60" rows="15" style="resize:none;" readonly><c:out value="${ requestScope.notice.nContent }"/></textarea>
                  </td>
               </tr>
            </table>
            <br>
            <div align="center">
               <button onclick="location.href='${ applicationScope.contextPath }/selectList.no'">메뉴로 돌아가기</button>
               <c:if test="${ !empty sessionScope.loginUser and sessionScope.loginUser.userId.equals('admin') }">
				<button onclick="location.href='views/notice/noticeUpdateForm.jsp'">수정하기</button>
			</c:if>
            </div>
      </div>
   </div>
</body>
</html>