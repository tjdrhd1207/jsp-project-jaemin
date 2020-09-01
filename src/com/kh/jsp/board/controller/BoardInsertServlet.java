package com.kh.jsp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.member.model.vo.Member;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/insert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int category = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		int writer = loginUser.getUno();
		
		Board newBoard = new Board();
		newBoard.setCid(category);
		newBoard.setbTitle(title);
		newBoard.setbContent(content);
		newBoard.setbWriter(writer);
		
		
		System.out.println("new board : "+ newBoard);
		
		
		//board insert 구문 추가될 것
		//bno는 seq_bno 시퀀스 이용, bid는 seq_bno1
		//bcount는 default값 이용, ref_bid는 null, reply_level은 0으로
		//bdate,modify_date	,status는 default 값으로 입력
		int result = new BoardService().insertBoard(newBoard);
		
		String path ="";
		if(result>0){
			response.sendRedirect(request.getContextPath()+"/selectList.bo");
		}else {
			request.setAttribute("message", "게시판 작성 실패");
			request.getRequestDispatcher(path).forward(request, response);
			
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
