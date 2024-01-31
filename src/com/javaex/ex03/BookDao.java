package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";
	
	// 생성자
	
	// 메소드 g/s
	
	// 메소드 일반
	
	public void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			}catch(ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			}catch (SQLException e) {
				 System.out.println("error:" + e);
			}
	} // getConnection();
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	} // close();
	
	public int bookInsert(BookVo bookVo) {
		this.getConnection();
		int count = -1;
		try {
			String query = "";
			query += " insert into book ";
			query += " values (null, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle() );
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			
			count = pstmt.executeUpdate();
			System.out.println(count + "건 추가되었습니다.");
		} catch (SQLException e) {
			 System.out.println("error:" + e);
		} 
		this.close();
		return count;
	} // bookInsert
	
	public int bookDelete(BookVo bookVo) {
		this.getConnection();
		int count = -1;
		try {
			String query ="";
			query += " delete from book ";
			query += " where book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookVo.getBookId());
			
			count = pstmt.executeUpdate();
			System.out.println(count + "건 삭제되었습니다.");
			
		}catch (SQLException e) {
			 System.out.println("error:" + e);
		} 
		this.close();
		return count;
		
	} // bookDelete
	
	public int bookUpdate(BookVo bookVo) {
		this.getConnection();
		int count = -1;
		try {
			String query = "";
			query += " update  book ";
			query += " set  book_id = ? ";
			query += "     ,title = ? ";
			query += "     ,pubs = ? ";
			query += "     ,pub_date = ? ";
			query += "     ,author_id = ? ";
			query += " where book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookVo.getBookId());
			pstmt.setString(2, bookVo.getTitle());
			pstmt.setString(3, bookVo.getPubs());
			pstmt.setString(4, bookVo.getPubDate());
			pstmt.setInt(5, bookVo.getAuthorId());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 수정되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;
	} // bookUpdate
	
	

}
