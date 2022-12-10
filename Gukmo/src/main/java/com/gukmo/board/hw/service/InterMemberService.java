package com.gukmo.board.hw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;

public interface InterMemberService {

	/**
	 * 일반회원 가입하기
	 * @param 사용자가 입력한 정보가 들어있는 MemberVO 객체
	 */
	void saveNormalMember(Map<String,String> paraMap) throws Throwable;

	
	/**
	 * 교육기관회원 회원 가입하기
	 * @param 사용자가 입력한 정보가 들어있는 MemberVO 객체
	 */
	void saveAcademyMember(Map<String, String> paraMap);
	

	/**
	 * 계정 삭제하기
	 * @param attribute
	 */
	int memberDelete(String userid);

	
	
	/**
	 * 로그인되어있는 유저의 활동내역 리스트 얻기
	 * @param userid
	 * @return 활동내역 리스트
	 */
	List<ActivityVO> getActivities(Map<String, String> paraMap);


	/**
	 * 유저의 활동내역 총 갯수를 알아오기
	 * @param 검색어,유저아이디
	 * @return 활동내역 총 갯수
	 */
	int getTotalActivities(Map<String, String> paraMap);


	/**
	 * 계정찾기 메일 보내기
	 * @param 사용자의 이메일
	 * @return 이메일 성공여부가 담겨있는 JSON형식 String
	 */
	String sendEmailByMyId(String email,HttpServletRequest request);


	/**
	 * 회원가입이메일 인증코드 전송
	 * @param 사용자의 이메일
	 * @return 이메일 성공여부가 담겨있는 JSON형식 String
	 */
	String sendEmailCertificationCode(String email, HttpServletRequest request);
	
	
	/**
	 * 이메일 값으로 회원 아이디 알아내기
	 * @param email
	 * @return
	 */
	String getMyID(String email);


	/**
	 * 계정찾기 비밀번호 변경 해주기
	 * @param userid,passwd 인풋값
	 * @return 성공여부
	 */
	int editPasswd(Map<String, String> paraMap);

	
	
	/**
	 * 먼저 있던 프로필 이미지 파일 지우고, 멤버정보 수정해주고,새로운 프로필이미지 넣기, 
	 * @param member
	 * @param profileImage
	 */
	int editMyInfo(MemberVO member,Map<String,String> paraMap);


	/**
	 * 프사첨부를 안했을경우 회원정보 수정
	 * @param member
	 * @param userid
	 * @return
	 */
	int editMyInfoWithOutNoFile(MemberVO member);


	/**
	 * 회원정보얻기
	 * @param 회원아이디
	 * @return 회원정보
	 */
	MemberVO getUser(String userid);


	/**
	 * 
	 * @param nickname
	 * @return
	 */
	ActivityVO getActivitiesByBoard(String nickname);


	
	/**
	 * 이메일 변경시 업데이트해주기
	 * @return 변경에 성공하면 true, 실패하면 false
	 */
	boolean editEmail(Map<String,String> paraMap);


	
	/**
	 * 마이페이지에서 비밀번호 변경 시 업데이트 해주기
	 * @param 사용자가 입력한 비밀번호 값,로그인중인 유저의 아이디
	 * @return 변경에 성공하면 true, 실패하면 false
	 */
	boolean editPasswdWithUserid(Map<String, String> paraMap);

	
	/**
	 * 기존비밀번호와 같은지 확인하기
	 * @param 사용자가 입력한 passwd 로그인된 userid
	 * @return 같다면 true, 없다면 false
	 */
	boolean samePasswdCheck(Map<String, String> paraMap);


	
	/**
	 * 다른사람의 유저의 활동내역 리스트 얻기
	 * @param 닉네임
	 * @return 활동내역 리스트
	 */
	List<ActivityVO> getActivityOther(Map<String, String> paraMap);

	/**
	 * 유저의 활동내역 총 갯수를 알아오기(닉네임으로)
	 * @param 닉네임
	 * @return 활동내역 총 갯수
	 */
	int getTotalActivityOther(Map<String, String> paraMap);


	/**
	 * 닉네임으로 프로필이미지 얻기
	 */
	Map<String,String> getProfileByNickname(Map<String, String> paraMap);


	


	




}
