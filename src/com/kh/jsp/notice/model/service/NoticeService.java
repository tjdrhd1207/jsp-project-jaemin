package com.kh.jsp.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.jsp.common.JDBCTemplate.getConnection;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.notice.model.dao.NoticeDao;
import com.kh.jsp.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList() {
		
		System.out.println("연결");
		Connection con = getConnection();
		
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(con);
		
		close(con);
		
		return list;
	}

	public int insertNotice(Notice newNotice) {
		
		Connection con = getConnection();
		
		int result = 0;
		
		result = new NoticeDao().insertNotice(con,newNotice);
		
		if(result>0){
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public Notice selectOneNoticeByNno(int num) {
		
		Connection con = getConnection();
		
		NoticeDao nd = new NoticeDao();
		
		Notice notice = null;
		
		int result = nd.upateCount(con,num);
		
		if(result > 0) {
			notice = nd.selectOne(con, num);
			if(notice != null) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		
		
		close(con);
		
		return notice;
	}

}
