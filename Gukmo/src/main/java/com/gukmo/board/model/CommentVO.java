package com.gukmo.board.model;

import java.util.Date;

import com.gukmo.board.common.MyUtil;

public class CommentVO {
	private String comment_num;            
	private String comment_level;                 
	private String content;
	private String nickname;                      
	private String parent_write_nickname;          
	private String cmt_board_num;                          
	private String fk_comment_num;                     
	private Date write_date;
	private String point;
	private String totalcount;
	private String comment_like_cnt;	
	private String comment_of_comment_cnt;
	
	// 셀렉용
	private String profile_image;
	
	// 댓글 수
	public String getTotalcount() {
		return totalcount;
	}

	// 포인트 불
	public String getPoint() {
		return point;
	}

	//기본생성자 protected
	protected CommentVO(){};
	
	//파라미터가 있는 생성자
	public CommentVO(String comment_num, String comment_level, String content, String nickname,
			String parent_write_nickname, String cmt_board_num, String fk_comment_num, Date write_date, String comment_like_cnt, String point, String comment_of_comment_cnt, String profile_image) {
		this.comment_num = comment_num;
		this.comment_level = comment_level;
		this.content = content;
		this.nickname = nickname;
		this.parent_write_nickname = parent_write_nickname;
		this.cmt_board_num = cmt_board_num;
		this.fk_comment_num = fk_comment_num;
		this.write_date = write_date;
		this.comment_like_cnt = comment_like_cnt;
		this.point = point;
		this.comment_of_comment_cnt = comment_of_comment_cnt;
		this.profile_image = profile_image;
	}
	
	
	
	
	public String getComment_of_comment_cnt() {
		return comment_of_comment_cnt;
	}

	public String getComment_like_cnt() {
		return comment_like_cnt;
	}

	//Getter
	public String getComment_num() {
		return comment_num;
	}
	public String getComment_level() {
		return comment_level;
	}
	public String getContent() {
		return content;
	}
	public String getNickname() {
		return nickname;
	}
	public String getParent_write_nickname() {
		return parent_write_nickname;
	}
	public String getCmt_board_num() {
		return cmt_board_num;
	}
	public String getFk_comment_num() {
		return fk_comment_num;
	}
	public String getWrite_date() {
		return MyUtil.calculateTime(write_date); // 기존의 getter, setter에서 변경된 부분
	}			
	public String getProfile_image() {
		return profile_image;
	}

	

	//확인용 toString Override
	@Override
	public String toString() {
		return "CommentVO [comment_num=" + comment_num + ", comment_level=" + comment_level + ", content=" + content
				+ ", nickname=" + nickname + ", parent_write_nickname=" + parent_write_nickname + ", cmt_board_num="
				+ cmt_board_num + ", fk_comment_num=" + fk_comment_num + ", write_date=" + write_date + ", point="
				+ point + ", totalcount=" + totalcount + ", comment_like_cnt=" + comment_like_cnt
				+ ", comment_of_comment_cnt=" + comment_of_comment_cnt + ", profile_image=" + profile_image + "]";
	}
	
	
	
	
	
	
	
	
}
