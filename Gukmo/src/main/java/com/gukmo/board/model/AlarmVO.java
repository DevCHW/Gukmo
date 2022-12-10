package com.gukmo.board.model;

import java.util.Date;

public class AlarmVO {

	private int alarmno;      
	private int fk_board_num;       
	private int fk_comment_num;       
	private String fk_userid;  
	private String cmd; 
	private Date alarm_date;          
	private String isread;
	
	
	
	public int getAlarmno() {
		return alarmno;
	}
	public void setAlarmno(int alarmno) {
		this.alarmno = alarmno;
	}
	public int getFk_board_num() {
		return fk_board_num;
	}
	public void setFk_board_num(int fk_board_num) {
		this.fk_board_num = fk_board_num;
	}
	public int getFk_comment_num() {
		return fk_comment_num;
	}
	public void setFk_comment_num(int fk_comment_num) {
		this.fk_comment_num = fk_comment_num;
	}
	public String getFk_userid() {
		return fk_userid;
	}
	public void setFk_userid(String fk_userid) {
		this.fk_userid = fk_userid;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public Date getAlarm_date() {
		return alarm_date;
	}
	public void setAlarm_date(Date alarm_date) {
		this.alarm_date = alarm_date;
	}
	public String getIsread() {
		return isread;
	}
	public void setIsread(String isread) {
		this.isread = isread;
	} 
	
	
}
