package com.kh.jsp.member.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import com.kh.jsp.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao(){
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Member loginCheck(Connection con, Member requestMember) {
	
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		Member loginUser = null;
		
		String query = prop.getProperty("loginSelect");
		
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getUserId());
			pstmt.setString(2, requestMember.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				
				loginUser = new Member();
				
				loginUser.setUno(rset.getInt("UNO"));
				loginUser.setUserId(rset.getString("USER_ID"));
				loginUser.setUserPwd(rset.getString("USER_PWD"));
				loginUser.setNickName(rset.getString("NICK_NAME"));
				loginUser.setPhone(rset.getString("PHONE"));
				loginUser.setEmail(rset.getString("EMAIL"));
				loginUser.setAddress(rset.getString("ADDRESS"));
				loginUser.setInterest(rset.getString("INTEREST"));
				loginUser.setEnrollDate(rset.getDate("ENROLL_DATE"));
				loginUser.setModifyDate(rset.getDate("MODIFY_DATE"));
				loginUser.setStatus(rset.getString("STATUS"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return loginUser;
	}



	public int insertMember(Connection con, Member newMember) {
	
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, newMember.getUno());
			pstmt.setString(2, newMember.getUserId());
			pstmt.setString(3, newMember.getUserPwd());
			pstmt.setString(4, newMember.getNickName());
			pstmt.setString(5, newMember.getPhone());
			pstmt.setString(6, newMember.getEmail());
			pstmt.setString(7, newMember.getAddress());
			pstmt.setString(8, newMember.getInterest());
			pstmt.setDate(9, newMember.getEnrollDate());
			pstmt.setDate(10, newMember.getModifyDate());
			pstmt.setString(11, newMember.getStatus());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}


}
