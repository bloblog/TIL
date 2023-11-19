package com.ssafit.pjt.model.dto;

import java.sql.Date;

public class Study {
	private int studyKey;
	private int leaderKey;
	private String studyName;
	private String category;
	private Date studyStart;
	private Date studyEnd;
	private int alarm;
	
	public Study() {}

	public Study(int leaderKey, String studyName, String category, Date studyStart, Date studyEnd, int alarm) {
		this.leaderKey = leaderKey;
		this.studyName = studyName;
		this.category = category;
		this.studyStart = studyStart;
		this.studyEnd = studyEnd;
		this.alarm = alarm;
	}

	public int getStudyKey() {
		return studyKey;
	}

	public void setStudyKey(int studyKey) {
		this.studyKey = studyKey;
	}

	public int getLeaderKey() {
		return leaderKey;
	}

	public void setLeaderKey(int leaderKey) {
		this.leaderKey = leaderKey;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getStudyStart() {
		return studyStart;
	}

	public void setStudyStart(Date studyStart) {
		this.studyStart = studyStart;
	}

	public Date getStudyEnd() {
		return studyEnd;
	}

	public void setStudyEnd(Date studyEnd) {
		this.studyEnd = studyEnd;
	}

	public int getAlarm() {
		return alarm;
	}

	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

	@Override
	public String toString() {
		return "Study [studyKey=" + studyKey + ", leaderKey=" + leaderKey + ", studyName=" + studyName + ", category="
				+ category + ", studyStart=" + studyStart + ", studyEnd=" + studyEnd + ", alarm=" + alarm + "]";
	}

	
}
