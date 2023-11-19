package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.Review;
import com.ssafit.pjt.model.dto.SearchCondition;

public interface ReviewService {
	
	public int writeReview(Review review, String loginUserId);
	
	public int modifyReview(Review review, String loginUserId);
	
	public int removeReview(int reviewKey, String loginUserId);
	
	public Review getReview(int reviewKey);
	
	public List<Review> searchReview(SearchCondition condition);
	
	
}
