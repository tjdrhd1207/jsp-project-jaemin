package com.kh.jsp.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.kh.jsp.board.model.dao.BoardDao;
import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.board.model.vo.PageInfo;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.rollback;
import static com.kh.jsp.common.JDBCTemplate.getConnection;

public class BoardService {

	//게시물 목록 조회용(페이징 처리 전)
	public ArrayList<Board> selectList() {
		Connection con = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(con);
		
		close(con);
		
		return list;
	}

	public int insertBoard(Board newBoard) {

		Connection con = getConnection();
		
		int result = 0;
		result = new BoardDao().insertBoard(con,newBoard);
		
		if(result>0) {
		commit(con);
		}else {
		rollback(con);
		}
		close(con);
		
		return result;
	}

	public int getListCount(){
		
		Connection con = getConnection();
		
		int listCount = new BoardDao().getListCount(con);
		
		close(con);
		
		
		return listCount;
	}

	
	//페이징 처리를 이용한게시물 목록 조회용 메소드
	public ArrayList<Board> selectListWithPaging(PageInfo pi) {
		Connection con = getConnection();
		
		ArrayList<Board>list = new BoardDao().selectListWithPaging(con,pi);
		
		close(con);
		
		return list;
	}

	//썸네일 게시판 등록용 메소드
	public int insertThumbnail(Map<String, Object> requestData) {
		Connection con = getConnection();
		int result = 0;
		
		BoardDao bd = new BoardDao();
		Board board = (Board) requestData.get("board");
		ArrayList<Attachment> fileList = (ArrayList<Attachment>) requestData.get("fileList");
		
		int result1 = bd.insertThumbnailContent(con, board);
		
		if(result1 > 0) {
			int bid = bd.selectCurrval(con);
			
			int result2 = 0;
			for(int i = 0; i < fileList.size(); i++) {
				Attachment at = fileList.get(i);
				at.setBid(bid);
				result2 += bd.insertAttachment(con, at);
			}
			
			if(result2 == fileList.size()) {
				commit(con);
				result = 1;
			} else {
				rollback(con);
			}
			
		} else {
			rollback(con);
		}
		
		return result;
	}


}
