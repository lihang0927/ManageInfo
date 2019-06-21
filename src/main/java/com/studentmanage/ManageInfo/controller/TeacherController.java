package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.Student;
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
	@PostMapping("/teacher")//添加一个老师的所有信息
	public Map<String,Object> addTeacher(@RequestBody Teacher teacher){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Teacher t = teacherRepo.save(teacher);
			map.put("result",true);
			map.put("student",t);
			map.put("msg", "添加老师成功！");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","添加老师失败!");
			return map;
		}
	}
	@PutMapping("/teacher")//修改一个老师的信息，和添加的时候传入的参数相同
	public Map<String,Object> editStudent(@RequestBody Teacher teacher){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Teacher t = teacherRepo.save(teacher);
			map.put("result",true);
			map.put("student",t);
			map.put("msg", "修改老师成功！");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改老师失败!");
			return map;
		}
	}
	@DeleteMapping("/teacher/{id}")//通过数据中的序号id对老师进行删除
	public Map<String,Object> deleteTeacher(@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			teacherRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","成功删除了老师!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","删除老师失败!");
			return map;
	    }
	}
}
