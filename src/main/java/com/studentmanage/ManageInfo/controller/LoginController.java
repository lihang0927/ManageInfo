package com.studentmanage.ManageInfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanage.ManageInfo.model.UserLogin;
import com.studentmanage.ManageInfo.repo.UserRepository;



@RestController
@RequestMapping("/login")//接口注解
public class LoginController {
	
	@Autowired
	private UserRepository usrepo;//与dao（repository）层进行交互
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Map<String,Object> getAllColleges(@RequestParam(required = false,value ="name") String name,@RequestParam(required = false,value ="password") String password){
		Map<String,Object> map = new HashMap<String,Object>();
		Iterable<UserLogin> userlogindata= usrepo.findByNameAndPassword(name,password);
		System.out.println(userlogindata);
		int flag=0;
		/*判断是否有对象*/
		for(UserLogin zdata: userlogindata) {
			flag=1;
		}
		if(flag==1) {
			map.put("result", true);
		    map.put("statue", 200);
		    map.put("msg","登陆成功");
		    return map;
		}else{
		     map.put("result", false);
			 map.put("statue", 404);
		     map.put("msg","登陆失败");
		     return map;
		}
	}
}