package com.gukmo.board.model;

import java.util.Date;

public class AlarmVO {

	private int alarmno;      
	private int alarm_board_num;       
	private int alarm_comment_num;       
	private String alarm_nickname;  
	private String cmd; 
	private Date alarm_date;          
	private String isread;
	
	
	
	public int getAlarmno() {
		return alarmno;
	}
	public void setAlarmno(int alarmno) {
		this.alarmno = alarmno;
	}
	public int getalarm_board_num() {
		return alarm_board_num;
	}
	public void setalarm_board_num(int alarm_board_num) {
		this.alarm_board_num = alarm_board_num;
	}
	public int getalarm_comment_num() {
		return alarm_comment_num;
	}
	public void setalarm_comment_num(int alarm_comment_num) {
		this.alarm_comment_num = alarm_comment_num;
	}
	public String getalarm_nickname() {
		return alarm_nickname;
	}
	public void setalarm_nickname(String alarm_nickname) {
		this.alarm_nickname = alarm_nickname;
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
