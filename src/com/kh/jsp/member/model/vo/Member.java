package com.kh.jsp.member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable{
	private int uno;
	private String userId;
	private String userPwd;
	private String nickName;
	private String phone;
	private String email;
	private String address;
	private String interest;
	private Date enrollDate;
	private String status;
	
	public Member(){}

	public Member(int uno, String userId, String userPwd, String nickName, String phone, String email, String address,
			String interest, Date enrollDate) {
		super();
		this.uno = uno;
		this.userId = userId;
		this.userPwd = userPwd;
		this.nickName = nickName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.interest = interest;
		this.enrollDate = enrollDate;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [uno=" + uno + ", userId=" + userId + ", userPwd=" + userPwd + ", nickName=" + nickName
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", interest=" + interest
				+ ", enrollDate=" + enrollDate + "]";
	}
	
	

}
