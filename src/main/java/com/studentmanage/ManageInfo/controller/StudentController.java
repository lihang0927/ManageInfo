package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.Student;
import com.studentmanage.ManageInfo.repo.StudentRepository;
/*
 * 学生信息表，主要是对学生进行增删查改
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentRepository studentRepo;
	@GetMapping("/student")  //得到所有学生的信息http://localhost:8080/student/student
	public Map<String,Object> getAllStudentes(){
		  Map<String,Object> map = new HashMap<String,Object>();
		  try {
	  		Iterable<Student> studentes = studentRepo.findAll();
	  		map.put("result", true);
	  		map.put("rows", studentes);
	  		map.put("msg","查询学生信息成功!");
	  		return map;
		  }catch(Exception e) {
			  map.put("result", false);
			  map.put("msg","查询学生信息失败!");
			  return map;
	    }
	}
	@GetMapping("/student/{no}")
	//根据学生学号获取一个学生的信息http://localhost:8080/student/student/512016001
	public Map<String,Object> getCourseById(@PathVariable String no){
		  Map<String,Object> map = new HashMap<String,Object>();
		    try {
		    	Iterable<Student> studentes = studentRepo.findByno(no);
		    	map.put("result", true);
		    	map.put("rows", studentes);
		    	map.put("msg","成功查询到该学生!");
		    	return map;
		    }catch(Exception e) {
		    	map.put("result", false);
		    	map.put("msg","查询该学生失败!");
		    	return map;
		   }
	}
	@PostMapping("/student")//添加一个学生的信息
	public Map<String,Object> addStudent(@RequestBody Student student){
		System.out.println(student.getNo());
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Student s = studentRepo.save(student);
			map.put("result",true);
			map.put("student",s);
			map.put("msg", "添加学生成功！");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","添加学生失败!");
			return map;
		}
	}
	//删除一个学生，根据学生的学号id进行删除
	@DeleteMapping("/student/{id}")
	public Map<String,Object> delStudentByNo(@PathVariable Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			studentRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","成功删除了学生!");
			return map;
		}catch(Exception e) {
			System.out.println(e);
			System.out.print(map);
			map.put("result", false);
			map.put("msg","删除学生失败!");
			return map;
	    }
	}
	@PutMapping("/student")//修改一个学生的信息，和添加的时候传入的参数相同
	public Map<String,Object> editStudent(@RequestBody Student student){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Student s = studentRepo.save(student);
			map.put("result",true);
			map.put("student",s);
			map.put("msg", "修改学生成功！");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改学生失败!");
			return map;
		}
	}
}
