package com.gukmo.board.model;

public class HashtagVO {

	private String hashtag_num;
	private String hashtag;
	private String fk_boardnum;
	
	protected HashtagVO() {}
	
	public HashtagVO(String hashtag_num, String hashtag, String fk_boardnum) {
		super();
		this.hashtag_num = hashtag_num;
		this.hashtag = hashtag;
		this.fk_boardnum = fk_boardnum;
	}
	
	public String getHashtag_num() {
		return hashtag_num;
	}
	public String getHashtag() {
		return hashtag;
	}
	public String getFk_boardnum() {
		return fk_boardnum;
	}
}
