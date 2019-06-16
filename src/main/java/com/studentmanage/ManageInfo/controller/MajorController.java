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

import com.studentmanage.ManageInfo.model.Major;
import com.studentmanage.ManageInfo.model.Teacher;
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
	@PostMapping("/major")//添加一个专业的信息
	public Map<String,Object> addMajor(@RequestBody Major major){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Major m = majorRepo.save(major);
			map.put("result",true);
			map.put("student",m);
			map.put("msg", "添加专业成功！");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","添加专业失败!");
			return map;
		}
	}
	@DeleteMapping("/major/{id}")//根据专业信息中的序号id进行删除
	public Map<String,Object> delMajorById(@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			majorRepo.deleteById(id);
			map.put("result", true);
			map.put("msg","成功删除了专业!");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","删除专业失败!");
			return map;
	    }
	}
	@PutMapping("/major")//修改一个专业，和添加专业的时候传入的参数相同
	public Map<String,Object> editMajor(@RequestBody Major major){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Major m = majorRepo.save(major);
			map.put("result",true);
			map.put("student",m);
			map.put("msg", "修改专业成功！");
			return map;
		}catch(Exception e) {
			map.put("result", false);
			map.put("msg","修改专业失败!");
			return map;
		}
	}
}
