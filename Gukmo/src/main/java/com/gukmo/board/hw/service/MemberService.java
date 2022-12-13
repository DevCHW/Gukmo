package com.gukmo.board.hw.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.common.FileManager;
import com.gukmo.board.common.MyUtil;
import com.gukmo.board.common.Sha256;
import com.gukmo.board.hw.email.GoogleMail;
import com.gukmo.board.hw.repository.InterMemberDAO;
import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;

@Service
public class MemberService implements InterMemberService{
	
	@Autowired
	private InterMemberDAO dao;
	
	@Autowired
	private GoogleMail mail;
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;
	
	/**
	 * 일반회원 가입하기
	 * @param 사용자가 입력한 정보가 들어있는 MemberVO 객체
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public void saveNormalMember(Map<String,String> paraMap) throws Throwable{
		paraMap.put("passwd",Sha256.encrypt(paraMap.get("passwd")));//비밀번호 단방향 암호화,
		int result1 = dao.insert_member_login(paraMap);	//tbl_member_login에 insert
		int result2 = 0;
		
		if(result1==1) {	//tbl_member에 insert가 성공시
		   result2 = dao.insert_member(paraMap);	//tbl_member에 insert
		}
		
		if(result1*result2 != 1) {
			Integer.parseInt("일반 회원가입실패했어요");	//오류내기
		}
		
	}
	
	/**
	 * 교육기관회원 회원 가입하기
	 * @param 사용자가 입력한 정보가 들어있는 MemberVO 객체
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public void saveAcademyMember(Map<String, String> paraMap) {
		paraMap.put("passwd",Sha256.encrypt(paraMap.get("passwd")));//비밀번호 단방향 암호화,
	    
		int result1 = dao.insert_member_login(paraMap);	//tbl_member_login에 insert
		int result2 = 0;
		int result3 = 0;
		
		if(result1 == 1) {	//tbl_member에 insert가 성공시
			result2 = dao.insert_member(paraMap);	//tbl_member에 insert
			
			if(result2 == 1) {
				result3 = dao.insert_academy_member(paraMap);
			}
		}
		
		if(result1*result2*result3 != 1) {
			Integer.parseInt("교육기관 회원가입실패했어요");	//오류내기
		}
		
	}

	
	
	/**
	 * 계정삭제하기
	 */
	@Override
	public int memberDelete(String userid) {
		int result = dao.memberDelete(userid);
		
		return result;
	}



	
	/**
	 * 로그인되어있는 유저의 활동내역 리스트 얻기
	 * @param userid
	 * @return 활동내역 리스트
	 */
	@Override
	public List<ActivityVO> getActivities(Map<String, String> paraMap) {
		List<ActivityVO> activities = dao.getActivities(paraMap);
		return activities;
	}



	
	/**
	 * 유저의 활동내역 총 갯수를 알아오기
	 * @param 검색어,유저아이디
	 * @return 활동내역 총 갯수
	 */
	@Override
	public int getTotalActivities(Map<String, String> paraMap) {
		int totalCount = dao.getTotalActivities(paraMap);
		return totalCount;
	}



	/**
	 * 계정찾기 메일 보내기
	 * @param 사용자의 이메일,request 객체
	 * @return 이메일 성공여부가 담겨있는 JSON형식 String
	 */
	@Override
	public String sendEmailByMyId(String email,HttpServletRequest request) {
		
		boolean sendMailSuccess = false; // 메일이 정상적으로 전송되었는지 유무를 알아오기 위한 용도
		
		// 이메일 제목 설정
        String subject = "계정찾기 이메일 전송";
        
        // UUID 생성
        String uuid = UUID.randomUUID().toString();
        
        // 생성된 uuid 값을 세션에 넣기
        HttpSession session = request.getSession();
        session.setAttribute("uuid", uuid);
        
        // 세션 생명시간 설정하기
        session.setMaxInactiveInterval(60*3);	//180초(3분)으로 설정함
        
		// 이메일에 보낼 메세지(계정을찾을수있는 링크 보내주기)
		String message = "<a href='http://localhost:9090"+request.getContextPath()+"/changePwd.do?email="+email+"&uuid="+uuid+"'>"
					   +   "계정을 찾으시려면 클릭하세요. 이 링크는 3분뒤 만료됩니다. "
					   + "</a>";
		try {
			mail.sendmail(email, subject, message);
			sendMailSuccess = true;	//메일 전송했음을 기록함.
			
		} catch(Exception e) {	//메일 전송이 실패한 경우
			sendMailSuccess = false;	//메일 전송 실패했음을 기록함.
		}//end of try-catch()----
		
		//제이슨 객체생성
		JSONObject jsonObj = new JSONObject();
		//제이슨에 값 담기
		jsonObj.put("sendMailSuccess", sendMailSuccess);
		jsonObj.put("message", message);
		
		return jsonObj.toString();
	}
	
