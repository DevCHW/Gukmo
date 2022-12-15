package com.gukmo.board.hasol.alarm;

import java.util.*;

//import javax.websocket.server.ServerEndpoint;

//import org.apache.tomcat.util.buf.StringUtils;
//import org.mybatis.logging.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//import com.gukmo.board.hasol.service.InterAlarmService;
//import com.gukmo.board.model.AlarmVO;
import com.gukmo.board.model.MemberVO;

public class WebSocketHandler extends TextWebSocketHandler {

//	@Autowired
//	private InterAlarmService service;
	
	// init-method
	public void init() throws Exception {}
	
	//로그인 한 전체 클라이언트를 저장하는 리스트
	private List<WebSocketSession> sessions =new ArrayList<>();
	
	//로그인 한 개별 클라이언트를 저장하는 Map
//	private Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
	
//	String userid = null;
	
	// websocket 연결 성공 시
    @Override
    public void afterConnectionEstablished(WebSocketSession wsession) throws Exception {
    
    	System.out.println("웹소켓 연결됨!");
    	System.out.println("~~~ 웹채팅 확인용 : " + wsession.getId() + " 이(가)접속했습니다.");
    	// 웹소켓 서버에 접속한 모든 클라이언트를 리스트에 저장
//    	sessions.add(session);
//    	
//        // 로그인한 유저의 아이디를 가져와서 1:1 관계에 값을 넣음
//    	userid = getId(session); // 하단에 함수 있음
//    	userSessionsMap.put(userid, session);
    
    }
    

	// websocket 메세지 수신 및 송신
    @Override
    protected void handleTextMessage(WebSocketSession wsession, TextMessage message) throws Exception {
    	// >>> 파라미터  WebSocketSession wsession 은 웹소켓서버에 접속한 클라이언트 이다. <<< //
    	// >>> 파라미터  TextMessage message 은 웹소켓서버에 접속한 클라이언트가 웹소켓서버로 보낸 웹소켓 메시지 이다. <<< //
    	
		/*
		 * String msg = message.getPayload();
		 * 
		 * if (msg != null) { String str[] = msg.split(",");
		 * 
		 * if(str != null && str.length == 6) {
		 * 
		 * String cmd = str[0]; // 알람 구분(댓글, 좋아요, 신고) String boardno = str[1]; // 게시판 번호
		 * String boardWriter = str[2]; // 글작성자 String commentWriter = str[3]; // 댓글작성자
		 * String subject = str[4]; // 게시글 제목 String comment = str[5]; // 댓글 // String
		 * cnt = str[6];
		 * 
		 * WebSocketSession boardWriterSession = userSessionsMap.get(boardWriter);
		 * WebSocketSession commentWriterSession = userSessionsMap.get(commentWriter);
		 * 
		 * // 게시글에 댓글이 달린 경우 if("comment".equals(cmd) && boardWriterSession != null) {
		 * 
		 * if(subject.length() > 13) { subject = subject.substring(0, 13) + "..."; }
		 * 
		 * TextMessage tmpMsg = new
		 * TextMessage("<a href='<%=ctxPath =%>/detail.do?boardNum=" +boardno+ "' > ["
		 * +subject+ "] 글에 댓글이 달렸습니다.</a>"); boardWriterSession.sendMessage(tmpMsg); }
		 * 
		 * // 게시글이 좋아요를 받은 경우 if("like".equals(cmd) && boardWriterSession != null) {
		 * if(subject.length() > 13) { subject = subject.substring(0, 13) + "..."; }
		 * 
		 * TextMessage tmpMsg = new
		 * TextMessage("<a href='<%=ctxPath =%>/detail.do?boardNum=" +boardno+ "'> ["
		 * +subject+ "] 글이 좋아요를 받았습니다.</a>"); boardWriterSession.sendMessage(tmpMsg); }
		 * 
		 * // 게시글이 신고를 받은 경우 if("penalty".equals(cmd) && boardWriterSession != null) {
		 * if(subject.length() > 13) { subject = subject.substring(0, 13) + "..."; }
		 * 
		 * TextMessage tmpMsg = new
		 * TextMessage("<a href='<%=ctxPath =%>/detail.do?boardNum=" +boardno+ "' > ["
		 * +subject+ "] 글이 신고를 받았습니다.</a>"); boardWriterSession.sendMessage(tmpMsg); }
		 * 
		 * 
		 * 
		 * // 댓글에 대댓글이 달린 경우 if("recomment".equals(cmd) && boardWriterSession != null) {
		 * if(comment.length() > 13) { comment = comment.substring(0, 13) + "..."; }
		 * TextMessage tmpMsg = new
		 * TextMessage("<a href='<%=ctxPath =%>/detail.do?boardNum=" +boardno+ "'> ["
		 * +comment+ "] 댓글에 댓글이 달렸습니다.</a>"); commentWriterSession.sendMessage(tmpMsg);
		 * }
		 * 
		 * // 댓글이 좋아요를 받은 경우 if("commentlike".equals(cmd) && boardWriterSession != null)
		 * { if(comment.length() > 13) { comment = comment.substring(0, 13) + "..."; }
		 * TextMessage tmpMsg = new
		 * TextMessage("<a href='<%=ctxPath =%>/detail.do?boardNum=" +boardno+ "' > ["
		 * +comment+ "] 댓글이 좋아요를 받았습니다.</a>"); commentWriterSession.sendMessage(tmpMsg);
		 * }
		 * 
		 * // 댓글이 신고를 받은 경우 if("commentPenalty".equals(cmd) && boardWriterSession !=
		 * null) { if(comment.length() > 13) { comment = comment.substring(0, 13) +
		 * "..."; } TextMessage tmpMsg = new
		 * TextMessage("<a href='<%=ctxPath =%>/detail.do?boardNum=" +boardno+ "' > ["
		 * +comment+ "] 댓글이 신고를 받았습니다.</a>"); commentWriterSession.sendMessage(tmpMsg);
		 * }
		 * 
		 * } }
		 * 
		 */
  	
        //접속한 해당 유저 읽지않은 알림 데이터 전체 카운트 만 가져올 경우  
        //int alarm_cnt = service.getNotReadAlarm_count(userid);
        


    }
    
    
    // websocket 연결 종료 시    
    @Override
    public void afterConnectionClosed(WebSocketSession wsession, CloseStatus status) throws Exception {
 
    	System.out.println("웹소켓 종료 ^_^");
//    	userSessionsMap.remove(wsession.getId());
//		sessions.remove(wsession);
		
    }
    
	/*
	 * // ID 값을 가져오는 메소드 private String getId(WebSocketSession session) {
	 * 
	 * // 로그인한 개별 userid 값 가져오기 Map<String, Object> httpSession =
	 * session.getAttributes(); MemberVO loginuser = (MemberVO)
	 * httpSession.get("user");
	 * 
	 * if(loginuser == null) { return session.getId(); } else { return
	 * loginuser.getUserid(); }
	 * 
	 * }
	 */
}

