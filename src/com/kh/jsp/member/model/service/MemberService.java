package com.kh.jsp.member.model.service;


import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.member.model.vo.Member;

public class MemberService {

	public Member loginCheck(Member requestMember) {
		Connection con = getConnection();
		
		Member loginUser = new MemberDao().loginCheck(con,requestMember);
		
		close(con);
		
		return loginUser;
	}

	public int insertMember(Member requestMember) {
		
		Connection con = getConnection();
		
		int result=0;
		
		result = new MemberDao().insertMember(con,requestMember);
		
		if(result>0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		
		
		return result;
	}

	
	
}

