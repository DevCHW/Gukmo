package com.gukmo.board.model;

public class HashtagVO {
	
	private int hashtag_num;
	private String hashtag;
	private int hashtag_cnt;
	private int week_cnt;
	private String fk_board_num;
	
	// select 용
	private String count; // 검색 순위 시 사용하는 카운트 수
	
	//기본생성자 protected
	protected HashtagVO(){}

	public HashtagVO(int hashtag_num, String hashtag, int hashtag_cnt, int week_cnt, String fk_board_num) {
		this.hashtag_num = hashtag_num;
		this.hashtag = hashtag;
		this.hashtag_cnt = hashtag_cnt;
		this.week_cnt = week_cnt;
		this.fk_board_num = fk_board_num;
	}

	
	public String getFk_board_num() {
		return fk_board_num;
	}

	//Getter
	public int getHashtag_num() {
		return hashtag_num;
	}

	public String getHashtag() {
		return hashtag;
	}

	public int getHashtag_cnt() {
		return hashtag_cnt;
	}

	public int getWeek_cnt() {
		return week_cnt;
	}

	public String getCount() {
		return count;
	}
	
	//확인용 toString Override
	@Override
	public String toString() {
		return "HashtagVO [hashtag_num=" + hashtag_num + ", hashtag=" + hashtag + ", hashtag_cnt=" + hashtag_cnt
				+ ", week_cnt=" + week_cnt + "]";
	}
	
	
	
	
	

	
	
	

	
	
	
	
}
