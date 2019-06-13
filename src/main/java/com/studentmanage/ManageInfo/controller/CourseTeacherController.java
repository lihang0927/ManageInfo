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
import com.studentmanage.ManageInfo.model.CourseStudent;
import com.studentmanage.ManageInfo.model.CourseTeacher;
import com.studentmanage.ManageInfo.repo.CourseTeacherRepository;


@RestController
@RequestMapping("/courseteacher")
public class CourseTeacherController {
	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private CourseTeacherRepository courseTeacherRepo;//与dao（repository）层进行交互
	
	//得到所有授课，比如axios请求：/courseteacher/allCourseTeacher
	@RequestMapping(value = "/allCourseTeacher", method = RequestMethod.GET)
	public Map<String,Object> getAllCourseTeacher(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Iterable<CourseTeacher> courseteacher = courseTeacherRepo.findAll();
		  	map.put("result", true);
		  	map.put("rows", courseteacher);
		  	map.put("msg","查询授课信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","查询授课课程信息失败!");
		    return map;
		}
	}
	
	//根据授课Id查表，比如axios请求：/courseteacher/courseTeacherId/1
	@RequestMapping(value = "/courseTeacherId/{id}", method = RequestMethod.GET)
	public Map<String,Object> getCourseTeacherById(@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Optional<CourseTeacher> coursestudent = courseTeacherRepo.findById(id);
		  	map.put("result", true);
		  	map.put("rows", coursestudent);
		  	map.put("msg","根据授课id查询选修课程信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","根据授课id查询选修课程信息失败!");
		    return map;
		}
	}
	
	
	//添加授课，比如axios请求：/courseteacher/addCourseTeacher?CourseTeacher(对象)
	@RequestMapping(value = "/addCourseTeacher", method = RequestMethod.POST)
	public Map<String,Object> addCourseTeacher(@RequestBody CourseTeacher courseteacher) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			CourseTeacher ct = courseTeacherRepo.save(courseteacher);
			map.put("result", true);
			map.put("rows", ct);
			map.put("msg","成功添加了授课!");
			return map;
		}catch(Exception e) {
			logger.error(e.getMessage());
			map.put("result", false);
			map.put("msg","添加授课失败!");
			return map;
		}
	}
	
	//删除一个授课,比如axios请求：/courseteacher/deleteCourseTeacher/1
	@RequestMapping(value = "/deleteCourseTeacher/{id}", method = RequestMethod.DELETE)
	public Map<String,Object> delCourseTeacherById(@PathVariable Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			courseTeacherRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","删除授课成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","删除授课失败!");
			return map;
		}
	}
	
	//修改授课,比如axios请求：/courseteacher/putCourseTeacher?CourseTeacher（对象）
	@RequestMapping(value = "/putCourseTeacher", method = RequestMethod.PUT)
	public Map<String,Object> putCourse(@RequestBody  CourseTeacher courseteacher) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			CourseTeacher ct = courseTeacherRepo.save(courseteacher);
			map.put("result", true);
			map.put("rows", ct);
			map.put("result", true);
			map.put("msg","修改授课成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改授课失败!");
			return map;
		}
	}
}
