package com.kh.jsp.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LoginWrapper extends HttpServletRequestWrapper{

	public LoginWrapper(HttpServletRequest request) {
		super(request);
	}
	
	
	@Override
	public String getParameter(String key){
		
		String value = "";
		if(key != null && key.contentEquals("userPwd")){
			//1.비밀번호 암호화
			value = getSha512(super.getParameter("userPwd"));
		}else {
			//2/ 원래 기능으로 사용
			value = super.getParameter(key);
		}
		
		return value;
	}
	
	private static String getSha512(String pwd){
			String encPwd="";
		//단방향 해시 암호화 알고리즘
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
			md.update(bytes);
			
			encPwd = Base64.getEncoder().encodeToString(md.digest());
				
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
			
		
		return encPwd;
	}
}




