package com.javaex.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원가입
	public void join(UserVo userVo) {
		
		userDao.join(userVo);
	}
	
	//회원가입시 입력한 아이디 블로그테이블에 저장
	public void blogId(UserVo userVo) {
		//입력한 Id 가져와서 블로그 주소 blogTitle 만들기
		/*1.
		String id = userVo.getId();
		String blogTitle = id +"의 블로그입니다.";
		BlogVo blogVo = new BlogVo(id,blogTitle);
		//id + 블로그입니다 -> userName +블로그입니다 로 고치도록 ->id값으로 userName갖고오는 쿼리문만들기
		userDao.blogId(blogVo);*/
		
		//2.
		String id = userVo.getId();
		String userName = userVo.getUserName();
		//System.out.println(userName); 확인용
		String blogTitle = userName + "의 블로그입니다.";
		//BlogVo blogVo = new BlogVo(id,blogTitle);
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogTitle(blogTitle);
		blogVo.setId(id);
		userDao.blogId(blogVo);
		
	}
	
	
	//로그인
	public String login(UserVo userVo, HttpSession session) {
		
		UserVo authUser = userDao.login(userVo);//id,userName 갖고오기
		
		if(authUser != null) { //db에서 select한 값이 존재하면
			session.setAttribute("authUser", authUser);//세션값넣기잊지말기
			return "1";
		}else{
			return "0";
		}
	}
	
	
	//회원가입시 중복체크
	public String overCheck(UserVo userVo) {
			
		UserVo saveId = userDao.overCheck(userVo);
			
		if(saveId == null) {
			return "0";
		}else {
			return "1";
		}
		
	}
	
	
	
}
