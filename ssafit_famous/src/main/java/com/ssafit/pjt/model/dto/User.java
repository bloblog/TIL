package com.ssafit.pjt.model.dto;

public class User {
	private int userKey;
	private String id;
	private String password;
	private int exp = 0;
	private String userImgPath;
	
	public User() {}

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getUserImgPathh() {
		return userImgPath;
	}

	public void setUserImgPath(String userImgPath) {
		this.userImgPath = userImgPath;
	}

	@Override
	public String toString() {
		return "User [userKey=" + userKey + ", id=" + id + ", password=" + password + ", exp=" + exp
				+ ", userImgPath=" + userImgPath + "]";
	}

}
