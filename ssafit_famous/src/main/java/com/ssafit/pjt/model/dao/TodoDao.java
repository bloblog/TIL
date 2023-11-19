package com.ssafit.pjt.model.dao;

import java.util.Map;

import com.ssafit.pjt.model.dto.Todo;

public interface TodoDao {
	// 목표 생성
	int insertTodo(Todo todo); 
	
	// 스터디 관계 생성
	int insertRelation(Map<String,Integer> map);
	
	// todo 관계 생성
	int insertUserRelation(Map<String,Integer> map);

	// todo 관계 삭제
	int deleteUserRelation(Map<String,Integer> map);


	// 목표 수정
	int updateTodo(Todo todo);
	
	// 목표 삭제
	int deleteTodo(int todoKey);
	
	
	// 목표 상세
	Todo selectOne(int todoKey);
	
	Todo selectOneByTodo(Todo todo);
}
