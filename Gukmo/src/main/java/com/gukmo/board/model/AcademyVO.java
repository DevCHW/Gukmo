package com.gukmo.board.model;

public class AcademyVO {

	private String fk_board_num;			//글번호
	private String representative_name;		//대표자이름
	private String address;					//학원주소
	private String phone;					//학원 문의처
	private String jurisdiction;			//관활기관명
	private String homepage; 				//학원 홈페이지 url
	private String academy_image;			//학원이미지
	
	//기본생성자 protected로 막기
	protected AcademyVO() {}

		
	//파라미터가 있는 생성자로만 값 주입 가능
	public AcademyVO(String fk_board_num, String representative_name, String address, String phone, String jurisdiction,
			String homepage, String academy_image) {
		super();
		this.fk_board_num = fk_board_num;
		this.representative_name = representative_name;
		this.address = address;
		this.phone = phone;
		this.jurisdiction = jurisdiction;
		this.homepage = homepage;
		this.academy_image = academy_image;
	}
	
	
	
	
	//Getter
	public String getFk_board_num() {
		return fk_board_num;
	}

	public String getRepresentative_name() {
		return representative_name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public String getHomepage() {
		return homepage;
	}

	public String getAcademy_image() {
		return academy_image;
	}


	@Override
	public String toString() {
		return "AcademyVO [fk_board_num=" + fk_board_num + ", representative_name=" + representative_name + ", address="
				+ address + ", phone=" + phone + ", jurisdiction=" + jurisdiction + ", homepage=" + homepage
				+ ", academy_image=" + academy_image + "]";
	}
	
	
	
	
	
	
	
}
