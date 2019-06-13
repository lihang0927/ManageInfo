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
import com.studentmanage.ManageInfo.repo.ClassesRepository;


@RestController
@RequestMapping("/classes")
public class ClassesController {
	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private ClassesRepository classesRepo;//与dao（repository）层进行交互
	
	//得到所有班级，比如axios请求：/classes/allClasses
	@RequestMapping(value = "/allClasses", method = RequestMethod.GET)
	public Map<String,Object> getAllClasses(){
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
	
	//根据班级Id查表，比如axios请求：/classes/classesId/1
	@RequestMapping(value = "/classesId/{id}", method = RequestMethod.GET)
	public Map<String,Object> getClassesById(@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Optional<Classes> classes = classesRepo.findById(id);
		  	map.put("result", true);
		  	map.put("rows", classes);
		  	map.put("msg","根据班级Id查询班级信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","根据班级Id查询班级信息失败!");
		    return map;
		}
	}
		
	//根据班级name查表，比如axios请求：/classes/classesName?name=2班
	@RequestMapping(value = "/classesName", method = RequestMethod.GET)
	public Map<String,Object> getClassesByname(@RequestParam(value ="name") String name){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Optional<Classes> classes = classesRepo.findByName(name);
		  	map.put("result", true);
		  	map.put("rows", classes);
		  	map.put("msg","根据班级名查询班级信息成功!");
		  	return map;
		}catch(Exception ex) {
			map.put("result", false);
		    map.put("msg","根据班级名查询班级信息失败!");
		    return map;
		}
	}
		
	//添加，比如axios请求：/classes/addClasses?classes(对象)
	@RequestMapping(value = "/addClasses", method = RequestMethod.POST)
	public Map<String,Object> addClasses(@RequestBody Classes Classes) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Classes classes = classesRepo.save(Classes);
			map.put("result", true);
			map.put("rows", classes);
			map.put("msg","成功添加了班级!");
			return map;
		}catch(Exception e) {
			logger.error(e.getMessage());
			map.put("result", false);
			map.put("msg","添加班级失败!");
			return map;
		}
	}
		
	//删除一个课程,比如axios请求：/classes/deleteClasses/1
	@RequestMapping(value = "/deleteCourse/{id}", method = RequestMethod.DELETE)
	public Map<String,Object> delClassesById(@PathVariable Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			classesRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","删除班级成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","删除班级失败!");
			return map;
		}
	}
	
	//修改一个班级,比如axios请求：/classes/putClasses?Classes（对象）
	@RequestMapping(value = "/putClasses", method = RequestMethod.PUT)
	public Map<String,Object> putCourse(@RequestBody  Classes classes) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Classes c = classesRepo.save(classes);
			map.put("result", true);
			map.put("rows", c);
			map.put("result", true);
			map.put("msg","修改班级成功!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改课程失败!");
			return map;
		}
	}
}
