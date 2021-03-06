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
import com.javaex.vo.BlogVo;

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
	
	//로고파일 업로드,블로그 제목 기본설정 변경
	@RequestMapping("/{id}/logoFileUpload")
	public String logoFileUpload(@PathVariable("id") String id,
								 @RequestParam("file") MultipartFile file, 
								 @RequestParam("blogTitle") String blogTitle,
								 Model model) {
		System.out.println("BlogController/logoFileUpload");
	
		//파일경로등.. blogservice에 restore메소드만들기
		String logoFile = blogService.restore(file);
		//model.addAttribute("logoFile",logoFile);
		
		//blogService.getUserName(id, model);//모델에 blogVo넣어야 blog-header 아이디제대로나옴
		//blogService.updateLogoTitle(blogVo);
		
		//blog-admin-basic에서 입력한 값 blogVo에저장
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setBlogTitle(blogTitle);
		blogVo.setLogoFile(logoFile);
		blogService.updateLogoTitle(blogVo,model);
		System.out.println(blogVo);
		
		return "redirect:/{id}";
	}
	
	//카테고리
	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable("id") String id,
						   Model model) {
		System.out.println("BlogController/category");
		
		blogService.getUserName(id, model);
		
		return "blog/admin/blog-admin-cate";
	}
	
}
