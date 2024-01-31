package com.javaex.ex01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		//authorDao.authorInsert("이름", "세부정보");
		
		//authorDao.authorDelete(12);
		
		//authorDao.authorUpdate("황일영", "강사", 6);
		
		List<AuthorVo> authorList = authorDao.authorList();	
		
		/*
		int cnt = authorDao.authorInsert("유승수", "학생");
		System.out.println(cnt);
		*/
		for(AuthorVo authorVo : authorList) {
			int id = authorVo.getAuthorId();
			String name = authorVo.getAuthorName();
			String desc = authorVo.getAuthorDesc();
			System.out.println(id + ", " + name + ", " + desc);
		}
		
		/*
		for(int i=0; i<authorList.size(); i++) {
			int id = authorList.get(i).getAuthorId();
			String name = authorList.get(i).getAuthorName();
			String desc = authorList.get(i).getAuthorDesc();
			System.out.println(id + ", " + name + ", " + desc);
		}
		*/
		
	}

}
