package com.gukmo.board.model;

public class LikeVO {
	
	private int fk_board_num;
	private String fk_userid;
	
	//기본생성자 protected
	protected LikeVO(){}

	public LikeVO(int fk_board_num, String fk_userid) {		
		this.fk_board_num = fk_board_num;
		this.fk_userid = fk_userid;
	}

	//Getter
	public int getFk_board_num() {
		return fk_board_num;
	}

	public String getFk_userid() {
		return fk_userid;
	}

	//확인용 toString Override
	@Override
	public String toString() {
		return "LikeVO [fk_board_num=" + fk_board_num + ", fk_userid=" + fk_userid + "]";
	}
	
	
	
	

}