	/**
	 * 이메일 값으로 회원 아이디 알아내기
	 * @param email
	 * @return
	 */
	@Override
	public String getMyID(String email) {
		String userid = dao.getMyID(email);
		return userid;
	}



	/**
	 * 계정찾기 비밀번호 변경 해주기
	 * @param email,passwd 인풋값
	 * @return 성공여부 result
	 */
	@Override
	public int editPasswd(Map<String, String> paraMap) {
		paraMap.put("passwd", Sha256.encrypt(paraMap.get("passwd"))); //암호화 해서 다시 넣기
		int result = dao.editPasswd(paraMap);
		return result;
	}
	
	
	/**
	 * 기존비밀번호와 같은지 확인하기
	 * @param 사용자가 입력한 passwd,로그인된 userid
	 * @return 같다면 true, 없다면 false
	 */
	@Override
	public boolean samePasswdCheck(Map<String, String> paraMap) {
		paraMap.put("passwd", Sha256.encrypt(paraMap.get("passwd"))); //암호화 해서 다시 넣기
		int result = dao.samePasswdCheck(paraMap);
		
		boolean samePasswd = result == 1?true:false;
		return samePasswd;
	}
	
	
	/**
	 * 마이페이지에서 비밀번호 변경 시 업데이트 해주기
	 * @param 사용자가 입력한 비밀번호 값,로그인중인 유저의 아이디
	 * @return 변경에 성공하면 true, 실패하면 false
	 */
	@Override
	public boolean editPasswdWithUserid(Map<String, String> paraMap) {
		paraMap.put("passwd", Sha256.encrypt(paraMap.get("passwd"))); //암호화 해서 다시 넣기
		int result = dao.editPasswd(paraMap);
		boolean editPasswdSuccess = result == 1?true:false;	//성공하면 true 실패하면 false
		return editPasswdSuccess;
	}
	
	
	/**
	 * 프사첨부를 안했을경우 회원정보 수정
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int editMyInfoWithOutNoFile(MemberVO member) {
		int result = dao.editMyInfoWithOutFile(member);	// 멤버테이블에서 정보 업데이트하기
		return result; 
	}



	
	/**
	 * 회원정보얻기
	 * @param 회원아이디
	 * @return 회원정보
	 */
	@Override
	public MemberVO getUser(String userid) {
		MemberVO user = dao.getUser(userid);
		return user;
	}


	
	/**
	 * 내가 글작성한 게시물 활동내역 가져오기
	 * @param nickname
	 * @return
	 */
	@Override
	public ActivityVO getActivitiesByBoard(String nickname) {
		ActivityVO activities = dao.getActivitiesByBoard(nickname);
		return activities;
	}


