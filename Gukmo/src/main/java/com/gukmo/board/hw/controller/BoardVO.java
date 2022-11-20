package com.gukmo.board.hw.controller;

import java.util.Date;
import java.util.List;

import com.gukmo.board.model.HashtagVO;

public class BoardVO {
	private String BOARD_NUM;     	//게시글번호
	private String nickname;        //작성자닉네임
	private String category;        //카테고리
	private String detail_category; //상세카테고리
	private String subject;         //제목
	private String content;         //내용
	private Date write_date;        //작성일자
	private String views;           //조회수
	private String profile_image;   //작성자 프로필이미지
	
	// 해시태그
	List<HashtagVO> hashtag;
	
	
	//기본생성자 protected로 막기
	protected BoardVO() {}
	
	//파라미터가있는 생성자만을 통해서 값 주입
	public BoardVO(String bOARD_NUM, String nickname, String category, String detail_category, String subject,
			String content, Date write_date, String views, String profile_image) {
		BOARD_NUM = bOARD_NUM;
		this.nickname = nickname;
		this.category = category;
		this.detail_category = detail_category;
		this.subject = subject;
		this.content = content;
		this.write_date = write_date;
		this.views = views;
		this.profile_image = profile_image;
	}
	
	
	public String getBOARD_NUM() {
		return BOARD_NUM;
	}
	public String getNickname() {
		return nickname;
	}
	public String getCategory() {
		return category;
	}
	public String getDetail_category() {
		return detail_category;
	}
	public String getSubject() {
		return subject;
	}
	public String getContent() {
		return content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public String getViews() {
		return views;
	}
	public String getProfile_image() {
		return profile_image;
	}
	
	
}
