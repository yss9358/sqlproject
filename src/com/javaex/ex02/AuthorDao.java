package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";

	// 생성자
	// 기본 생성자 사용
	
	// 메소드 g/s
	
	// 메소드 일반
	
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
	}
	
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
		
	}
	
	// 작가 등록
	public int authorInsert(String name, String desc) {
		
		int count = -1;
		this.getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			
			// 실행
			count = pstmt.executeUpdate();
			
			// 4. 결과처리
			System.out.println(count + "건 등록되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		
		return count;
		
	} // authorInsert();

	// 작가 삭제
	public int authorDelete(int no) {
		
		int count = -1;
		this.getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL 문 준비
			String query ="";
			query += " delete from author  ";
			query += " where author_id = ? ";
			
			// 바인딩
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			// 실행
			
			count = pstmt.executeUpdate();
			
			// 4. 결과처리
			System.out.println(count + "건 삭제되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("errer:" + e);
		} 
		this.close();
		return count;
		
	} // authorDelete();
	
	// 작가 수정
	public int authorUpdate(String name, String desc, int no) {
		
		int count = -1;
		this.getConnection();
		try {
			
			String query = "";
			query += " update  author ";
			query += " set author_name = ? ";
			query += "    ,author_desc = ? ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setInt(3, no);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 수정되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;
		
	} // authorUpdate();
	
	// 작가 리스트 보기
	public List<AuthorVo> authorList() {
		this.getConnection();
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		try {
			String query = "";
			query += " select  author_id ";
			query += "        ,author_name ";
			query += "        ,author_desc ";
			query += " from author ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(no, name, desc);
				authorList.add(authorVo);
			}
			
		} catch(SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return authorList;
	} // authorList
	
	
}
