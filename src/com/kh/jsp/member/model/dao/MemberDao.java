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
			
	
			pstmt.setString(1, newMember.getUserId());
			pstmt.setString(2, newMember.getUserPwd());
			pstmt.setString(3, newMember.getNickName());
			pstmt.setString(4, newMember.getPhone());
			pstmt.setString(5, newMember.getEmail());
			pstmt.setString(6, newMember.getAddress());
			pstmt.setString(7, newMember.getInterest());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

	public int updateMemberInformation(Connection con, Member updateRequestMember) {
	      PreparedStatement pstmt = null;
	      int result = 0;
	      System.out.println("업데이트 정보 연결");
	      
	      String query = prop.getProperty("updateMember");
	      System.out.println(1);
	      try {
	         pstmt = con.prepareStatement(query);
	        
	         pstmt.setString(1, updateRequestMember.getNickName());
	         pstmt.setString(2, updateRequestMember.getPhone());
	         pstmt.setString(3, updateRequestMember.getEmail());
	         pstmt.setString(4, updateRequestMember.getAddress());
	         pstmt.setString(5, updateRequestMember.getInterest());
	         pstmt.setString(6, updateRequestMember.getUserId());
	         pstmt.setString(7, updateRequestMember.getUserPwd());
	         System.out.println(2);
	         
	         result = pstmt.executeUpdate();
	         
	         System.out.println("result : "+result);
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	    	  close(pstmt);
	      }
	      return result;
	}
	
	
	public Member selectChangedMemberInformation(Connection con, Member updateRequestMember) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member changedMemberInformation = null;
		System.out.println("SELECT 정보 ");
		String query = prop.getProperty("selectChangedInfo");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, updateRequestMember.getUserId());
			pstmt.setString(2, updateRequestMember.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				changedMemberInformation = new Member();
				
				changedMemberInformation.setUno(rset.getInt("UNO"));
				changedMemberInformation.setUserId(rset.getString("USER_ID"));
				changedMemberInformation.setUserPwd(rset.getString("USER_PWD"));
				changedMemberInformation.setNickName(rset.getString("NICK_NAME"));
				changedMemberInformation.setPhone(rset.getString("PHONE"));
				changedMemberInformation.setEmail(rset.getString("EMAIL"));
				changedMemberInformation.setAddress(rset.getString("ADDRESS"));
				changedMemberInformation.setInterest(rset.getString("INTEREST"));
				changedMemberInformation.setEnrollDate(rset.getDate("ENROLL_DATE"));
				changedMemberInformation.setModifyDate(rset.getDate("MODIFY_DATE"));
				changedMemberInformation.setStatus(rset.getString("STATUS"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return changedMemberInformation;
	}


}
