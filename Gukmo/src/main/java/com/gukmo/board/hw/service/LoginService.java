package com.gukmo.board.hw.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.common.Sha256;
import com.gukmo.board.hw.repository.InterLoginDAO;
import com.gukmo.board.model.MemberVO;

@Service
public class LoginService implements InterLoginService{
	@Autowired
	InterLoginDAO dao;
	
	/**
	 * 회원의 상태 체크하기(활동,정지,휴면,대기,비밀번호 변경시점 3개월)
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 활동중이라면 "활동" 정지회원이라면 "정지" 휴면회원이라면 "휴면" 승인대기라면 "대기" 활동중이면서 비밀번호 변경시점이 3개월 이상이라면 "비밀번호 변경 권장"
	 */
	@Override
	public String statusCheck(String userid) {
		MemberVO user = dao.statusCheck(userid);
		String status = user.getStatus();
		
		if(!"휴면".equals(user.getStatus())) {
			try {
				status = restMemberUpdate(userid,status);	//로그인되어질 회원이 로그인한지 1년이 지났다면 휴면으로 바꿔주기
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		int lastUpdateDay = dao.getLastUpdateDay(userid);	//비밀번호 변경날짜를 알아오기
		if("활동".equals(status) && lastUpdateDay > 90) {		//user가 활동중인 상태지만 비밀번호 변경날짜가 3개월 이상 지났다면
			status = "비밀번호 변경 권장";
		}
		return status;
	}
	
	
	/**
	 * 회원이 로그인한지 1년이 지났다면 상태를 휴면으로 업데이트해주기
	 * @throws SQLException 업데이트 실패하면 예외발생
	 */
	public String restMemberUpdate(String userid,String status) throws SQLException {
		
		int lastLoginDay = dao.getLastLoginday(userid); // 마지막로그인날짜가 몇일전인지 알아내기
		
		if(lastLoginDay > 365) {	//마지막 로그인날짜가 365일 이상 되었다면
			int n = dao.editUserStatus_rest(userid);	//상태를 휴면으로 업데이트 해주기
			if(n==1) {	//업데이트에 성공하였다면
				return "휴면";
			}
			else {	//업데이트에 실패하였다면
				throw new SQLException("휴면회원으로 업데이트가 실패하였습니다.");
			}
		}
		return status;
	}


	/**
	 * 회원 아이디,비밀번호 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 아이디,비밀번호를 맞게 입력하였다면 true, 아니라면 false 반환
	 */
	@Override
	public boolean userExistCheck(Map<String, String> paraMap) {
		paraMap.put("passwd",Sha256.encrypt(paraMap.get("passwd")));	//비밀번호 단방향 암호화해서 덮어씌우기
		boolean userExist = dao.userExistCheck(paraMap);
		return userExist;
	}
	
	
	
	/**
	 * 관리자 로그인 검사
	 * @param 유저가 입력한 관리자아이디, 유저가 입력한 비밀번호
	 * @return 관리자 아이디,비밀번호를 맞게 입력하였다면 true, 아니라면 false 반환
	 */
	@Override
	public boolean adminExistCheck(Map<String, String> paraMap) {
		paraMap.put("passwd",Sha256.encrypt(paraMap.get("passwd")));	//비밀번호 단방향 암호화해서 덮어씌우기
		boolean userExist = dao.adminExistCheck(paraMap);
		return userExist;
	}

	
	
	/**
	 * 로그인 완료처리하기(로그인 기록테이블에 로그인 기록insert, user조회)
	 * @param 유저아이디,클라이언트 ip(userid,client_ip)
	 * @return MemberVO 타입 객체
	 */
	@Override
	public MemberVO login_complete(Map<String, String> paraMap) {
		dao.loginRecordSave(paraMap);	//로그인 기록테이블에 기록하기
		return dao.getUser(paraMap.get("userid"));
	}


	
	
	
	
	
	

	
	
}
