package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.Major;
import com.studentmanage.ManageInfo.repo.MajorRepository;

@RestController
@RequestMapping("/major")
public class MajorController {
	@Autowired
	private MajorRepository majorRepo;
	@GetMapping("/major")  //得到所有专业的信息http://localhost:8080/major/major
	public Map<String,Object> getAllMajor(){
		  Map<String,Object> map = new HashMap<String,Object>();
		  try {
	  		Iterable<Major> majors = majorRepo.findAll();
	  		map.put("result", true);
	  		map.put("rows", majors);
	  		map.put("msg","查询专业信息成功!");
	  		return map;
		  }catch(Exception e) {
			  map.put("result", false);
			  map.put("msg","查询专业信息失败!");
			  return map;
	    }
	}
}
