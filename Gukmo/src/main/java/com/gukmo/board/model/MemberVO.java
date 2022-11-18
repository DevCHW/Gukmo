package com.gukmo.board.model;

public class MemberVO {
	// 로그인 관련
	private String userid;				//유저아이디
	private String passwd;				//비밀번호
	private String status;				//상태(활동,정지,휴면,대기)
	private String update_passwd_date;	//마지막비밀번호변경일자
	
	// 일반회원,교육기관회원 공통field
	private String email;				//이메일
	private String email_acept;			//이메일 수신동의(0:수신거부 1:수신동의)
	private String nickname;			//닉네임
	private String point;				//활동점수
	private String join_date;			//가입일자
	private String profile_image;		//프로필이미지 파일명
	
	// 교육기관회원 field
	private String academy_name;		//교육기관명
	private String company_num;			//사업자번호
	private String homepage;			//홈페이지URL
	private String phone;				//핸드폰번호
	
	// 일반회원 field
	private String username;				//회원 이름



	// 기본 생성자 일부러 protected로 막음
	protected MemberVO(){};
	
	
	// 파라미터가있는 생성자로만 값 주입 가능, Setter도 막음
	public MemberVO(String userid, String passwd, String status, String update_passwd_date, String email,
					String email_acept, String nickname, String point, String join_date, String profile_image,
					String academy_name, String company_num, String homepage, String phone, String username) {
		this.userid = userid;
		this.passwd = passwd;
		this.status = status;
		this.update_passwd_date = update_passwd_date;
		this.email = email;
		this.email_acept = email_acept;
		this.nickname = nickname;
		this.point = point;
		this.join_date = join_date;
		this.profile_image = profile_image;
		this.academy_name = academy_name;
		this.company_num = company_num;
		this.homepage = homepage;
		this.phone = phone;
		this.username = username;
	}

	// Getter
	public String getUserid() {
		return userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getStatus() {
		return status;
	}
	public String getUpdate_passwd_date() {
		return update_passwd_date;
	}
	public String getEmail() {
		return email;
	}
	public String getEmail_acept() {
		return email_acept;
	}
	public String getNickname() {
		return nickname;
	}
	public String getPoint() {
		return point;
	}
	public String getJoin_date() {
		return join_date;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public String getAcademy_name() {
		return academy_name;
	}
	public String getCompany_num() {
		return company_num;
	}
	public String getHomepage() {
		return homepage;
	}
	public String getPhone() {
		return phone;
	}

	public String getUsername() {
		return username;
	}
	
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", passwd=" + passwd + ", status=" + status + ", update_passwd_date="
				+ update_passwd_date + ", email=" + email + ", email_acept=" + email_acept + ", nickname=" + nickname
				+ ", point=" + point + ", join_date=" + join_date + ", profile_image=" + profile_image
				+ ", academy_name=" + academy_name + ", company_num=" + company_num + ", homepage=" + homepage
				+ ", phone=" + phone + "]";
	}
	
	
	
}
