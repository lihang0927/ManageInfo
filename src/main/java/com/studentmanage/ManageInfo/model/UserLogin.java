package com.studentmanage.ManageInfo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userlogin")
public class UserLogin{
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
  	@Column(name="id")
  	private Long id; //用户id
  
  	@Column(name="name")
  	private String name; //用户名称
  
  
  	@Column(name="password")
  	private String password; //用户密码


	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserLogin(Long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



  	
  
}
