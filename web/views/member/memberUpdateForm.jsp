<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<style>
   .outer{
      width:600px;
      height:500px;
      background:black;
      color:white;
      margin-left:auto;
      margin-right:auto;
      margin-top:50px;
   }
   
   #idCheck, #ckZip, #goMain, #joinBtn {
      background:orangered;
      border-radius:5px;
      width:80px;
      height:25px;
      text-align:center;
   }
   
   #idCheck:hover, #ckZip:hover, #joinBtn:hover, #goMain:hover {
      cursor:pointer;
   }
   td {
      text-align:right;
   }
   #ckZip, #joinBtn {
      background:yellowgreen;
   }
   #joinBtn, #goMain {
      display:inline-block;
   }
</style>
</head>
<body>
   <jsp:include page="../common/menubar.jsp"/>
   <div class="outer">
      <br>
      <h2 align="center">정보수정</h2>
      
      <form id="joinForm" action="${ applicationScope.contextPath }/updateMember.me" method="post">
         
         <table align="center">
            <tr>
               <td width="200px">* 아이디 </td>
               <td><input type="text" maxlength="13" name="userId" id="userId" readonly value="${ sessionScope.loginUser.getUserId() }"></td>
               <td width="200px"><div id="idCheck">중복확인</div></td>
            </tr>
            <tr>
               <td>* 비밀번호</td>
               <td><input type="password" maxlength="13" name="userPwd" ></td>
               <td></td>
            </tr>
            <tr>
               <td>* 비밀번호확인</td>
               <td><input type="password" maxlength="13" name="userPwd2"></td>
               <td><label id="pwdResult"></label></td>
            </tr>
            <tr>
               <td>* 닉네임</td>
               <td><input type="text" maxlength="5" name="nickName" value="${ sessionScope.loginUser.getNickName() }"></td>
               <td><label id="nnResult"></label></td>
            </tr>
            <tr>
               <td>연락처 </td>
               <td>
                  <input type="text" maxlength="3" name="tel1" size="2">-
                  <input type="text" maxlength="4" name="tel2" size="2">-
                  <input type="text" maxlength="4" name="tel3" size="2">
               </td>
               <td></td>
            </tr>
            <tr>
               <td>이메일 </td>
               <td><input type="email" name="email" value="${ sessionScope.loginUser.getEmail() }"></td>
               <td></td>
            </tr>
            <tr>
               <td>우편번호</td>
               <td><input type="text" name="zipCode"></td>
               <td><div id="ckZip">검색</div></td>
            </tr>
            <tr>
               <td>주소</td>
               <td><input type="text" name="address1"></td>
               <td></td>
            </tr>
            <tr>
               <td>상세주소</td>
               <td><input type="text" name="address2"></td>
               <td></td>
            </tr>
            <tr>
               <td>관심분야</td>
               <td>
                  <input type="checkbox" id="sports" name="interest" value="운동">
                  <label for="sports">운동</label>
                  <input type="checkbox" id="climbing" name="interest" value="등산">
                  <label for="climbing">등산</label>
                  <input type="checkbox" id="fishing" name="interest" value="낚시">
                  <label for="fishing">낚시</label>
                  <input type="checkbox" id="cooking" name="interest" value="요리">
                  <label for="cooking">요리</label>
                  <input type="checkbox" id="game" name="interest" value="게임">
                  <label for="game">게임</label>
                  <input type="checkbox" id="etc" name="interest" value="기타">
                  <label for="etc">기타</label>
               </td>
               <td></td>
            </tr>
         </table>
         <br>
         <div class="btns" align="center">
            <div id="goMain" onclick="goMain()">메인으로</div>
            <div id="joinBtn" onclick="updateMember();">수정하기</div>
         </div>
         
         </form>
      
   </div>
   <script>
         function goMain(){
            location.href = "${ applicationScope.contextPath }/index.jsp";
         }
         
         function updateMember(){
            $("#joinForm").submit();
         }
         
         
         //취미 체킹 받아옴
         $(function(){
            $("input[name=interest]").each(function(){
               var arr = '${ sessionScope.loginUser.getInterest() }'.split(", ");
               console.log("interest arr :" + arr);
               
               for(var i = 0; i < arr.length; i++){
                  if($(this).val() === arr[i]){
                     $(this).attr("checked", true);
                  }
               }
               
               
            });
         });
         
         //번호 받아와서 value 값 설정
         $(function(){
            /* $("input[name=tel$]").each(function(){
               var arr = '${ sessionScope.loginUser.getPhone() }'.split("-");
               console.log("번호 arr : " + arr);
               
               for(var i = 0; i < arr.length; i++){
                  
                  $(this).val(arr[i]);
               }
               
            }); */
            
            var arr = '${ sessionScope.loginUser.getPhone() }'.split("-");
         console.log("번호 arr : " + arr);
         
            for(var i = 1 ; i <= arr.length ; i++ ){
               $("input[name=tel"+ i +"]").each(function(){
                  $(this).val(arr[i-1]);
               });
            }
            
         });
         
         //주소 받아와서 value 설정
         $(function(){
            var arr = '${ sessionScope.loginUser.getAddress() }'.split("$");
            /* var arr = '${ sessionScope.loginUser.address }'.split("$"); 밑에거 아래거 둘다 됨 */
            
            console.log("address arr : " + arr)
            
            $("input[name=zipCode]").val(arr[0]);
            
            for(var i = 1 ; i<= arr.length -1 ; i++) {
               
            $("input[name=address" + i +"]").each(function(){
                  
               $(this).val(arr[i]);
                  
            });
               
            }
         });
         
   </script>
</body>
</html>