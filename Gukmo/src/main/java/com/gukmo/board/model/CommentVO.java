package com.gukmo.board.model;

public class CommentVO {
	private String comment_num;            
	private String comment_level;                 
	private String content;
	private String nickname;                      
	private String parent_write_nickname;          
	private String board_num;                          
	private String fk_comment_num;                     
	private String write_date;
	
	
	//기본생성자 protected
	protected CommentVO(){};
	
	//파라미터가 있는 생성자
	public CommentVO(String comment_num, String comment_level, String content, String nickname,
			String parent_write_nickname, String board_num, String fk_comment_num, String write_date) {
		this.comment_num = comment_num;
		this.comment_level = comment_level;
		this.content = content;
		this.nickname = nickname;
		this.parent_write_nickname = parent_write_nickname;
		this.board_num = board_num;
		this.fk_comment_num = fk_comment_num;
		this.write_date = write_date;
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
	public String getBoard_num() {
		return board_num;
	}
	public String getFk_comment_num() {
		return fk_comment_num;
	}
	public String getWrite_date() {
		return write_date;
	}

	
	
	//확인용 toString Override
	@Override
	public String toString() {
		return "CommentVO [comment_num=" + comment_num + ", comment_level=" + comment_level + ", content=" + content
				+ ", nickname=" + nickname + ", parent_write_nickname=" + parent_write_nickname + ", board_num="
				+ board_num + ", fk_comment_num=" + fk_comment_num + ", write_date=" + write_date + "]";
	}
	
	
	
	
	
	
	
}
