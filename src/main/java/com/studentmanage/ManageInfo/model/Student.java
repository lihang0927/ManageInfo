package com.studentmanage.ManageInfo.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
  	@Column(name="id")
  	private Long id; //学生id
  
  	@Column(name="no")
  	private String no; //学生学号
  
  	@Column(name="name")
  	private String name; //学生姓名
  
  	@Column(name="sex")
  	private String sex; //学生性别
  	
  	@Column(name="phone")
  	private String phone; //学生电话
  	
  	@Column(name="address")
  	private String address; //学生地址
  	
  	@Column(name="birth")
  	private LocalDate birth; //学生生日
  
  	@Column(name="grade")
	private String grade; //学生年级
  
  	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
  	@JoinColumn(name="major_id")
  	private Major major;
	
  	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
  	@JoinColumn(name="college_id")
  	private College college;
	

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String no, String name, String sex, LocalDate birth, String grade,String phone,String address) {
		super();
		this.no = no;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.grade = grade;
		this.phone = phone;
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public LocalDate getBirth() {
		return birth;
	}
	
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
