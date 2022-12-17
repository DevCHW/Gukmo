package com.gukmo.board.hasol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hasol.repository.InterAlarmDAO;
import com.gukmo.board.model.AlarmVO;

@Service
public class AlarmService implements InterAlarmService {

	@Autowired
	private InterAlarmDAO dao;
	
	@Override
	public int getNotReadAlarm_count(String userid) {
		System.out.println("왜 안디ㅗ");
		int result = dao.getNotReadAlarm_count(userid);
		System.out.println("result: " + result);
		return result;
	}

	@Override
	public List<AlarmVO> getNotReadAlarmList(String userid) {
		List<AlarmVO> notReadAlarmList = dao.getNotReadAlarmList(userid);
		return notReadAlarmList;
	}

	@Override
	public List<AlarmVO> getAlarm(String userid) {
		List<AlarmVO> alarmList = dao.getAlarmList();
		return alarmList;
	}

	// 알람테이블에 값 넣기
	@Override
	public int setAlarm(Map<String, String> paraMap) {
		int n = dao.setAlarm(paraMap);
		return n;
	}

	// 읽음 컬럼값 변경하기
	@Override
	public int changeIsRead(Map<String, String> paraMap) {
		int result = dao.changeIsRead(paraMap);
		return result;
	}

	// 안읽음 컬럼 카운트
	@Override
	public int showAlarmCnt(String userid) {
		int n = dao.showAlarmCnt(userid);
		return n;
	}



}
