package com.ssafit.pjt.model.dto;

import java.sql.Date;

public class Schedule {
	private int scheduleKey;
	private int studyKey;
	private Date scheduleDate;
	private String scheduleContent;
	private String schedulePlace;
	
	public Schedule() {}

	public Schedule(int scheduleKey, int studyKey, Date scheduleDate, String scheduleContent, String schedulePlace) {
		this.scheduleKey = scheduleKey;
		this.studyKey = studyKey;
		this.scheduleDate = scheduleDate;
		this.scheduleContent = scheduleContent;
		this.schedulePlace = schedulePlace;
	}

	public int getScheduleKey() {
		return scheduleKey;
	}

	public void setScheduleKey(int scheduleKey) {
		this.scheduleKey = scheduleKey;
	}

	public int getStudyKey() {
		return studyKey;
	}

	public void setStudyKey(int studyKey) {
		this.studyKey = studyKey;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleContent() {
		return scheduleContent;
	}

	public void setScheduleContent(String scheduleContent) {
		this.scheduleContent = scheduleContent;
	}

	public String getSchedulePlace() {
		return schedulePlace;
	}

	public void setSchedulePlace(String schedulePlace) {
		this.schedulePlace = schedulePlace;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleKey=" + scheduleKey + ", studyKey=" + studyKey + ", scheduleDate=" + scheduleDate
				+ ", scheduleContent=" + scheduleContent + ", schedulePlace=" + schedulePlace + "]";
	}
	
	
}
