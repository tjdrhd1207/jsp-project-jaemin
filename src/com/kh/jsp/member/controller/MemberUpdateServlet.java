package com.kh.jsp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/updateMember.me")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
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
		
		//2.전송값 꺼내서 변수에 담기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String nickName = request.getParameter("nickName");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");   //8852
	    String tel3 = request.getParameter("tel3");   //5540
	    String phone = tel1 + "-" + tel2 + "-" + tel3; // 010-8852-5540
	    String email = request.getParameter("email");
	    String zip = request.getParameter("zipCode");
	    String add1 = request.getParameter("address1");
	    String add2 = request.getParameter("address2");
	    String address = zip + "$" + add1 + "$" + add2;
	    String[] irr = request.getParameterValues("interest");
	    
	    String interest = "";
	    
	    if(irr !=null){
	    	for(int i=0; i<irr.length; i++){
	    		if(i==0){
	    			interest += irr[i];
	    		}else {
	    			interest +=", "+irr[i];
	    		}
	    	}
	    }
	    
	    Member updateRequestMember = new Member();
	    
	    updateRequestMember.setUserId(userId);
		updateRequestMember.setUserPwd(userPwd);
		updateRequestMember.setNickName(nickName);
		updateRequestMember.setPhone(phone);
		updateRequestMember.setEmail(email);
		updateRequestMember.setAddress(address);
		updateRequestMember.setInterest(interest);
		
		  System.out.println("update request member: " + updateRequestMember);
		  
		  Member changedMemberInformation = new MemberService().updateMemberInformation(updateRequestMember);
		  
		  String path = "";
          if(changedMemberInformation != null) {
             //성공 시 session에 loginUser 정보 갱신
             //같은 key를 가지고 setAttribute 하는 경우 기존 값을 새로운 값으로 덮어 씀
             request.getSession().setAttribute("loginUser", changedMemberInformation);
             
             //successPage.jsp로 포워딩할 코드를 request영역에 담기
             request.setAttribute("successCode", "updateMember");
             
             path = "views/common/successPage.jsp";
             
          } else {
             //실패시 request에 실패 메세지 담기
             request.setAttribute("message", "회원 정보 수정 실패!");
             
             path = "views/common/errorPage.jsp";
          }
          
          request.getRequestDispatcher(path).forward(request, response);
       
    
    
 }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
