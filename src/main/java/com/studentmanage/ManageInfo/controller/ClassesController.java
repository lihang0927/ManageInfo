package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.Classes;
import com.studentmanage.ManageInfo.repo.ClassesRepository;


@RestController
@RequestMapping("/classes")
public class ClassesController {
	@Autowired
	private ClassesRepository classesRepo;//与dao（repository）层进行交互
	
	@RequestMapping(value = "/allClasses", method = RequestMethod.GET)//得到所有班级，比如axios请求：/classes/allClasses
	public Map<String,Object> getAllCourseStudent(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Iterable<Classes> classes = classesRepo.findAll();
		  	map.put("result", true);
		  	map.put("rows", classes);
		  	map.put("msg","查询所有班级信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","查询所有班级信息失败!");
		    return map;
		}
	}
}
