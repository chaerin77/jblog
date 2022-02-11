package com.javaex.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	//id,userName,blogTitle 가져와서 ds에넣기
	public String getUserName(String id,Model model) {
		String count = "1";
		
		BlogVo blogVo = blogDao.getUserName(id);
		model.addAttribute("blogVo", blogVo);
		
		return count;
	}
	
	//블로그페이지에서 로그아웃
	public String bloglogout(String id,HttpSession session) {
		String count ="1";
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return count;
	}
}
