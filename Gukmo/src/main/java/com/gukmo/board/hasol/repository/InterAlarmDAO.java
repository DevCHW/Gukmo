package com.gukmo.board.hasol.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.AlarmVO;

public interface InterAlarmDAO {

	// 안읽은 알람 카운트
	int getNotReadAlarm_count(String nickname);

	// 안읽은 알람 리스트
	List<AlarmVO> getNotReadAlarmList(String nickname);

	// 알람테이블에 값 넣기
	int setAlarm(Map<String, String> paraMap);

	// 읽음 컬럼값 변경하기
	int changeIsRead(Map<String, String> paraMap);

	// 전체 알람 카운트
	int getTotalAlarmPage(Map<String, String> paraMap);

	// 전체 알람 리스트
	List<AlarmVO> getAlarmList(Map<String, String> paraMap);




}
