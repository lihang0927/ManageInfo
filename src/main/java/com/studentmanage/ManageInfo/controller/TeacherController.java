package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.Teacher;
import com.studentmanage.ManageInfo.repo.TeacherRepository;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherRepository teacherRepo;
	@GetMapping("/allTeacher")  //得到所有老师的信息http://localhost:8080/teacher/allTeacher
	public Map<String,Object> getAllteacher(){
		  Map<String,Object> map = new HashMap<String,Object>();
		  try {
			  Iterable<Teacher> teachers = teacherRepo.findAll();
	  		  map.put("result", true);
	  		  map.put("rows", teachers);
	  		  map.put("msg","查询老师信息成功!");
	  		  return map;
		  }catch(Exception e) {
			  map.put("result", false);
			  map.put("msg","查询老师信息失败!");
			  return map;
	    }
	}
}
