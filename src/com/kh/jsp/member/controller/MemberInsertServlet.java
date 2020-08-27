package com.kh.jsp.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/insertMember.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//1.인코딩
			request.setCharacterEncoding("UTF-8");
		
			  String userId = request.getParameter("userId");
		      String userPwd = request.getParameter("userPwd");
		      String nickName = request.getParameter("nickName");
		      String tel1 = request.getParameter("tel1"); //010
		      String tel2 = request.getParameter("tel2");   //8852
		      String tel3 = request.getParameter("tel3");   //5540
		      String phone = tel1 + "-" + tel2 + "-" + tel3; // 010-8852-5540
		      String email = request.getParameter("email");
		      String zip = request.getParameter("zipCode");
		      String add1 = request.getParameter("address1");
		      String add2 = request.getParameter("address2");
		      String address = zip + "$" + add1 + "$" + add2; //012345$서울시 강남구 역삼동$672-1
		      //주소에서 사용하지 않는 기호를 사용한다
		      String[] irr = request.getParameterValues("interest");
		      String interest = "";
		      
		      if(irr != null) {
		         for(int i = 0; i < irr.length ; i++) {
		            if(i == 0) {
		               interest += irr[i];
		            } else {
		               interest += ", " + irr[i];
		            }
		         }
		      }
		      
		      Member newMember = new Member();
		      newMember.setUserId(userId);
		      newMember.setUserPwd(userPwd);
		      newMember.setNickName(nickName);
		      newMember.setPhone(phone);
		      newMember.setEmail(email);
		      newMember.setAddress(address);
		      newMember.setInterest(interest);
		      
		      System.out.println("insert request member: " + newMember);
		   

		      
		
		//3.서비스 로직 호출
		int result = new MemberService().insertMember(newMember);
		
		//4.성공 실패 여부에 따른 뷰 연결
		String page="";
		if(result>0){
			page = "index.jsp";
			
			response.sendRedirect(page);
			
		}else {
			page ="views/common/errorPage.jsp";
			request.setAttribute("message", "회원가입 실패!");
			
			request.getRequestDispatcher(page).forward(request, response);
		}
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
		
		
		
		
		
		
	}

}
