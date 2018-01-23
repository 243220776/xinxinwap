package com.app.domain;

import java.util.Date;

public class TelCode {
	private Integer id;
	private String phone;
	private Integer code;
	private Integer status;
	private Date time;
	private Integer session_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getSession_id() {
		return session_id;
	}
	public void setSession_id(Integer session_id) {
		this.session_id = session_id;
	}
	
	

}
