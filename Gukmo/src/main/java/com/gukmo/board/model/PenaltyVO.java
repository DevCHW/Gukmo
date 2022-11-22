package com.gukmo.board.model;

public class PenaltyVO {

	private String penalty_num;
	private String nickname;
	private String simple_penalty_reason;
	private String detail_penalty_reason;
	private String penalty_start_date;
	private String penalty_period;
	
	// 기본 생성자 막기
	protected PenaltyVO() {}

	// 파라미터가있는 생성자로만 값 주입 가능, Setter도 막음
	public PenaltyVO(String penalty_num, String nickname,
					 String simple_penalty_reason, String detail_penalty_reason,
					 String penalty_start_date, String penalty_period) {
		this.penalty_num = penalty_num;
		this.nickname = nickname;
		this.simple_penalty_reason = simple_penalty_reason;
		this.detail_penalty_reason = detail_penalty_reason;
		this.penalty_start_date = penalty_start_date;
		this.penalty_period = penalty_period;	
	}
	
	
	
	
	
	
	public String getPenalty_num() {
		return penalty_num;
	}

	public String getNickname() {
		return nickname;
	}

	public String getSimple_penalty_reason() {
		return simple_penalty_reason;
	}

	public String getDetail_penalty_reason() {
		return detail_penalty_reason;
	}

	public String getPenalty_start_date() {
		return penalty_start_date;
	}

	public String getPenalty_period() {
		return penalty_period;
	};
	
	
	
	
	
}
