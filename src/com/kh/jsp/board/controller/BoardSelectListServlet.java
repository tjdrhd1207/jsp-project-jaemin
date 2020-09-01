package com.kh.jsp.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardSelectListServlet
 */
@WebServlet("/selectList.bo")
public class BoardSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*ArrayList<Board> list = new BoardService().selectList();
		
		System.out.println("select board list : " + list);
		
		String path = "";
		if(list != null) {
			path = "views/board/boardList.jsp";
			request.setAttribute("list", list);
		} else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "게시판 조회 실패!!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);*/
		
		//페이징 처리 추가
		int currentPage;		//현재 페이지를 표시할 변수
		int limit;				//한 페이지에 게시글이 몇 개가 보여질 것인지 표시
		int maxPage;			//전체 페이지에서 가장 마지막 페이지
		int startPage;			//한 번에 표시될 페이지가 시작할 페이지
		int endPage;			//한 번에 표시될 페이지가 끝나는 페이지
		
		//게시판은 보통 1페이지부터 시작함
		currentPage = 1;
		
		//파라미터로 전달되는 값이 있는 경우 그 값으로 currentPage값으로 사용
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//한 페이지에 보여질 목록 갯수
		limit = 10;
		
		BoardService bs = new BoardService();
		
		//전체 목록 갯수 리턴
		int listCount = bs.getListCount();
		
		System.out.println("전체 게시물 목록 갯수 : " + listCount);
		
		//총 페이지 수 계산
		//예를 들면 목록 갯수가 123개 이면, 총 13개 페이지가 필요함
		//짜투리 목록이 생기면(글이 최소 1개가 생기면) 1페이지가 추가되어야 함
		//limit에 따라 달라짐 (가중치를 계산해야 함)
		//limit가 10일 때 게시물 한 개는 10%(0.1)의 가중치를 가지고 있음
		//0.9를 더하기 해줘야 함
		maxPage = (int) ((double) listCount / limit + 0.9);
		
		//시작 페이지 수 계산
		//1, 11, 21, 31, ...
		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
		
		//목록 아래 보여질 마지막 페이지 수(10, 20, 30,...)
		endPage = startPage + 10 - 1;
		
		//13페이지까지만 필요한 상황임
		//1 2 3 4 5 6 7 8 9 10
		//11 12 13 14 15 16 17 18 19 20
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		//페이징에 필요한 정보들을 담을 vo
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<Board> list = bs.selectListWithPaging(pi);
		
		String path = "";
		if(list != null) {
			path = "views/board/boardList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "게시판 조회 실패!!");
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

