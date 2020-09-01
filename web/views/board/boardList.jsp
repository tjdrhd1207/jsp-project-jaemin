<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   .outer {
      width:900px;
      height:500px;
      background:black;
      color:white;
      margin-top:50px;
      margin-left:auto;
      margin-right:auto;
   }
   table {
      border:1px solid white;
      text-align:center;
   }
   .table-area {
      width:650px;
      height:350px;
      margin:0 auto;
   }
   .search-area {
      width:650px;
      margin:0 auto;
   }
</style>
</head>
<body>
   <jsp:include page="../common/menubar.jsp"/>
   <div class="outer">
      <br>
      <h2 align="center">게시판</h2>
      <div class="table-area">
         <table align="center" id="listArea">
            <tr>
               <th width="100px">글번호</th>
               <th width="100px">카테고리</th>
               <th width="300px">글제목</th>
               <th width="100px">작성자</th>
               <th width="100px">조회수</th>
               <th width="150px">작성일</th>
            </tr>
            <c:forEach var="b" items="${ requestScope.list }">
               <tr>
                  <td><c:out value="${ b.bno }"/></td>
                  <td><c:out value="${ b.cName }"/></td>
                  <td><c:out value="${ b.bTitle }"/></td>
                  <td><c:out value="${ b.nickName }"/></td>
                  <td><c:out value="${ b.bCount }"/></td>
                  <td><c:out value="${ b.bDate }"/></td>
               </tr>
            </c:forEach>
         </table>
      </div>
      
      <div class="paging-area" align="center">
         <button onclick="location.href='${applicationScope.contextPath}/selectList.bo?currentPage=1'"><<</button>
         
         <c:if test="${ requestScope.pi.currentPage <= 1 }">
            <button disabled><</button>
         </c:if>
         <c:if test="${ requestScope.pi.currentPage > 1 }">
            <button onclick="location.href='${applicationScope.contextPath}/selectList.bo?currentPage=<c:out value="${ requestScope.pi.currentPage - 1 }"/>'"><</button>
         </c:if>
         
         <c:forEach var="p" begin="${ requestScope.pi.startPage }" end="${ requestScope.pi.endPage }" step="1">
            <c:if test="${ requestScope.pi.currentPage eq p }">
               <button disabled><c:out value="${ p }"/></button>
            </c:if>
            <c:if test="${ requestScope.pi.currentPage ne p }">
               <button onclick="location.href='${applicationScope.contextPath}/selectList.bo?currentPage=<c:out value="${ p }"/>'"><c:out value="${ p }"/></button>
            </c:if>
         </c:forEach>
         
         
         
         <c:if test="${ requestScope.pi.currentPage >= requestScope.pi.maxPage }">
            <button disabled>></button>
         </c:if>
         <c:if test="${ requestScope.pi.currentPage < requestScope.pi.maxPage }">
            <button onclick="location.href='${applicationScope.contextPath}/selectList.bo?currentPage=<c:out value="${ requestScope.pi.currentPage + 1 }"/>'">></button>
         </c:if>
         
         <button onclick="location.href='${applicationScope.contextPath}/selectList.bo?currentPage=<c:out value="${ requestScope.pi.maxPage }"/>'">>></button>
      </div>
      
      <div class="search-area" align="center">
         <select id="searchCondition" name="searchCondition">
            <option value="category">카테고리</option>
            <option value="writer">작성자</option>
            <option value="title">제목</option>
            <option value="content">내용</option>
         </select>
         <input type="search" name="searchValue">
         <button type="submit">검색하기</button>
         <c:if test="${ !empty sessionScope.loginUser }">
            <button onclick="location.href='views/board/boardInsertForm.jsp'">작성하기</button>
         </c:if>
      </div>
      
   </div>
   
</body>
</html>

