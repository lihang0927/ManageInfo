package com.studentmanage.ManageInfo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.studentmanage.ManageInfo.model.Classes;
import com.studentmanage.ManageInfo.model.Course;


public interface ClassesRepository extends CrudRepository<Classes,Long>{

	Optional<Classes> findByName(String name);

}
