
package com.gukmo.board.model;

import java.util.Date;
import java.util.List;

import com.gukmo.board.common.MyUtil;

public class BoardVO {
	private String board_num;     	//게시글번호
	private String nickname;        //작성자닉네임
	private String category;        //카테고리
	private String detail_category; //상세카테고리
	private String subject;         //제목
	private String content;         //내용
	private Date write_date;        //작성일자
	private String views;           //조회수
	private String profile_image;   //작성자 프로필이미지
	private String comment_cnt;		//댓글수
	private String like_cnt;		//좋아요 수
	
	private String previousseq;      // 이전글번호
	private String previoussubject;  // 이전글제목
	private String nextseq;          // 다음글번호
	private String nextsubject;      // 다음글제목	
	
	//select 용도(TBL_MEMBER)
	private String writer_point;	//작성자 활동점수
	
	//select 용도(TBL_ACADEMY)
	private AcademyVO academy;
	
	//select 용도(TBL_CURRICULUM)
	private CurriculumVO curriculum;
	
	//select 용도
	private String dday;
    
	

	// 해시태그VO리스트(연관관계에 있는 필드)
	List<HashtagVO> hashtags;
	
	
	// 댓글VO리스트(연관관계에 있는 필드)
	List<CommentVO> comment;
	
	// 좋아요VO리스트(연관관계에 있는 필드)
	List<CommentVO> like;
	
	
	
	//기본생성자 protected로 막기
	protected BoardVO() {}


	public BoardVO(String board_num, String nickname, String category, String detail_category, String subject,
			String content, Date write_date, String views, String profile_image, String comment_cnt, String like_cnt,
			String previousseq, String previoussubject, String nextseq, String nextsubject, String writer_point,
			AcademyVO academy, CurriculumVO curriculum, List<HashtagVO> hashtags, List<CommentVO> comment, List<CommentVO> like) {
		this.board_num = board_num;
		this.nickname = nickname;
		this.category = category;
		this.detail_category = detail_category;
		this.subject = subject;
		this.content = content;
		this.write_date = write_date;
		this.views = views;
		this.profile_image = profile_image;
		this.comment_cnt = comment_cnt;
		this.like_cnt = like_cnt;
		this.previousseq = previousseq;
		this.previoussubject = previoussubject;
		this.nextseq = nextseq;
		this.nextsubject = nextsubject;
		this.writer_point = writer_point;
		this.academy = academy;
		this.curriculum = curriculum;
		this.hashtags = hashtags;
		this.comment = comment;
		this.comment = like;
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

	public void setContent(String content) {
		this.content = content;
	}



   public String getWrite_date() {
      return MyUtil.calculateTime(write_date); // 기존의 getter, setter에서 변경된 부분
   }



	public String getViews() {
		return views;
	}



	public String getProfile_image() {
		return profile_image;
	}



	public String getComment_cnt() {
		return comment_cnt;
	}



	public String getLike_cnt() {
		return like_cnt;
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



	public String getWriter_point() {
		return writer_point;
	}

	public String getDday() {
		return dday;
	}

	


	public AcademyVO getAcademy() {
		return academy;
	}



	public CurriculumVO getCurriculum() {
		return curriculum;
	}



	public List<HashtagVO> getHashtags() {
		return hashtags;
	}



	public List<CommentVO> getComment() {
		return comment;
	}		
	
	public List<CommentVO> getLike() {
		return like;
	}


	@Override
	public String toString() {
		return "BoardVO [board_num=" + board_num + ", nickname=" + nickname + ", category=" + category
				+ ", detail_category=" + detail_category + ", subject=" + subject + ", content=" + content
				+ ", write_date=" + write_date + ", views=" + views + ", profile_image=" + profile_image
				+ ", comment_cnt=" + comment_cnt + ", like_cnt=" + like_cnt + ", previousseq=" + previousseq
				+ ", previoussubject=" + previoussubject + ", nextseq=" + nextseq + ", nextsubject=" + nextsubject
				+ ", writer_point=" + writer_point + ", academy=" + academy + ", curriculum=" + curriculum
				+ ", hashtags=" + hashtags + ", comment=" + comment + ", like=" + like + "]";
	}



	

	

	
	
	
	
	
}
