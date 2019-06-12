package com.studentmanage.ManageInfo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.studentmanage.ManageInfo.model.Student;


public interface StudentRepository extends CrudRepository<Student, Long>{
	// 根据某门课程名（模糊）查询所有选修情况
	public Iterable<Student> findByno(String no);
}
