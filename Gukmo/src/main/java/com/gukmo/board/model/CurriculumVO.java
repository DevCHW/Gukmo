package com.gukmo.board.model;

public class CurriculumVO {

	private String fk_board_num;
	private String core_technology;
	private String academy_name;
	private String curriculum_start_date;
	private String curriculum_end_date;
	
	
	public CurriculumVO(String fk_board_num, String core_technology, String academy_name, String curriculum_start_date,
			String curriculum_end_date) {
		this.fk_board_num = fk_board_num;
		this.core_technology = core_technology;
		this.academy_name = academy_name;
		this.curriculum_start_date = curriculum_start_date;
		this.curriculum_end_date = curriculum_end_date;
	}
	
	
	//Getter
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

	
	
	//확인용 toString() override
	@Override
	public String toString() {
		return "CurriculumVO [fk_board_num=" + fk_board_num + ", core_technology=" + core_technology + ", academy_name="
				+ academy_name + ", curriculum_start_date=" + curriculum_start_date + ", curriculum_end_date="
				+ curriculum_end_date + "]";
	}
	
	
	
}
