package com.gukmo.board.model;

public class VisitCountVO {

	private String visit_num;
	private String visit_ip;
	private String visit_time;
	private String visit_refer;
	private String visit_browser;
	
	
	public String getVisit_num() {
		return visit_num;
	}
	public void setVisit_num(String visit_num) {
		this.visit_num = visit_num;
	}
	public String getVisit_ip() {
		return visit_ip;
	}
	public void setVisit_ip(String visit_ip) {
		this.visit_ip = visit_ip;
	}
	public String getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}
	public String getVisit_refer() {
		return visit_refer;
	}
	public void setVisit_refer(String visit_refer) {
		this.visit_refer = visit_refer;
	}
	public String getVisit_browser() {
		return visit_browser;
	}
	public void setVisit_browser(String visit_browser) {
		this.visit_browser = visit_browser;
	}
	
	@Override
	public String toString() {
		return "VisitCountVO [visit_num=" + visit_num + ", visit_ip=" + visit_ip + ", visit_time=" + visit_time
				+ ", visit_refer=" + visit_refer + ", visit_agent=" + visit_browser + "]";
	}
	
	
	
	

}
