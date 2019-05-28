package com.studentmanage.ManageInfo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Teacher")
public class Teacher {
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
  	@Column(name="id")
  	private Long id; //老师id
  
  	@Column(name="name")
  	private String name; //老师名

  	@Column(name="position")
  	private String position; //老师职称
  	
  	@Column(name="birth")
  	private String birth; //老师出生
  	
  	@ManyToOne
  	@JoinColumn(name="college_id",nullable=false)
	private College college;
  	
  	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(Long id, String name, String position, String birth) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.birth = birth;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
  	
  	
}