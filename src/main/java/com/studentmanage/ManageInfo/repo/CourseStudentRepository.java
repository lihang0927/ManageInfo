package com.studentmanage.ManageInfo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.studentmanage.ManageInfo.model.CourseStudent;

public interface CourseStudentRepository extends CrudRepository<CourseStudent,Long>{

	Optional<CourseStudent> findByScore(Float score);
}
