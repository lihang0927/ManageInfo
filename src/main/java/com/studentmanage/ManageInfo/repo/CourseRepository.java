package com.studentmanage.ManageInfo.repo;

import org.springframework.data.repository.CrudRepository;

import com.studentmanage.ManageInfo.model.Course;
import com.studentmanage.ManageInfo.model.UserLogin;


public interface CourseRepository extends CrudRepository<Course, Long>{
}
