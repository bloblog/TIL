package com.ssafit.pjt.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafit.pjt.model.dto.Study;

public interface StudyDao {
	// 스터디 생성
	int insertStudy(Study study);
	
	// 관계 생성 (study - user)
	int insertRelation(Map<String, Object> map);

	// study 의 todo 리스트 가져오기
	List<Integer> getTodoKeys(int StudyKey);
	
	// 스터디 수정
	int updateStudy(Study study);
	
	void deleteRelation(Map<String, Integer> map);
	
	// 스터디 삭제
	int deleteStudy(int studyKey);
	
	// 스터디 상세
	Study selectOne(int studyKey);
	
	Study selectOneByName(String name);
}
