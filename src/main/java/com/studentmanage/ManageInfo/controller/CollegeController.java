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

import com.studentmanage.ManageInfo.model.College;
import com.studentmanage.ManageInfo.repo.CollegeRepository;
@RestController
@RequestMapping("/college")
public class CollegeController {
	@Autowired
	private CollegeRepository collegeRepo;
	@GetMapping("/college")  //得到所有学院的信息http://localhost:8080/college/college
	public Map<String,Object> getAllStudentes(){
		  Map<String,Object> map = new HashMap<String,Object>();
		  try {
	  		Iterable<College> colleges = collegeRepo.findAll();
	  		map.put("result", true);
	  		map.put("rows", colleges);
	  		map.put("msg","查询学院信息成功!");
	  		return map;
		  }catch(Exception e) {
			  map.put("result", false);
			  map.put("msg","查询学院信息失败!");
			  return map;
	    }
	}
	@PostMapping("/college")//添加一个学院的信息
	public Map<String,Object> addCollege(@RequestBody College college){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			College c = collegeRepo.save(college);
			map.put("result",true);
			map.put("student",c);
			map.put("msg", "添加学院成功！");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","添加学院失败!");
			return map;
		}
	}
	@DeleteMapping("/college/{id}")//根据学院信息中的序号id进行删除
	public Map<String,Object> delCollegeById(@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			collegeRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","成功删除了学院!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","删除学院失败!");
			return map;
	    }
	}
	@PutMapping("/college")//修改学院
	public Map<String,Object> editCollege(@RequestBody College college){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			College c = collegeRepo.save(college);
			map.put("result",true);
			map.put("course", c);
			map.put("msg","成功修改了学院!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改学院失败!");
			return map;
		}
	}
}
