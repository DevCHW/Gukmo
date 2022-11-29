package com.gukmo.board.model;

public class TagPostMappingVO {

	private int hashtag_num;
	private int board_num;
	
	protected TagPostMappingVO() {}

	public TagPostMappingVO(int hashtag_num, int board_num) {
		this.hashtag_num = hashtag_num;
		this.board_num = board_num;
	}

	public int getHashtag_num() {
		return hashtag_num;
	}


	public void setHashtag_num(int hashtag_num) {
		this.hashtag_num = hashtag_num;
	}


	public int getBoard_num() {
		return board_num;
	}


	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	
	
	
}
