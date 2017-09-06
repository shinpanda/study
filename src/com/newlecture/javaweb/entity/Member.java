package com.newlecture.javaweb.entity;

import java.util.Date;

public class Member {
	private String id;
	private String pwd;
	private int isLunar;
	private	String name;
	private String nicName;
	private String gender;
	private String birthday;
	private String phone;
	private String email;
	private Date regDate;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Member(String id, String pwd,  String name, String nicName, String gender, String birthday, int isLunar,
			String phone, String email) {
		this.id = id;
		this.pwd = pwd;
		this.isLunar = isLunar;
		this.name = name;
		this.nicName = nicName;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getIsLunar() {
		return isLunar;
	}
	public void setLunar(int isLunar) {
		this.isLunar = isLunar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNicName() {
		return nicName;
	}
	public void setNicName(String nicName) {
		this.nicName = nicName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public Date getRegDate() {
		return regDate;
	}



	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
