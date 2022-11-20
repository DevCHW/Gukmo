package com.gukmo.board.common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
// 구글에서 제공하는 SMTP (메일전송)서버에 접근해야 한다.
// 이메일 주소와 앱 비밀번호가 일치하는지 확인하는 클래스이다.
public class MySMTPAuthenticator extends Authenticator {

   @Override
   public PasswordAuthentication getPasswordAuthentication() {
      // Gmail 의 경우 @gmail.com 을 제외한 아이디만 입력한다.(첫번째 파라미터)
      // 두번째 파라미터에는 발급받은 앱 비밀번호를 입력한다.
      return new PasswordAuthentication("jsangyeong194","dicongkcowuvzhbl");
   }
   
}