	/**
	 * 회원가입이메일 인증코드 전송
	 * @param 사용자의 이메일
	 * @return 이메일 성공여부가 담겨있는 JSON형식 String
	 */
	@Override
	public String sendEmailCertificationCode(String email, HttpServletRequest request) {
		boolean sendMailSuccess = false; // 메일이 정상적으로 전송되었는지 유무를 알아오기 위한 용도
		
		// 이메일 제목 설정
        String subject = "[국비의모든것] 회원가입을 환영합니다.";
        
        // 이메일인증코드 생성(10자리의 난수)
        String certificationCode = MyUtil.getAuthKey(10);
        
		// 이메일에 보낼 메세지(계정을찾을수있는 링크 보내주기)
		String message = "<div style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 540px; height: 600px; border-top: 4px solid #208EC9; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"
						   +"<h1 style='margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;'>"
						   +"<span style='font-size: 20px; margin: 0 0 10px 3px; font-weight:bold;'>국비의모든것</span><br />"
						   +"<span style='color: #208EC9;'>메일인증</span> 안내입니다."
						   +"</h1>"
						   +"<p style='font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;'>"
						      +"안녕하세요.<br />"  
						      +"국비의모든것에 가입해 주셔서 진심으로 감사드립니다.<br />" 
						      +"아래 <b style='color: #208EC9;'>인증코드</b> 를 복사하여 회원가입 페이지로 돌아가서 <br/>회원가입을 완료해주세요.<br />"  
						      +"감사합니다."
						   +"</p>"
						   + "<div style='border:solid 1px #208EC9; font-size:20px; border-radius:10px; text-align:center; font-weight:bold;'>"
						   + "&nbsp;<span style='color:#208EC9;'>인증코드&nbsp;:&nbsp;</span>"
						     + "<span style='color: red; text-decoration: none;'>"
						       + "<p style='display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; line-height: 45px; vertical-align: middle; font-size: 16px;'>"
						         + certificationCode
						       + "</p>"
						     + "</span>"
						   + "</div>"
						 + "</div>";
		try {
			mail.sendmail(email, subject, message);
			sendMailSuccess = true;	//메일 전송했음을 기록함.
			
		} catch(Exception e) {	//메일 전송이 실패한 경우
			sendMailSuccess = false;	//메일 전송 실패했음을 기록함.
		}//end of try-catch()----
		
		//제이슨 객체생성
		JSONObject jsonObj = new JSONObject();
		//제이슨에 값 담기
		jsonObj.put("sendMailSuccess", sendMailSuccess);
		jsonObj.put("certificationCode", certificationCode);
		jsonObj.put("message", message);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 프로필사진을 첨부했을 때 회원정보수정
	 * @param member
	 * @param profileImage
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int editMyInfo(MemberVO member,Map<String,String> paraMap) {
		String path = paraMap.get("path");
		String currentProfileImage = paraMap.get("profile_image");
		
		paraMap.put("userid",member.getUserid());
		paraMap.put("nickname",member.getNickname());
		paraMap.put("username",member.getUsername());
		paraMap.put("email_acept",member.getEmail_acept()+"");
		
		// 프로필이미지 파일 지워주기
		if( !"user.PNG".equals(currentProfileImage) || paraMap.get("newFileName") == null) {
			fileManager.doFileDelete(currentProfileImage, path);
		}
		int result1 = dao.editMyInfo(paraMap);				//멤버테이블에서 파일이름 업데이트 해주기
		int result2 = dao.changeBoardByProfileImg(paraMap);	//게시판에서도 프사 변경해주기
		return result1*result2;
	}

	
	
	/**
	 * 이메일 변경시 업데이트해주기
	 * @return 변경에 성공하면 true, 실패하면 false
	 */
	@Override
	public boolean editEmail(Map<String,String> paraMap) {
		
		int result = dao.editEmail(paraMap);
		
		boolean editEmailSuccess = result == 1?true:false;
		
		return editEmailSuccess;
	}

	
	/**
	 * 다른사람의 유저의 활동내역 리스트 얻기
	 * @param 닉네임
	 * @return 활동내역 리스트
	 */
	@Override
	public List<ActivityVO> getActivityOther(Map<String, String> paraMap) {
		List<ActivityVO> activities = dao.getActivityOther(paraMap);
		return activities;
	}

	
	/**
	 * 유저의 활동내역 총 갯수를 알아오기(닉네임으로)
	 * @param 닉네임
	 * @return 활동내역 총 갯수
	 */
	@Override
	public int getTotalActivityOther(Map<String, String> paraMap) {
		int totalCount = dao.getTotalActivityOther(paraMap);
		return totalCount;
	}

	/**
	 * 닉네임으로 프로필이미지 얻기
	 */
	@Override
	public Map<String,String> getProfileByNickname(Map<String, String> paraMap) {
		Map<String,String> memberMap = dao.getProfileByNickname(paraMap);
		return memberMap;
	}

	/**
	 * 일반회원에서 교육기관회원으로 전환하기
	 */
	@Override
	public boolean ChangeAcaMember(Map<String, String> paraMap) {
		int result1 = dao.insertAcaMember(paraMap);
		int result2 = dao.updateStatus(paraMap);
		
		return result1*result2 == 1?true:false;
	}

	
	
	/**
	 * 가입된 이메일이 존재하는지 여부 검사 + 소셜연동회원인지 검사
	 * @param email
	 * @return 가입된 이메일이 존재하고 소셜연동회원이 아니라면 true, 존재하지 않는이메일이거나 소셜연동회원이라면 false를 반환한다.
	 */
	@Override
	public boolean emailExistAndSnsCheck(String email) {
		boolean emailExist = dao.emailExistCheck(email);
		boolean snsConnectionExist = dao.snsConnectionCheck(email);
		
		System.out.println("emailExist : "+emailExist);
		System.out.println("snsConnectionExist : "+snsConnectionExist);
		
		return emailExist && !snsConnectionExist?true:false;
	}

	
	
	

	
	



	

		
	
	
	
	
	
	
	

}
