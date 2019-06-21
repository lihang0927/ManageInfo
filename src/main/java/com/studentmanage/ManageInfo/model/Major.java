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
@Table(name="Major")
public class Major {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
  	@Column(name="id")
  	private Long id; //专业id
  
  	@Column(name="name")
  	private String name; //专业名

  	@ManyToOne
  	@JoinColumn(name="college_id")
	private College college;
  	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="major")
	@JsonIgnore
	private Collection<Classes> classess = new HashSet<Classes>();
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="major")
	@JsonIgnore
	private Collection<Student> students = new HashSet<Student>();
	
	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Collection<Classes> getClassess() {
		return classess;
	}

	public void setClassess(Collection<Classes> classess) {
		this.classess = classess;
	}

	public Major() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Major(Long id, String name) {
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
