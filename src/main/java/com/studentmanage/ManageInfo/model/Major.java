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
@Table(name="Major")
public class Major {
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
  	@Column(name="id")
  	private Long id; //专业id
  
  	@Column(name="name")
  	private String name; //专业名

  	@ManyToOne
  	@JoinColumn(name="college_id",nullable=false)
	private College college;
  	
  	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
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
