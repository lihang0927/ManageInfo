package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.CourseTeacher;
import com.studentmanage.ManageInfo.repo.CourseTeacherRepository;


@RestController
@RequestMapping("/courseteacher")
public class CourseTeacherController {
	@Autowired
	private CourseTeacherRepository courseTeacherRepo;//与dao（repository）层进行交互
	
	@RequestMapping(value = "/allCourseTeacher", method = RequestMethod.GET)//得到所有授课，比如axios请求：/courseteacher/allCourseTeacher
	public Map<String,Object> getAllCourseStudent(){
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
}
