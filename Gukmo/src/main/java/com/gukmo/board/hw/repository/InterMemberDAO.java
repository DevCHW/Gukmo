package com.gukmo.board.hw.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;

public interface InterMemberDAO {

	/**
	 * 회원이 존재하는지 여부 검사
	 * @return 회원이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean idExistCheck(String userid);

	
	/**
	 * 가입된 닉네임이 존재하는지 여부 검사
	 * @return 가입된 닉네임이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean nicknameExistCheck(String nickname);

	
	/**
	 * 가입된 이메일이 존재하는지 여부 검사
	 * @return 가입된 이메일이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean emailExistCheck(String email);

	
	
	
	/**
	 * 가입된 교육기관명이 존재하는지 여부 검사
	 * @return 가입된 이메일이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean academyNameExistCheck(String academyName);
	
	
	
	
	/**
	 * tbl_member_login에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	int insert_member_login(Map<String,String> paraMap);
	
	
	/**
	 * tbl_member에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	int insert_member(Map<String,String> paraMap);

	
	
	/**
	 * tbl_academy_member에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	int insert_academy_member(Map<String, String> paraMap);
	
	
	/**
	 * 계정삭제하기
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
	 * 이메일 값으로 회원 아이디 알아내기
	 * @param email
	 * @return
	 */
	String getMyID(String email);


	/**
	 * 계정찾기 비밀번호 변경 해주기
	 * @param userid,passwd 인풋값
	 * @return 성공여부 result
	 */
	int editPasswd(Map<String, String> paraMap);

	
	
	/**
	 * 프사첨부를 안했을경우 회원정보 수정
	 */
	int editMyInfoWithOutFile(MemberVO member);


	/**
	 * 프로필사진을 첨부했을 때 회원정보수정
	 */
	int editMyInfo(Map<String,String> paraMap);


	/**
	 * 회원정보얻기
	 * @param 회원아이디
	 * @return 회원정보
	 */
	MemberVO getUser(String userid);


	/**
	 * 내가 글작성한 게시물 활동내역 가져오기
	 * @param nickname
	 * @return
	 */
	ActivityVO getActivitiesByBoard(String nickname);


	
	/**
	 * 이메일 변경시 업데이트해주기
	 */
	int editEmail(Map<String,String> paraMap);


	/**
	 * 기존비밀번호와 같은지 확인하기
	 * @param 사용자가 입력한 passwd,로그인된 userid
	 * @return 값이 있으면 1 , 없다면 0
	 */
	int samePasswdCheck(Map<String, String> paraMap);


	/**
	 * 닉네임으로 
	 */
	Map<String,String> getProfileByNickname(Map<String, String> paraMap);


	/**
	 * 교육기관회원 insert
	 */
	int insertAcaMember(Map<String, String> paraMap);

	/**
	 * 상태 업데이트하기
	 */
	int updateStatus(Map<String, String> paraMap);


	/**
	 * 소셜연동회원인지 체크하기
	 * @param email
	 */
	boolean snsConnectionCheck(String email);


	/**
	 * 게시판에서도 프사 변경해주기
	 */
	int changeBoardByProfileImg(Map<String, String> paraMap);

	/**
	 * 총 회원 수 세기
	 */
	int cntMember();


	


	


	


	




	

}
