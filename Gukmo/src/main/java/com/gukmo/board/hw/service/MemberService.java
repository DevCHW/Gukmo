package com.gukmo.board.hw.service;

import java.util.List;
import java.util.Map;
import java.util.Random;
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
	public void saveNormalMember(MemberVO input_member) throws Throwable{
		MemberVO member = new MemberVO(input_member.getUserid(), 
									   Sha256.encrypt(input_member.getPasswd()), //비밀번호 단방향 암호화, 
				 					   input_member.getStatus(), 
				 					   input_member.getUpdate_passwd_date(), 
				 					   input_member.getEmail(),
				 					   input_member.getEmail_acept()+"", 
				 					   input_member.getNickname(), 
				 					   input_member.getPoint(), 
				 					   input_member.getJoin_date(), 
				 					   "user.PNG",
				 					   input_member.getAcademy_name(), 
				 					   input_member.getCompany_num(), 
				 					   input_member.getHomepage(), 
				 					   input_member.getPhone(),
				 					   input_member.getUsername());
		
		int n = dao.insert_member_login(member);	//tbl_member_login에 insert
		if(n==1) {	//tbl_member에 insert가 성공시
			dao.insert_member(member);	//tbl_member에 insert
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
		
	
	
	
	
	
	
	
	// == 유틸 메소드 == //
	
	
	/**
	 * 인증코드 난수 발생(추후 회원가입할때 이메일로 인증코드 발생시킬 때 구현하기)
	 * @param 난수를 발생시킬 사이즈
	 * @return 사이즈만큼의 자릿수를가진 난수
	 */
    private String getAuthKey(int size) {
        Random random = new Random();
        
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }



    /**
	 * 프로필사진을 첨부했을 때 회원정보수정
	 * @param member
	 * @param profileImage
	 */
	@Override
	public int editMyInfo(MemberVO member,Map<String,String> paraMap) {
		String path = paraMap.get("path");
		String currentProfileImage = member.getProfile_image();
		
		paraMap.put("userid",member.getUserid());
		paraMap.put("nickname",member.getNickname());
		paraMap.put("username",member.getUsername());
		paraMap.put("email_acept",member.getEmail_acept()+"");
		
		// 프로필이미지 파일 지워주기
		if( !"user.PNG".equals(currentProfileImage) ) {
			fileManager.doFileDelete(currentProfileImage, path);
		}
		int result1 = dao.editMyInfo(paraMap);				//멤버테이블에서 파일이름 업데이트 해주기
		return result1;
	}


	/**
	 * 프사첨부를 안했을경우 회원정보 수정
	 */
	@Override
	public int editMyInfoWithOutNoFile(MemberVO member) {
		return dao.editMyInfoWithOutFile(member);	// 멤버테이블에서 정보 업데이트하기
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



   
	
	

}
