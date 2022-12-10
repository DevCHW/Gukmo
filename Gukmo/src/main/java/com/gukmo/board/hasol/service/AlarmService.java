package com.gukmo.board.hasol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hasol.repository.InterAlarmDAO;
import com.gukmo.board.model.AlarmVO;

@Service
public class AlarmService implements InterAlarmService {

	@Autowired
	private InterAlarmDAO dao;
	
	public int getNotReadAlarm_count(String userid) {
		int n = dao.getNotReadAlarm_count();
		return n;
	}

	@Override
	public List<AlarmVO> getNotReadAlarmList(String userid) {
		List<AlarmVO> notReadAlarmList = dao.getNotReadAlarmList();
		return notReadAlarmList;
	}

	@Override
	public List<AlarmVO> getAlarm(String userid) {
		List<AlarmVO> alarmList = dao.getAlarmList();
		return alarmList;
	}

}
