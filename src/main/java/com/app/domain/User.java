package com.app.domain;

import java.util.Date;

public class User {
	
	private Integer id;
    private String username;
    private String password;
    private String phone;
    private Integer uID;
    private Date reg_time;
    private Boolean locked = Boolean.FALSE;
    
    public User(){
    	super();
    }
    public User(String username,String password){
    	super();
    	this.username=username;
    	this.password=password;
    }
        
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Date getReg_time() {
		return reg_time;
	}
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getuID() {
		return uID;
	}
	public void setuID(Integer uID) {
		this.uID = uID;
	}

}
