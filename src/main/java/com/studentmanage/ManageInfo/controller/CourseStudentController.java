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

import com.studentmanage.ManageInfo.model.Classes;
import com.studentmanage.ManageInfo.model.Course;
import com.studentmanage.ManageInfo.model.CourseStudent;
import com.studentmanage.ManageInfo.repo.CourseStudentRepository;

@RestController
@RequestMapping("/coursestudent")
public class CourseStudentController {
	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	//成绩（选修）
	@Autowired
	private CourseStudentRepository courseStudentRepo;//与dao（repository）层进行交互
	
	//得到所有选修课程信息，比如axios请求：/coursestudent/allCourseStudent
	@RequestMapping(value = "/allCourseStudent", method = RequestMethod.GET)
	public Map<String,Object> getAllCourseStudent(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Iterable<CourseStudent> coursestudent = courseStudentRepo.findAll();
		  	map.put("result", true);
		  	map.put("rows", coursestudent);
		  	map.put("msg","查询选修、成绩课程信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","查询选修、成绩课程信息失败!");
		    return map;
		}
	}
	
	//根据选修课程Id查表，比如axios请求：/coursestudent/courseStudentId/1
	@RequestMapping(value = "/courseStudentId/{id}", method = RequestMethod.GET)
	public Map<String,Object> getCourseStudentById(@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Optional<CourseStudent> coursestudent = courseStudentRepo.findById(id);
		  	map.put("result", true);
		  	map.put("rows", coursestudent);
		  	map.put("msg","根据选修id查询选修课程信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","根据选修id查询选修课程信息失败!");
		    return map;
		}
	}
	
	//根据选修分数查询，比如axios请求：/coursestudent/courseStudentScore?score=60
	@RequestMapping(value = "/courseStudentScore", method = RequestMethod.GET)
	public Map<String,Object> getCourseStudentByScore(@RequestParam(value ="score") Float score){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Optional<CourseStudent> coursestudent = courseStudentRepo.findByScore(score);
		  	map.put("result", true);
		  	map.put("rows", coursestudent);
		  	map.put("msg","根据分数查询课程信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","根据分数查询课程信息失败!");
		    return map;
		}
	}
	
	//添加选修课程，比如axios请求：/coursestudent/addCourseStudent?CourseStudent(对象)
	@RequestMapping(value = "/addCourseStudent", method = RequestMethod.POST)
	public Map<String,Object> addCourseStudent(@RequestBody CourseStudent coursestudent) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			CourseStudent cs = courseStudentRepo.save(coursestudent);
			map.put("result", true);
			map.put("rows", cs);
			map.put("msg","成功添加了课程!");
			return map;
		}catch(Exception e) {
			logger.error(e.getMessage());
			map.put("result", false);
			map.put("msg","添加课程失败!");
			return map;
		}
	}
	
	//删除一个选修课程,比如axios请求：/coursestudent/deleteCourseStudent/1
	@RequestMapping(value = "/deleteCourseStudent/{id}", method = RequestMethod.DELETE)
	public Map<String,Object> delCourseStudentById(@PathVariable Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			courseStudentRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","删除选修课程成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","删除选修课程失败!");
			return map;
		}
	}
		
	//修改一个选修,比如axios请求：/coursestudent/putCourseStudent?CourseStudent（对象）
	@RequestMapping(value = "/putCourseStudent", method = RequestMethod.PUT)
	public Map<String,Object> putCourse(@RequestBody  CourseStudent coursestudent) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			CourseStudent cs = courseStudentRepo.save(coursestudent);
			map.put("result", true);
			map.put("rows", cs);
			map.put("result", true);
			map.put("msg","修改选修成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改选修失败!");
			return map;
		}
	}
}
