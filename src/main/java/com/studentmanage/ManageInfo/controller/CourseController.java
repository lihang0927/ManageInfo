package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.Course;
import com.studentmanage.ManageInfo.repo.CourseRepository;


@RestController
@RequestMapping("/course")//接口注解
public class CourseController {
	//课程
	@Autowired
	private CourseRepository courseRepo;//与dao（repository）层进行交互
	
	@RequestMapping(value = "/allCourse", method = RequestMethod.GET)//得到所有课程，比如axios请求：/course/allCourse
	public Map<String,Object> getAllCourse(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Iterable<Course> course = courseRepo.findAll();
		  	map.put("result", true);
		  	map.put("rows", course);
		  	map.put("msg","查询课程信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","查询课程信息失败!");
		    return map;
		}
	}
	
	@RequestMapping(value = "/courseId/{id}", method = RequestMethod.GET)//根据课程Id查表，比如传参：/course/courseId/1
	public Map<String,Object> getCourseByName(@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Optional<Course> course = courseRepo.findById(id);
		  	map.put("result", true);
		  	map.put("rows", course);
		  	map.put("msg","根据课程名查询课程信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","根据课程名查询课程信息失败!");
		    return map;
		}
	}
	
	
}
