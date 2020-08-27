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

	public int insertMember(Member newMember) {
		
		Connection con = getConnection();
		
		int result=0;
		
		result = new MemberDao().insertMember(con,newMember);
		
		if(result>0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		
		
		return result;
	}

	public Member updateMemberInformation(Member updateRequestMember) {
		//커넥션 생성
		Connection con = getConnection();
		
		//리턴할 변경된 정보를 저장할 memberType변수 선언
		Member changedMemberInformation = null;
		
		//리턴
		MemberDao md = new MemberDao();
		
		System.out.println("서비스 연결");
		
		int updateResult = md.updateMemberInformation(con,updateRequestMember);
		
		System.out.println("updateResult :"+updateResult);
		
		
		if(updateResult>0){
			//수정이 정상적으로 완료된 경우 변경된 회원 정보를 DB에서 다시 조회
			changedMemberInformation = md.selectChangedMemberInformation(con,updateRequestMember);
			System.out.println("수정 성공");
			
			if(changedMemberInformation !=null){
				commit(con);
			}else {
				System.out.println("조회가 되지않음");
				rollback(con);
			}
		}else {
			//수정이 정상적으로 되지 않은 경우 rollback
			System.out.println("수정이 안됨");
			rollback(con);
			
			}	
		
		close(con);
		
		return changedMemberInformation;
	}

	
	
}

