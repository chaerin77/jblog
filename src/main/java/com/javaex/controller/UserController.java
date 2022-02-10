package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("UserController/joinForm");
		
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController/join");
		System.out.println(userVo);
		userService.join(userVo);
		
		return "user/joinSuccess";
	}
	
	
	//로그인 폼
	@RequestMapping("/loginForm")//주소
	public String loginForm() {
		System.out.println("UserController/loginForm");
		
		return "user/loginForm";
	}
	
	
	//로그인
	@ResponseBody	
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController/login");
		
		UserVo authUser = userService.login(userVo);//id,userName 갖고오기
		if(authUser != null) {
			System.out.println("로그인 성공");
			return "1";
		}else {
			System.out.println("로그인 실패");
			return "0"; 
		}
	}
	
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		//세션정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}

	
	//회원가입시 중복체크
	@ResponseBody
	@RequestMapping("/overCheck")
	public String overCheck(@ModelAttribute UserVo userVo) {
		System.out.println(userVo);
		
		String result = userService.overCheck(userVo);
		
		return result;
		
	}
	
	
}
