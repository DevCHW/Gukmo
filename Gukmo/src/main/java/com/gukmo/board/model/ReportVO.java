package com.gukmo.board.model;

public class ReportVO {
	private String report_num;
	private String fk_num;
	private String report_nickname;
	private String reported_nickname;
	private String simple_report_reason;
	private String detail_report_reason;
	private String report_date;
	private String report_type;
	private String userid;
	private String receipt;
	
	protected ReportVO() {};
	
	
	
	public ReportVO(String report_num, String fk_num
					, String report_nickname, String reported_nickname
					, String simple_report_reason, String detail_report_reason, String report_date, String report_type, String userid, String receipt) {
		
		this.report_num = report_num;
		this.fk_num = fk_num;
		this.report_nickname = report_nickname;
		this.reported_nickname = reported_nickname;
		this.simple_report_reason = simple_report_reason;
		this.detail_report_reason = detail_report_reason;
		this.report_date = report_date;
		this.report_type = report_type;
		this.userid = userid;
		this.receipt = receipt; 
	}

	
	public String getReceipt() {
		return receipt;
	}



	// Getter 
	public String getReport_num() {
		return report_num;
	}

	public String getFk_num() {
		return fk_num;
	}

	public String getReport_nickname() {
		return report_nickname;
	}

	public String getReported_nickname() {
		return reported_nickname;
	}

	public String getSimple_report_reason() {
		return simple_report_reason;
	}

	public String getReport_type() {
		return report_type;
	}

	public String getDetail_report_reason() {
		return detail_report_reason;
	}

	public String getReport_date() {
		return report_date;
	}
	
	public String getUserid() {
		return userid;
	}


	// id값 가져오기 위한
	public void setUserid(String userid) {
		this.userid = userid;
	}
	

	
	
	
}
