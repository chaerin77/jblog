package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {
	
	@Autowired SqlSession sqlSession;
	
	public String getUserName(String id) {

		return sqlSession.selectOne("blog.getUserName",id);
	}
	
}
