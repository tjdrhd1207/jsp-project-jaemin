package com.kh.jsp.notice.model.dao;

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

import com.kh.jsp.notice.model.vo.Notice;

public class NoticeDao {
	private Properties prop = new Properties();
	
	public NoticeDao(){
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.properties").getPath();
	
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	public ArrayList<Notice> selectNoticeList(Connection con) {
	
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectList");
		
			
			try {
				
				stmt = con.createStatement();
				rset = stmt.executeQuery(query);
				
				list = new ArrayList<Notice>();
				
				while(rset.next()) {
					Notice n = new Notice();
					
					n.setNno(rset.getInt("NNO"));
					n.setnTitle(rset.getString("NTITLE"));
					n.setNickName(rset.getString("NICK_NAME"));
					n.setnContent(rset.getString("NCONTENT"));
					n.setnCount(rset.getInt("NCOUNT"));
					n.setnDate(rset.getDate("NDATE"));
			
					list.add(n);
				
					}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
				close(stmt);
				close(rset);
			}
			
		
		return list;
	}

	public int insertNotice(Connection con, Notice newNotice) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			 pstmt.setString(1, newNotice.getnTitle());
	         pstmt.setString(2, newNotice.getnContent());
	         pstmt.setInt(3, newNotice.getnWriter());
	         //pstmt.setInt(4, newNotice.getnCount());
	         pstmt.setDate(4,newNotice.getnDate());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}



	public Notice selectOne(Connection con, int num) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		
		String query = prop.getProperty("selectOne");
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				notice = new Notice();
				
				notice.setNno(rset.getInt("NNO"));
				notice.setnTitle(rset.getString("NTITLE"));
				notice.setNickName(rset.getString("NICK_NAME"));
				notice.setnContent(rset.getString("NCONTENT"));
				notice.setnCount(rset.getInt("NCOUNT"));
				notice.setnDate(rset.getDate("NDATE"));
			
				
			}
			System.out.println("list 값 : "+notice);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

	public int upateCount(Connection con, int num) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setInt(2, num);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

}
