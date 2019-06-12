package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
