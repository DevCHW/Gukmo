package com.gukmo.board.model;

public class HashtagVO {

	private String hashtag_num;
	private String hashtag;
	private String fk_board_num;
	
	protected HashtagVO() {}
	
	public HashtagVO(String hashtag_num, String hashtag, String fk_board_num) {
		this.hashtag_num = hashtag_num;
		this.hashtag = hashtag;
		this.fk_board_num = fk_board_num;
	}
	
	public String getHashtag_num() {
		return hashtag_num;
	}
	public String getHashtag() {
		return hashtag;
	}
	public String getFk_board_num() {
		return fk_board_num;
	}

	@Override
	public String toString() {
		return "HashtagVO [hashtag_num=" + hashtag_num + ", hashtag=" + hashtag + ", fk_boardnum=" + fk_board_num + "]";
	}
	
}
