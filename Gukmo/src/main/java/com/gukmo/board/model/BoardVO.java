package com.gukmo.board.model;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {

	private String board_num;        // 글번호 
	private String nickname;    	 // 작성자닉네임
	private String category;         // 카테고리
	private String detail_category;  // 상세카테고리
	private String subject;      	 // 글제목 
	private String content;          // 글내용
	private String write_date;    	 // 작성일자
	private String views;      	 	 // 조회수
	private String profile_image;    // 작성자 프로필이미지명   1:사용가능한 글,  0:삭제된글 
	
	// 좋아요
	private String fk_userid;
	private String fk_board_num;
	
	private String previousseq;      // 이전글번호
	private String previoussubject;  // 이전글제목
	private String nextseq;          // 다음글번호
	private String nextsubject;      // 다음글제목
	
	private String commentCount;     // 댓글수 
		
	private String groupno;
	private String fk_seq;
		
	private String depthno;
	
	private MultipartFile attach;
	private String fileName;    // WAS(톰캣)에 저장될 파일명(2022103109271535243254235235234.png) 
	private String orgFilename; // 진짜 파일명(강아지.png)  // 사용자가 파일을 업로드 하거나 파일을 다운로드 할때 사용되어지는 파일명
	private String fileSize;    // 파일크기 
		
    protected BoardVO() {}

	public BoardVO(String board_num, String nickname, String category, String detail_category, String subject,
			String content, String write_date, String views, String profile_image, String fk_userid,
			String fk_board_num) {
		this.board_num = board_num;
		this.nickname = nickname;
		this.category = category;
		this.detail_category = detail_category;
		this.subject = subject;
		this.content = content;
		this.write_date = write_date;
		this.views = views;
		this.profile_image = profile_image;
		this.fk_userid = fk_userid;
		this.fk_board_num = fk_board_num;
	}

	public String getBoard_num() {
		return board_num;
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


	public String getWrite_date() {
		return write_date;
	}


	public String getViews() {
		return views;
	}


	public String getProfile_image() {
		return profile_image;
	}


	public String getFk_userid() {
		return fk_userid;
	}


	public String getFk_board_num() {
		return fk_board_num;
	}


	public String getPreviousseq() {
		return previousseq;
	}


	public String getPrevioussubject() {
		return previoussubject;
	}


	public String getNextseq() {
		return nextseq;
	}


	public String getNextsubject() {
		return nextsubject;
	}


	public String getCommentCount() {
		return commentCount;
	}


	public String getGroupno() {
		return groupno;
	}


	public String getFk_seq() {
		return fk_seq;
	}


	public String getDepthno() {
		return depthno;
	}


	public MultipartFile getAttach() {
		return attach;
	}


	public String getFileName() {
		return fileName;
	}

	public String getOrgFilename() {
		return orgFilename;
	}


	public String getFileSize() {
		return fileSize;
	}
	
}


