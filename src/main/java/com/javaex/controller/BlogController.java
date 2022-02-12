package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	//{회원아이디} 주소로 블로그접속
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model) {
		System.out.println("BlogController/{id}");
		
		blogService.getUserName(id, model);
		
		return "blog/blog-main";
	}
	
	//블로그페이지에서 로그아웃시 블로그페이지는 떠있는채 로그인버튼으로 바뀌게끔
	@RequestMapping("/{id}/logout")
	public String logout(@PathVariable("id") String id,HttpSession session) {
		System.out.println("BlogController/{id}/logout");
		
		blogService.bloglogout(id, session);
		
		return "redirect:/{id}";
		//세션값 삭제후 리다이렉트  /{id} 이렇게해주면될듯?
	}
	
	
	//내블로그 관리
	@RequestMapping("/{id}/admin/basic")
	public String adminbasic(@PathVariable("id") String id, Model model) {
		System.out.println("BlogController/adminbasic");
		
		blogService.getUserName(id, model);//이 작업을 안해서 {id}/logout 했을때 blogVo=null 떴던것같음
		
		return "blog/admin/blog-admin-basic";//경로 잘쓰기
	}
	
	//로고파일 업로드
	@RequestMapping("/logoFileUpload")
	public String logoFileUpload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("BlogController/logoFileUpload");
	
		//파일경로등.. blogservice에 restore메소드만들기
		String saveName = blogService.restore(file);
		model.addAttribute("saveName",saveName);
		
		return "redirect:/{id}";
	}
	
}
