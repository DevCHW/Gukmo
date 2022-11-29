package com.gukmo.board.model;


public class ActivityVO {
	private String activity_num;     
	private String fk_userid;      
	private String fk_board_num;     
	private String activity_date;       
	private String subject;         
	private String detail_category;
	private String division;
	private String nickname;
	

	// protected로 기본생성자 막기
	protected ActivityVO() {}

	//파라미터가 있는 생성자로만 값 주입 가능, Setter로 변경 불가
	public ActivityVO(String activity_num, String fk_userid, String fk_board_num, String activity_date,
			String subject, String detail_category, String division) {
		this.activity_num = activity_num;
		this.fk_userid = fk_userid;
		this.fk_board_num = fk_board_num;
		this.activity_date = activity_date;
		this.subject = subject;
		this.detail_category = detail_category;
		this.division = division;
	}
	
	
	public String getActivity_num() {
		return activity_num;
	}

	public String getFk_userid() {
		return fk_userid;
	}

	public String getFk_board_num() {
		return fk_board_num;
	}

	public String getActivity_date() {
		return activity_date;
	}

	public String getSubject() {
		return subject;
	}

	public String getDetail_category() {
		return detail_category;
	}

	public String getDivision() {
		return division;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	
}
