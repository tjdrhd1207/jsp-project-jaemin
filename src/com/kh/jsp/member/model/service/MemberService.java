package com.kh.jsp.member.model.service;

import java.sql.Connection;

import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.member.model.vo.Member;

public class MemberService {

	public Member loginCheck(Member requestMember) {
		Connection con =getConnection();
		
		Member loginUser = new MemberDao().loginCheck(con,requestMember);
		
		close(con);
		
		return loginUser;
	}

	
	
}

