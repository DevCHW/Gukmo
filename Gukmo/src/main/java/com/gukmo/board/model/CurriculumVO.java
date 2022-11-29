package com.gukmo.board.model;

import java.util.List;

public class CurriculumVO {

	private int fk_board_num;
	private String core_technology;
	private String academy_name;
	private String c_start_date; // curriculum_start_date
	private String curriculum_period;
	
	
	// 게시글VO 리스트(연관관계에 있는 필드)
	List<BoardVO> board;
	
	
	public int getFk_board_num() {
		return fk_board_num;
	}

	public void setFk_board_num(int fk_board_num) {
		this.fk_board_num = fk_board_num;
	}
	public String getCore_technology() {
		return core_technology;
	}
	public void setCore_technology(String core_technology) {
		this.core_technology = core_technology;
	}
	public String getAcademy_name() {
		return academy_name;
	}
	public void setAcademy_name(String academy_name) {
		this.academy_name = academy_name;
	}
	public String getCurriculum_start_date() {
		return c_start_date;
	}
	public void setCurriculum_start_date(String curriculum_start_date) {
		this.c_start_date = curriculum_start_date;
	}
	public String getCurriculum_period() {
		return curriculum_period;
	}
	public void setCurriculum_period(String curriculum_period) {
		this.curriculum_period = curriculum_period;
	}

	
	
	
	
	public List<BoardVO> getBoard() {
		return board;
	}
	public void setBoard(List<BoardVO> board) {
		this.board = board;
	}
	
}
