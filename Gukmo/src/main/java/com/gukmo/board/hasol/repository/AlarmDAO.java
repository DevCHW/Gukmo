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
	
	// 안 읽은 알람 카운트
	@Override
	public int getNotReadAlarm_count(String nickname) {
		int n = gukmo_sql.selectOne("jhs.getNotReadAlarm_count", nickname);
		return n;
	}

	// 안 읽은 알림 리스트
	@Override
	public List<AlarmVO> getNotReadAlarmList(String nickname) {
		List<AlarmVO> notReadAlarmList = gukmo_sql.selectList("jhs.getNotReadAlarmList", nickname);
		//System.out.println("dao:"+notReadAlarmList);
		return notReadAlarmList;
	}

	//알람테이블에 값넣기
	@Override
	public int setAlarm(Map<String, String> paraMap) {
		int n = gukmo_sql.insert("jhs.setAlarm", paraMap);
		return n;
	}

	// 읽음 컬럼값 변경하기
	@Override
	public int changeIsRead(Map<String, String> paraMap) {
		int result = gukmo_sql.insert("jhs.changeIsRead", paraMap);
		System.out.println("dao:" +result);
		return result;
	}
	
	// 전체 알람 카운트
	@Override
	public int getTotalAlarmPage(Map<String, String> paraMap) {
		int result = gukmo_sql.selectOne("jhs.getTotalAlarmPage", paraMap);
		return result;
	}
	
	// 전체 알람 리스트
	@Override
	public List<AlarmVO> getAlarmList(Map<String, String> paraMap) {
		List<AlarmVO> alarmList = gukmo_sql.selectList("jhs.getAlarmList" , paraMap);
		return alarmList;
	}

}
