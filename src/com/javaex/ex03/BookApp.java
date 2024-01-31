package com.javaex.ex03;

public class BookApp {

	public static void main(String[] args) {
		
		
		
		//작가5명 등록
					
		//작가 출력
		
		
		/*
		List<BookVo> bookList = bookDao.bookList();
		for(BookVo vo : bookList) {
			System.out.println(vo.getBookId() + vo.getTitle() + vo.getPubs() + vo.getPubDate() + vo.getAuthorId() + vo.getAuthorName() +vo.getAuthorDesc());
		}
		*/
				
		//작가2명 삭제
				
		//작가1명 수정
			
		//작가 출력
			
		//책 5권등록
		BookDao bookDao = new BookDao();
		
		BookVo bookVo = new BookVo();
		bookVo.setTitle("책이름");
		bookVo.setPubs("출판사");
		bookVo.setPubDate("2024-01-30");
		bookVo.setAuthorId(3);
		
		bookDao.bookInsert(bookVo);
				
		//책 수정
				
		//책 삭제
				
		//책 1권출력
				
		//책만 다 출력
				
        //책+작가 다출력		
		
	}

}
