package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	//회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("UserController/joinForm");
		
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping("/join")
	public String join() {
		System.out.println("UserController/join");
		
		
		return "user/joinSuccess";
	}
	
	

	@RequestMapping("/loginForm")//주소
	public String loginForm() {
		System.out.println("UserController/loginForm");
		
		return "user/loginForm";
	}
}
