package com.gukmo.board.model;

public class HashtagVO {
	
	private int hashtag_num;
	private String hashtag;
	private int hashtag_cnt;
	private int week_cnt;
	
	protected HashtagVO() {}

	public HashtagVO(int hashtag_num, String hashtag, int hashtag_cnt, int week_cnt) {
		this.hashtag_num = hashtag_num;
		this.hashtag = hashtag;
		this.hashtag_cnt = hashtag_cnt;
		this.week_cnt = week_cnt;
	}

	public int getHashtag_num() {
		return hashtag_num;
	}

	public void setHashtag_num(int hashtag_num) {
		this.hashtag_num = hashtag_num;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getHashtag_cnt() {
		return hashtag_cnt;
	}

	public void setHashtag_cnt(int hashtag_cnt) {
		this.hashtag_cnt = hashtag_cnt;
	}

	public int getWeek_cnt() {
		return week_cnt;
	}

	public void setWeek_cnt(int week_cnt) {
		this.week_cnt = week_cnt;
	}

	
	
	
	
}
