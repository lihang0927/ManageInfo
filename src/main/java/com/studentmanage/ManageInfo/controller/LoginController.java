package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {
	@GetMapping("/login")
	public Map<String,Object> getAllColleges(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("result", true);
		    map.put("data", "123");
		    map.put("msg","成功登陆");
		    return map;
		}catch(Exception e) {
		     map.put("result", false);
		     map.put("msg","登陆失败");
		     return map;
		}
	}
}