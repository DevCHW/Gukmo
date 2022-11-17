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
		
    public BoardVO() {}

	public BoardVO(String board_num, String nickname, String category, String detail_category, String subject,
			String content, String write_date, String views, String profile_image, String fk_userid,
			String fk_board_num) {
		super();
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

	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetail_category() {
		return detail_category;
	}

	public void setDetail_category(String detail_category) {
		this.detail_category = detail_category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getFk_userid() {
		return fk_userid;
	}

	public void setFk_userid(String fk_userid) {
		this.fk_userid = fk_userid;
	}

	public String getFk_board_num() {
		return fk_board_num;
	}

	public void setFk_board_num(String fk_board_num) {
		this.fk_board_num = fk_board_num;
	}

	public String getPreviousseq() {
		return previousseq;
	}

	public void setPreviousseq(String previousseq) {
		this.previousseq = previousseq;
	}

	public String getPrevioussubject() {
		return previoussubject;
	}

	public void setPrevioussubject(String previoussubject) {
		this.previoussubject = previoussubject;
	}

	public String getNextseq() {
		return nextseq;
	}

	public void setNextseq(String nextseq) {
		this.nextseq = nextseq;
	}

	public String getNextsubject() {
		return nextsubject;
	}

	public void setNextsubject(String nextsubject) {
		this.nextsubject = nextsubject;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}

	public String getFk_seq() {
		return fk_seq;
	}

	public void setFk_seq(String fk_seq) {
		this.fk_seq = fk_seq;
	}

	public String getDepthno() {
		return depthno;
	}

	public void setDepthno(String depthno) {
		this.depthno = depthno;
	}

	public MultipartFile getAttach() {
		return attach;
	}

	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrgFilename() {
		return orgFilename;
	}

	public void setOrgFilename(String orgFilename) {
		this.orgFilename = orgFilename;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
}


