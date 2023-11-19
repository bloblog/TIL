package com.ssafit.pjt.model.dto;

import java.sql.Date;

public class Review {
	private int reviewKey;
	private int studyKey;
	private String reviewTitle;
    private String reviewContent;
	private int viewCnt;
	private Date reviewDate;
	private String reviewImgPath;
	
	
	public Review() {}


	public Review(int reviewKey, int studyKey, String reviewTitle, String reviewContent, int viewCnt, Date reviewDate,
			String reviewImgPath) {
		this.reviewKey = reviewKey;
		this.studyKey = studyKey;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.viewCnt = viewCnt;
		this.reviewDate = reviewDate;
		this.reviewImgPath = reviewImgPath;
	}


	public int getReviewKey() {
		return reviewKey;
	}


	public void setReviewKey(int reviewKey) {
		this.reviewKey = reviewKey;
	}


	public int getStudyKey() {
		return studyKey;
	}


	public void setStudyKey(int studyKey) {
		this.studyKey = studyKey;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public int getViewCnt() {
		return viewCnt;
	}


	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}


	public Date getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	public String getReviewImgPath() {
		return reviewImgPath;
	}


	public void setReviewImgPath(String reviewImgPath) {
		this.reviewImgPath = reviewImgPath;
	}


	@Override
	public String toString() {
		return "Review{" +
				"reviewKey=" + reviewKey +
				", studyKey=" + studyKey +
				", reviewTitle='" + reviewTitle + '\'' +
				", reviewContent='" + reviewContent + '\'' +
				", viewCnt=" + viewCnt +
				", reviewDate=" + reviewDate +
				", reviewImgPath='" + reviewImgPath + '\'' +
				'}';
	}
}
