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
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="credit")
	private int credit;
	
	@ManyToOne()
	@JoinColumn(name="college_id")
	private College college;
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="course")
	@JsonIgnore
	private Collection<CourseStudent> courseStudents = new HashSet<CourseStudent>();
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="course")
	@JsonIgnore
	private Collection<CourseTeacher> courseTeachers = new HashSet<CourseTeacher>();
	
	
	public Collection<CourseTeacher> getCourseTeachers() {
		return courseTeachers;
	}

	public void setCourseTeachers(Collection<CourseTeacher> courseTeachers) {
		this.courseTeachers = courseTeachers;
	}

	public Collection<CourseStudent> getCourseStudents() {
		return courseStudents;
	}

	public void setCourseStudents(Collection<CourseStudent> courseStudents) {
		this.courseStudents = courseStudents;
	}

	//构造函数
	public Course() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public Course(String name, int credit) {
		super();
		this.name = name;
		this.credit = credit;
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
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
}
