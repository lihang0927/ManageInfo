package com.studentmanage.ManageInfo.model;

import java.time.LocalDate;
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
@Table(name="College")
public class College {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
  	@Column(name="id")
  	private Long id; //学院id
  
  	@Column(name="name")
  	private String name; //学院名
  	
  	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="college")
  	@JsonIgnore
 	private Collection<Student> students = new HashSet<Student>();
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="college")
	@JsonIgnore
  	private Collection<Major> majors = new HashSet<Major>();

	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="college")
	@JsonIgnore
  	private Collection<Course> courses = new HashSet<Course>();
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="college")
	@JsonIgnore
  	private Collection<Teacher> teachers = new HashSet<Teacher>();
	
	public Collection<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Collection<Course> getCourses() {
		return courses;
	}
	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}
	public Collection<Major> getMajors() {
		return majors;
	}
	public void setMajors(Collection<Major> majors) {
		this.majors = majors;
	}
	public Collection<Student> getStudents() {
		return students;
	}
	public void setStudents(Collection<Student> students) {
		this.students = students;
	}
	public College() {
		super();
		// TODO Auto-generated constructor stub
	}
	public College(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
}