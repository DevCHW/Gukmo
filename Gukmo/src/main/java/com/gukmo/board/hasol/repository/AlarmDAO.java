package com.gukmo.board.hasol.repository;

import java.util.List;
import java.util.Map;

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

	//알람테이블에 값넣기
	@Override
	public int setAlarm(Map<String, String> paraMap) {
		int n = gukmo_sql.insert("jhs.setAlarm");
		return n;
	}

	// 읽음 컬럼값 변경하기
	@Override
	public int changeIsRead(String userid) {
		int n = gukmo_sql.insert("jhs.changeIsRead");
		return n;
	}
	
	// 안읽음 컬럼 카운트
	@Override
	public int showAlarmCnt(String userid) {
		int n = gukmo_sql.selectOne("jhs.showAlarmcnt");
		return n;
	}
	

}
