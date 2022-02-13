package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

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
	
	//로고파일 업로드
	public String restore(MultipartFile file) {
		
		String saveDir = "D:\\javaStudy\\blogupload";
		
		//원본 파일 이름
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		//저장파일 이름
		String saveName = System.currentTimeMillis()+ UUID.randomUUID().toString() + exName;
		
		//파일패스
		String filePath = saveDir + "\\" + saveName;
		
		//파일사이즈
		long fileSize = file.getSize();
	
		
		//파일 저장(업로드)
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}
	
	/*
	public void updateLogoTitle(BlogVo blogVo) {

		blogDao.updateLogoTitle(blogVo);
	}*/
	
	public void updateLogoTitle(BlogVo blogVo, Model model) {
		
		//model.addAttribute("bVo",blogVo);
		
		blogDao.updateLogoTitle(blogVo);
	}
}
