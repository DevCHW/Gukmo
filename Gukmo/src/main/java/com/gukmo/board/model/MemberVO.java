package com.gukmo.board.model;

public class MemberVO {
	// 로그인 관련
	private String userid;
	private String passwd;
	private String status;
	private String update_passwd_date;
	
	// 기본 생성자 일부러 protected로 막음
	protected MemberVO(){};
	
	
	// 파라미터가있는 생성자로만 값 주입 가능, Setter도 막음
	public MemberVO(String userid, String passwd, String status, String update_passwd_date, String acept_status) {
		this.userid = userid;
		this.passwd = passwd;
		this.status = status;
		this.update_passwd_date = update_passwd_date;
	}
	
	
	// Getter
	public String getUserid() {
		return userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getStatus() {
		return status;
	}
	public String getUpdate_passwd_date() {
		return update_passwd_date;
	}
}
