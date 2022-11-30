package com.gukmo.board.model;

public class TagPostMappingVO {

	private int hashtag_num;
	private int fk_board_num;
	
	protected TagPostMappingVO() {}

	public TagPostMappingVO(int hashtag_num, int fk_board_num) {
		this.hashtag_num = hashtag_num;
		this.fk_board_num = fk_board_num;
	}

	public int getHashtag_num() {
		return hashtag_num;
	}


	public void setHashtag_num(int hashtag_num) {
		this.hashtag_num = hashtag_num;
	}


	public int getFk_board_num() {
		return fk_board_num;
	}


	public void setFk_board_num(int fk_board_num) {
		this.fk_board_num = fk_board_num;
	}
	
	
	
}
