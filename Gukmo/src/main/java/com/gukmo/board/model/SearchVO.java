package com.gukmo.board.model;

import javax.xml.crypto.Data;

public class SearchVO {

	private int search_num;        
	private String keyword;
	private Data search_date;       
	private String search_ip;
	private String userid;
	
	// select 용
	private String count; // 검색어 순위 카운트용
	private String start_data; // 이전 날짜 검색 때문에...
	private String end_date;  // 이전 날짜 검색 때문에...
	
	
	public int getSearch_num() {
		return search_num;
	}
	public String getKeyword() {
		return keyword;
	}
	public Data getSearch_date() {
		return search_date;
	}
	public String getSearch_ip() {
		return search_ip;
	}
	public String getUserid() {
		return userid;
	}
	
	
	public String getCount() {
		return count;
	}
	
	
	@Override
	public String toString() {
		return "SearchVO [search_num=" + search_num + ", keyword=" + keyword + ", search_date=" + search_date
				+ ", search_ip=" + search_ip + ", userid=" + userid + "]";
	}
	
	
	
}
