package com.ssafit.pjt.controller;

import java.util.List;

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

import com.ssafit.pjt.model.dto.Review;
import com.ssafit.pjt.model.dto.Study;
import com.ssafit.pjt.model.service.StudyService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(tags = "스터디 컨트롤러")
public class StudyController {
	
	@Autowired
	private StudyService studyService;
	
	// 스터디 삭제하기
	@DeleteMapping("/study/{studyKey}")
	public ResponseEntity<Integer> delete(@PathVariable int studyKey, HttpSession session) {
		String loginUserId = String.valueOf(session.getAttribute("loginUser"));
		
		int result = studyService.removeStudy(studyKey, loginUserId);

		if (result == -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.UNAUTHORIZED);
		} else if (result == 0){
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
	// 스터디 생성하기
	@PostMapping("/study")
	public ResponseEntity<Integer> add(@RequestBody Study study) {
		int result = studyService.addStudy(study);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	// 스터디 디테일보기
	@GetMapping("/study/{studyKey}")
	public ResponseEntity<?> detail(@PathVariable int studyKey) {
		Study result = studyService.getStudy(studyKey);
		if (result == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Study>(result, HttpStatus.OK);
		}
	}
	
	// 스터디 수정하기
	@PutMapping("/study/{studyKey}")
	public ResponseEntity<Integer> update(@RequestBody Study study, int[] out, int[] in, @PathVariable int studyKey, HttpSession session) {
		String loginUserId = String.valueOf(session.getAttribute("loginUser"));
		
		study.setStudyKey(studyKey);
		int result = studyService.modifyStudy(study, loginUserId, out, in);
		if (result == -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.UNAUTHORIZED);
		} else if (result == 0){
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
