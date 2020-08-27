<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String successCode= (String)request.getAttribute("successCode"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<script>
		var successCode = '<%=successCode%>';
		var alertMessage = "";
		var redirectPath = "";
	
		switch(successCode){
		case "insertMember" : alertMessage = "회원 가입에 성공하셨습니다!";
							  redirectPath = "<%=request.getContextPath()%>";
							  break;
		case "updateMember" : alertMessage = "회원 정보 수정에 성공하셨습니다!";
							  redirectPath = "<%=request.getContextPath()%>";
							  break;
		case "deleteMember" : alertMessage = "회원 정보 삭제에 성공하셨습니다!";
							  redirectPath = "<%=request.getContextPath()%>";
							  break;
		}
	
		alert(alertMessage);
		location.href=redirectPath;
		
	</script>
</body>
</html>


