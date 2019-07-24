package com.school.vo;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable{
	private String sid;
	private String s_key;
	private String s_name;
	private String s_sex;
	private int s_age;
	private float s_credit;
	private String s_address;
	private String s_phone;
	private String s_email;
	private List<Elect> elect;
	
	
	
	public List<Elect> getElect() {
		return elect;
	}
	public void setElect(List<Elect> elect) {
		this.elect = elect;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getS_key() {
		return s_key;
	}
	public void setS_key(String s_key) {
		this.s_key = s_key;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_sex() {
		return s_sex;
	}
	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}
	public int getS_age() {
		return s_age;
	}
	public void setS_age(int s_age) {
		this.s_age = s_age;
	}
	public float getS_credit() {
		return s_credit;
	}
	public void setS_credit(float s_credit) {
		this.s_credit = s_credit;
	}
	public String getS_address() {
		return s_address;
	}
	public void setS_address(String s_address) {
		this.s_address = s_address;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	public String getS_email() {
		return s_email;
	}
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", s_key=" + s_key + ", s_name=" + s_name + ", s_sex=" + s_sex + ", s_age="
				+ s_age + ", s_credit=" + s_credit + ", s_address=" + s_address + ", s_phone=" + s_phone + ", s_email="
				+ s_email + ", elect=" + elect + "]";
	}
	
}
