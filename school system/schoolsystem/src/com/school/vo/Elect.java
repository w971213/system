package com.school.vo;

public class Elect {
	private String sid;
	private int cid;
	private float score;
	
	
	
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Elect [sid=" + sid + ", cid=" + cid + ", score=" + score + "]";
	}
	
	
}
