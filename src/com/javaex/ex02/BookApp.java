package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		//authorDao.authorInsert("지금은", "점심시간");
		
		//authorDao.authorInsert("서장훈", "농구선수");
		
		List<AuthorVo> authorList = authorDao.authorList();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() + ". " + vo.getAuthorName() + " " +vo.getAuthorDesc() );
		}
		
		//authorDao.authorDelete(13);
		//authorDao.authorDelete(14);
		//authorDao.authorDelete(15);
		
		
	}

}
