package com.ssafit.pjt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Schedule;
import com.ssafit.pjt.model.service.ScheduleService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(tags = "일정 컨트롤러")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	// 일정 등록하기
	@PostMapping("/schedule")
	public ResponseEntity<Integer> add(@RequestBody Schedule schedule){
		int result = scheduleService.addSchedule(schedule);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	// 일정 수정하기
	@PutMapping("/schedule/{scheduleKey}")
	public ResponseEntity<Integer> update(@RequestBody Schedule schedule, @PathVariable int scheduleKey, HttpSession session) {
		String loginUserId = String.valueOf(session.getAttribute("loginUser"));
		schedule.setScheduleKey(scheduleKey);
		
		int result = scheduleService.modifySchedule(schedule, loginUserId);
		if (result == -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.UNAUTHORIZED);
		} else if (result == 0){
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	// 일정 삭제하기
	@DeleteMapping("/schedule/{scheduleKey}")
	public ResponseEntity<Integer> delete(@PathVariable int scheduleKey, HttpSession session) {
		String loginUserId = String.valueOf(session.getAttribute("loginUser"));
		
		int result = scheduleService.removeSchedule(scheduleKey, loginUserId);
		if (result == -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.UNAUTHORIZED);
		} else if (result == 0){
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	// 일정 디테일보기
	@GetMapping("/schedule/{scheduleKey}")
	public ResponseEntity<?> detail(@PathVariable int scheduleKey) {
		Schedule result = scheduleService.getSchedule(scheduleKey);
		if (result == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Schedule>(result, HttpStatus.OK);
		}
	}
	
}
