package com.school.vo;

public class Course {
	private int cid;
	private String ctid;
	private String cname;
	private String ctype;
	private float credit;
	private int cmax;
	private String cnote;
	private int cprecid;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCtid() {
		return ctid;
	}
	public void setCtid(String ctid) {
		this.ctid = ctid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public int getCmax() {
		return cmax;
	}
	public void setCmax(int cmax) {
		this.cmax = cmax;
	}
	public String getCnote() {
		return cnote;
	}
	public void setCnote(String cnote) {
		this.cnote = cnote;
	}
	public int getCprecid() {
		return cprecid;
	}
	public void setCprecid(int cprecid) {
		this.cprecid = cprecid;
	}
	@Override
	public String toString() {
		return "Course [cid=" + cid + ", ctid=" + ctid + ", cname=" + cname + ", ctype=" + ctype + ", credit=" + credit
				+ ", cmax=" + cmax + ", cnote=" + cnote + ", cprecid=" + cprecid + "]";
	}
	
}
