package com.ssafit.pjt.model.dao;

import java.util.Map;

import com.ssafit.pjt.model.dto.Schedule;

public interface ScheduleDao {
	// 일정 생성
	int insertSchedule(Schedule schedule);
	
	// 관계 생성
	int insertRelation(Map<String,Integer> map);
	
	// 일정 수정
	int updateSchedule(Schedule schedule);
	
	// 일정 삭제
	int deleteSchedule(int scheduleKey);
	
	// 관계 삭제
	int deleteRelation(Map<String,Integer> map);
	
	// 일정 상세
	Schedule selectOne(int scheduleKey);
	
	Schedule selectOneBySchedule(Schedule sc);
}
