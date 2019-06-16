package com.studentmanage.ManageInfo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="courseteacher")
public class CourseTeacher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//实现id自增长
	Long id;

	@ManyToOne()
	Teacher teacher;
	
	@ManyToOne()
	Course course;
	
	public CourseTeacher() {super();}
	public CourseTeacher(Teacher teacher,Course course) {
		super();
		this.teacher = teacher;
		this.course = course;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
