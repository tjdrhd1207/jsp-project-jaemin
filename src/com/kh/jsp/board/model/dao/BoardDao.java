package com.kh.jsp.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import static com.kh.jsp.common.JDBCTemplate.close;

import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.board.model.vo.PageInfo;

public class BoardDao {
	private Properties prop = new Properties();
	
	public BoardDao(){
		String fileName = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
	
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	
	public ArrayList<Board> selectList(Connection con){
		
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String query = prop.getProperty("selectList");
	
		try {
			stmt =  con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Board>();
			
			while(rset.next()){
				
						Board b = new Board();
						
						
						b.setBid(rset.getInt("BID"));
						b.setbType(rset.getInt("BTYPE"));
						b.setBno(rset.getInt("BNO"));
						b.setCid(rset.getInt("CID"));
						b.setcName(rset.getString("CNAME"));
						b.setbTitle(rset.getString("BTITLE"));
						b.setbContent(rset.getString("BCONTENT"));
						b.setbWriter(rset.getInt("BWRITER"));
						b.setNickName(rset.getString("NICK_NAME"));
						b.setbCount(rset.getInt("BCOUNT"));
						b.setRefBid(rset.getInt("REF_BID"));
						b.setReplyLevel(rset.getInt("REPLY_LEVEL"));
						b.setbDate(rset.getDate("BDATE"));
						b.setModifyDate(rset.getDate("MODIFY_DATE"));
						b.setStatus(rset.getString("STATUS"));
						
						list.add(b);
						
					}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(rset);
			close(stmt);
			
		}
		
	
		return list;
	}



	public int insertBoard(Connection con, Board newBoard) {
	
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		
		try {
			
			
			pstmt = con.prepareStatement(query);
			
			
			pstmt.setInt(1, newBoard.getCid());
			pstmt.setString(2, newBoard.getbTitle());
			pstmt.setString(3, newBoard.getbContent());
			pstmt.setInt(4, newBoard.getbWriter());

			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	//전체 목록 갯수 조회용 메소드
	public int getListCount(Connection con) {
		
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("listCount");
		
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;

	}



	public ArrayList<Board> selectListWithPaging(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String query = prop.getProperty("selectListWithPaging");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow= (pi.getCurrentPage()-1)*pi.getLimit()+1;
			int endRow= startRow + pi.getLimit()-1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset=pstmt.executeQuery();
			
			list = new ArrayList<Board>();
			
			while(rset.next()){
				
				Board b = new Board();
				
				
				b.setBid(rset.getInt("BID"));
				b.setbType(rset.getInt("BTYPE"));
				b.setBno(rset.getInt("BNO"));
				b.setCid(rset.getInt("CID"));
				b.setcName(rset.getString("CNAME"));
				b.setbTitle(rset.getString("BTITLE"));
				b.setbContent(rset.getString("BCONTENT"));
				b.setbWriter(rset.getInt("BWRITER"));
				b.setNickName(rset.getString("NICK_NAME"));
				b.setbCount(rset.getInt("BCOUNT"));
				b.setRefBid(rset.getInt("REF_BID"));
				b.setReplyLevel(rset.getInt("REPLY_LEVEL"));
				b.setbDate(rset.getDate("BDATE"));
				b.setModifyDate(rset.getDate("MODIFY_DATE"));
				b.setStatus(rset.getString("STATUS"));
				
				list.add(b);
				
			}
			
					
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
		

	}



	public int insertThumbnailContent(Connection con, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertThumb");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getbTitle());
			pstmt.setString(2, board.getbContent());
			pstmt.setInt(3, board.getbWriter());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public int selectCurrval(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		
		int bid = 0;
		
		String query = prop.getProperty("selectCurrval");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				bid = rset.getInt("CURRVAL");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return bid;
	}



	public int insertAttachment(Connection con, Attachment at) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, at.getBid());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			pstmt.setInt(5, at.getFileLevel());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
