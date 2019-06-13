package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.Course;
import com.studentmanage.ManageInfo.repo.CourseRepository;


@RestController
@RequestMapping("/course")//接口注解
public class CourseController {
	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	//注入数据库课程
	@Autowired
	private CourseRepository courseRepo;//与dao（repository）层进行交互
	
	//得到所有课程，比如axios请求：/course/allCourse
	@RequestMapping(value = "/allCourse", method = RequestMethod.GET)
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
	
	//根据课程Id查表，比如axios请求：/course/courseId/1
	@RequestMapping(value = "/courseId/{id}", method = RequestMethod.GET)
	public Map<String,Object> getCourseById(@PathVariable Long id){
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
	
	//根据课程name查表，比如axios请求：/course/courseName?name=JAVAEE
	@RequestMapping(value = "/courseName", method = RequestMethod.GET)
	public Map<String,Object> getCourseByName(@RequestParam(value ="name") String name){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Optional<Course> course = courseRepo.findByName(name);
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
	
	//添加课程，比如axios请求：/course/addCourse?course(对象)
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public Map<String,Object> addCourse(@RequestBody Course course) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Course c = courseRepo.save(course);
			map.put("result", true);
			map.put("rows", c);
			map.put("msg","成功添加了课程!");
			return map;
		}catch(Exception e) {
			logger.error(e.getMessage());
			map.put("result", false);
			map.put("msg","添加课程失败!");
			return map;
		}
	}
	
	//删除一个课程,比如axios请求：/course/deleteCourse/1
	@RequestMapping(value = "/deleteCourse/{id}", method = RequestMethod.DELETE)
	public Map<String,Object> delCourseById(@PathVariable Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			courseRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","删除课程成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","删除课程失败!");
			return map;
		}
	}
	
	//修改一个课程,比如axios请求：/course/putCourse?Course（对象）
	@RequestMapping(value = "/putCourse", method = RequestMethod.PUT)
	public Map<String,Object> putCourse(@RequestBody  Course course) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Course c = courseRepo.save(course);
			map.put("result", true);
			map.put("rows", c);
			map.put("result", true);
			map.put("msg","修改课程成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改课程失败!");
			return map;
		}
	}
	
}
