package com.gukmo.board.hasol.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.AlarmVO;

public interface InterAlarmService {

	// 읽지 않은 알람 카운트
	int getNotReadAlarm_count(String userid);

	// 읽지 않은 알람 가져오기
	List<AlarmVO> getNotReadAlarmList(String userid);

	// 전체 알람 가져오기
	List<AlarmVO> getAlarm(String userid);

	// 알람에 값 넣기
	int setAlarm(Map<String, String> paraMap);

	// 읽음 컬럼 값 변경하기
	int changeIsRead(String userid);

	// 모든 알람 컬럼 카운트
	int showAlarmCnt(String userid);


}
