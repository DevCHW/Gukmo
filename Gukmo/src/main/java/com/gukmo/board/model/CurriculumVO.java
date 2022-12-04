package com.gukmo.board.model;

public class CurriculumVO {

	private String fk_board_num;			//글번호
	private String core_technology;			//핵심기술
	private String academy_name;			//학원명
	private String curriculum_start_date;	//과정시작일자
	private String curriculum_end_date;		//과정끝일자
	private String recuitment_end_date;		//모집마감일
	private String cnt_recruits;			//모집인원
	private String join_url;				//신청URL
	
	
	//기본생성자 protected로 막기
	protected CurriculumVO() {}
	
	
	//파라미터가 있는 생성자만으로 값 주입
	public CurriculumVO(String fk_board_num, String core_technology, String academy_name, String curriculum_start_date,
			String curriculum_end_date, String recuitment_end_date, String cnt_recruits, String join_url) {
		this.fk_board_num = fk_board_num;
		this.core_technology = core_technology;
		this.academy_name = academy_name;
		this.curriculum_start_date = curriculum_start_date;
		this.curriculum_end_date = curriculum_end_date;
		this.recuitment_end_date = recuitment_end_date;
		this.cnt_recruits = cnt_recruits;
		this.join_url = join_url;
	}


	public String getFk_board_num() {
		return fk_board_num;
	}
	public String getCore_technology() {
		return core_technology;
	}
	public String getAcademy_name() {
		return academy_name;
	}
	public String getCurriculum_start_date() {
		return curriculum_start_date;
	}
	public String getCurriculum_end_date() {
		return curriculum_end_date;
	}
	public String getRecuitment_end_date() {
		return recuitment_end_date;
	}
	public String getCnt_recruits() {
		return cnt_recruits;
	}
	public String getJoin_url() {
		return join_url;
	}

	
	
	//확인용 toString override
	@Override
	public String toString() {
		return "CurriculumVO [fk_board_num=" + fk_board_num + ", core_technology=" + core_technology + ", academy_name="
				+ academy_name + ", curriculum_start_date=" + curriculum_start_date + ", curriculum_end_date="
				+ curriculum_end_date + ", recuitment_end_date=" + recuitment_end_date + ", cnt_recruits="
				+ cnt_recruits + ", join_url=" + join_url + "]";
	}
	
	
	
	
	
}
