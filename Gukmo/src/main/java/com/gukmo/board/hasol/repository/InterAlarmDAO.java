package com.gukmo.board.hasol.repository;

import java.util.List;

import com.gukmo.board.model.AlarmVO;

public interface InterAlarmDAO {

	// 안읽은 알람 카운트
	int getNotReadAlarm_count();

	// 안읽은 알람 리스트
	List<AlarmVO> getNotReadAlarmList();

	// 전체 알람 리스트
	List<AlarmVO> getAlarmList();


}
