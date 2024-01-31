package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	// 필드
	
	// 생성자
	// 기본 생성자 사용
	
	// 메소드 g/s
	
	// 메소드 일반
	
	// 작가 등록
	public int authorInsert(String name, String desc) {
		
		// book_db 데이터베이스에 접속
		// book /book
		// author 테이블에
		// 작가를 insert / 작가이름, 작가설명
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		
		try {
			// 1. JDBC 드라이버 (Oracle)로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
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
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
			
		} finally {
			
			// 5. 자원정리
			
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
		return count;
		
	} // authorInsert();

	// 작가 삭제
	public int authorDelete(int no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
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
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("errer:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("errer:" + e);
			}
			
		}
		return count;
		
		
	} // authorDelete();
	
	// 작가 수정
	public int authorUpdate(String name, String desc, int no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
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
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} finally{
			
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch(SQLException e) {
				System.out.println("error:" + e);
			}
			
		}
		return count;
		
	} // authorUpdate();
	
	// 작가 리스트 보기
	public List<AuthorVo> authorList() {
		
		// 리스트만들기
		// db에서 데이터 가져오고
		// 리스트 추가
		
		// 리스트 주소전달
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
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
				
				authorList.add(new AuthorVo(no, name, desc));
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} finally{
			
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch(SQLException e) {
				System.out.println("error:" + e);
			}
		
			
		}

		return authorList;
		
	} // authorList
	
	
}
