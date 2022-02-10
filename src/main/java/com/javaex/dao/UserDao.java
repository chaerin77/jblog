package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	public void join(UserVo userVo) {
		
		sqlSession.insert("user.join", userVo);
	}
	
	//회원가입시 입력한 아이디 블로그테이블에 저장
	public void blogId(BlogVo blogVo) {
		
		sqlSession.insert("user.blogId", blogVo);
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		
		return sqlSession.selectOne("user.login", userVo);
	}
	
	//회원가입시 아이디중복체크
	public UserVo overCheck(UserVo userVo) {
		
		return sqlSession.selectOne("overCheck", userVo);
	}
}
