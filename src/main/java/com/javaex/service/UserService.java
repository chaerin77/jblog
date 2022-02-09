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
	public UserVo login(UserVo userVo) {
		
		return userDao.login(userVo); 
		
		
		
	}
	
}
