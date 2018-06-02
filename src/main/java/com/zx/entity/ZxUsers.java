package com.zx.entity;

import java.util.Date;

public class ZxUsers {
	
	private Integer id;
	
	private String name;
	
	private Date birth;
	
	private Integer age;
	
	private String sex;
	
	public ZxUsers(){}
	

	public ZxUsers(Integer id, String name, Date birth, Integer age, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.age = age;
		this.sex = sex;
	}
	
	

	public String toString() {
		return "ZxUsers [id=" + id + ", name=" + name + ", birth=" + birth + ", age=" + age + ", sex=" + sex + "]";
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
