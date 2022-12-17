package com.gukmo.board.model;

import java.util.Date;

import com.gukmo.board.common.MyUtil;

public class AlarmVO {

	private int alarmno;     
	private String alarm_nickname; 
	private String cmd; 
	private String url;    
	private int url_num;   
	private String content;          
	private Date alarm_date;          
	private String isread; 
	
	
	// select 용
	private String subject;
	private String cmt_content;
	public int getAlarmno() {
		return alarmno;
	}
	public String getAlarm_nickname() {
		return alarm_nickname;
	}
	public String getCmd() {
		return cmd;
	}
	public String getUrl() {
		return url;
	}
	public int getUrl_num() {
		return url_num;
	}
	public String getContent() {
		return content;
	}
	public String getAlarm_date() {
		return MyUtil.calculateTime(alarm_date); // 기존의 getter, setter에서 변경된 부분
	}
	public String getIsread() {
		return isread;
	}
	public String getSubject() {
		return subject;
	}
	public String getCmt_content() {
		return cmt_content;
	}

}