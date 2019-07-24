package com.byzx.vo;

import java.util.Date;

public class UserList {
	private Integer user_id;
	private String nick_name;
	private String user_code;
	private String group_name;
	private String role_name;
	private String user_state;
	private Date create_time;
	private Integer group_id;
	
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	
	@Override
	public String toString() {
		return "UserList [user_id=" + user_id + ", nick_name=" + nick_name + ", user_code=" + user_code
				+ ", group_name=" + group_name + ", role_name=" + role_name + ", user_state=" + user_state
				+ ", create_time=" + create_time + "]";
	}
	
	
	
	
	
}
