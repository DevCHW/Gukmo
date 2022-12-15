package com.gukmo.board.model;

public class VisitCountVO {

	private int visit_num;
	private int visit_ip;
	private int visit_time;
	private int visit_refer;
	private int visit_agent;
	
	
	protected VisitCountVO () {}
	
	public VisitCountVO(int visit_num, int visit_ip, int visit_time, int visit_refer, int visit_agent) {
		super();
		this.visit_num = visit_num;
		this.visit_ip = visit_ip;
		this.visit_time = visit_time;
		this.visit_refer = visit_refer;
		this.visit_agent = visit_agent;
	}


	//Getter
	public int getVisit_num() {
		return visit_num;
	}
	public int getVisit_ip() {
		return visit_ip;
	}
	public int getVisit_time() {
		return visit_time;
	}
	public int getVisit_refer() {
		return visit_refer;
	}
	public int getVisit_agent() {
		return visit_agent;
	}
	

}
