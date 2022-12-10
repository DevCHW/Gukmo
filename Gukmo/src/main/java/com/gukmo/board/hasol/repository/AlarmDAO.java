package com.gukmo.board.hasol.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.AlarmVO;

@Repository
public class AlarmDAO implements InterAlarmDAO {
	
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	@Override
	public int getNotReadAlarm_count() {
		int n = gukmo_sql.selectOne("jhs.getNotReadAlarm_count");
		return n;
	}

	@Override
	public List<AlarmVO> getNotReadAlarmList() {
		List<AlarmVO> notReadAlarmList = gukmo_sql.selectList("jhs.getNotReadAlarmList");
		return notReadAlarmList;
	}

	@Override
	public List<AlarmVO> getAlarmList() {
		List<AlarmVO> alarmList = gukmo_sql.selectList("jhs.getAlarmList");
		return alarmList;
	}

}
