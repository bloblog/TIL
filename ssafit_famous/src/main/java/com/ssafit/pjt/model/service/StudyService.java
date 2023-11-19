package com.ssafit.pjt.model.service;

import com.ssafit.pjt.model.dto.Study;

public interface StudyService {
	
	int addStudy(Study study);
	
	int addMember(Study study, int[] list);
	
	int modifyStudy(Study study, String loginUserId, int[] out, int[] in);
	
	int removeStudy(int studyKey, String loginUserId);
	
	Study getStudy(int studyKey);
}
