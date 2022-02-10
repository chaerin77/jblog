package com.javaex.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원가입
	public void join(UserVo userVo) {
		
		userDao.join(userVo);
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
