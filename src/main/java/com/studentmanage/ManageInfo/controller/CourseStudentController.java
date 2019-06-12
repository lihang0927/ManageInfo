package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.CourseStudent;
import com.studentmanage.ManageInfo.repo.CourseStudentRepository;

@RestController
@RequestMapping("/coursestudent")
public class CourseStudentController {
		//成绩（选修）
		@Autowired
		private CourseStudentRepository courseStudentRepo;//与dao（repository）层进行交互
		
		@RequestMapping(value = "/allCourseStudent", method = RequestMethod.GET)//得到所有选修课程，比如axios请求：/coursestudent/allCourseStudent
		public Map<String,Object> getAllCourseStudent(){
			Map<String,Object> map = new HashMap<String,Object>();
			try {
				Iterable<CourseStudent> coursestudent = courseStudentRepo.findAll();
			  	map.put("result", true);
			  	map.put("rows", coursestudent);
			  	map.put("msg","查询选修课程信息成功!");
			  	return map;
			}catch(Exception ex) {
				map.put("result", false);
			    map.put("msg","查询选修课程信息失败!");
			    return map;
			}
		}
		
}
