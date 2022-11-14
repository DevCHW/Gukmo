package com.gukmo.board.model;

public class MemberDTO {
	private String userid;
	private String passwd;
	private String status;
	private String update_passwd_date;
	private String Acept_status;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdate_passwd_date() {
		return update_passwd_date;
	}
	public void setUpdate_passwd_date(String update_passwd_date) {
		this.update_passwd_date = update_passwd_date;
	}
	public String getAcept_status() {
		return Acept_status;
	}
	public void setAcept_status(String acept_status) {
		Acept_status = acept_status;
	}
}
