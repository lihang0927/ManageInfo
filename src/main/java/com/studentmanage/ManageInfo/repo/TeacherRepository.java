package com.studentmanage.ManageInfo.repo;

import org.springframework.data.repository.CrudRepository;

import com.studentmanage.ManageInfo.model.Student;
import com.studentmanage.ManageInfo.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long>{
}
