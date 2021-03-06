package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
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
		userService.join(userVo);
		
		userService.blogId(userVo);
		
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
	public String login(@ModelAttribute UserVo userVo, HttpSession session, Model model) {//세션필요
		System.out.println("UserController/login");
		
		String result = userService.login(userVo, session);//UserService의 결과 담김
		model.addAttribute("result",result);
		
		return result;
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
