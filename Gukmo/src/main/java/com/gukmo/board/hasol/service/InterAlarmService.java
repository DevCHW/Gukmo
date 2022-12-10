package com.gukmo.board.hasol.service;

import java.util.List;

import com.gukmo.board.model.AlarmVO;

public interface InterAlarmService {

	// 읽지 않은 알람 카운트
	int getNotReadAlarm_count(String userid);

	// 읽지 않은 알람 가져오기
	List<AlarmVO> getNotReadAlarmList(String userid);

	// 전체 알람 가져오기
	List<AlarmVO> getAlarm(String userid);

}
