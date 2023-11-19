package com.ssafit.pjt.model.service;

import com.ssafit.pjt.model.dto.Schedule;

public interface ScheduleService {
	// 일정 생성
	int addSchedule(Schedule schedule);
	// 일정 수정
	int modifySchedule(Schedule schedule, String loginUser);
	// 일정 삭제
	int removeSchedule(int scheduleKey, String loginUser);
	// 일정 상세
	Schedule getSchedule(int scheduleKey);
}
