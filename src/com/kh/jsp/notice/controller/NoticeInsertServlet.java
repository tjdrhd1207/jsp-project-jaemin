package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/insert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		int uno = Integer.parseInt(request.getParameter("uno"));
		String date = request.getParameter("date");
		String content = request.getParameter("content");
	
		System.out.println(date);
		
		java.sql.Date day = null;	
		if(date !="") {
//			String[] dateArr = date.split("-");
//			int year = Integer.parseInt(dateArr[0]);
//			int month = Integer.parseInt(dateArr[1])-1;
//			int dy = Integer.parseInt(dateArr[2]);
//			day = new java.sql.Date(new GregorianCalendar(year,month,dy).getTimeInMillis());
	
			day = java.sql.Date.valueOf(date);
		}else {
			
			day = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		}
		
		
		Notice newNotice = new Notice();
		newNotice.setnTitle(title);
		newNotice.setnWriter(uno);
		newNotice.setnContent(content);
		newNotice.setnDate(day);
		
		System.out.println("new notice : "+newNotice);
		
		int result = new NoticeService().insertNotice(newNotice);
		
		String path="";
		
		if(result>0){
			path="selectList.no";
			response.sendRedirect(path);
		}else {
			path="views/common/errorPage.jsp";
			request.setAttribute("message", "공지사항 등록 실패");
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
