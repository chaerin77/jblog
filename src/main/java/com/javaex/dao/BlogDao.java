package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired SqlSession sqlSession;
	
	public BlogVo getUserName(String id) {

		return sqlSession.selectOne("blog.getUserName",id);
	}
	
	public void updateLogoTitle(BlogVo blogVo) {
	
		sqlSession.update("blog.updateLogoTitle", blogVo);
	}
	
}
