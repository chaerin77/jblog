package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model,HttpSession session) {
		System.out.println("BlogController/{id}");
		System.out.println(id);//세션값..authuser.id였던것
		model.addAttribute("id", id);
		
		//select문짜기 id가 {id}값인 것의 userName갖고오기..
		String userName = blogService.getUserName(id);
		model.addAttribute("uName",userName);
		System.out.println(userName);
		System.out.println(session.getAttribute("authUser"));//세션유지 확인용
		
		//blogVo에 저장한 데이터로 select문 만들어서 갖고오는방법도 될듯
		
		return "blog/blog-main";
	}
}
