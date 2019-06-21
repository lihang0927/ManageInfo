package com.studentmanage.ManageInfo.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Teacher")
public class Teacher {
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
  	@Column(name="id")
  	private Long id; //老师id
  
  	@Column(name="name")
  	private String name; //老师名

  	@Column(name="sex")
  	private String sex; //老师性别
  	
  	@Column(name="position")
  	private String position; //老师职称
  	
  	@Column(name="birth")
  	private String birth; //老师出生
  	
  	@ManyToOne
  	@JoinColumn(name="college_id")
	private College college;
  	
  	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="teacher")
	@JsonIgnore
  	private Collection<CourseTeacher> courseteachers = new HashSet<CourseTeacher>();
  	
  	public Collection<CourseTeacher> getCourseteachers() {
		return courseteachers;
	}

	public void setCourseteachers(Collection<CourseTeacher> courseteachers) {
		this.courseteachers = courseteachers;
	}

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

	public Teacher(Long id, String name, String position, String birth,String sex) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.birth = birth;
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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