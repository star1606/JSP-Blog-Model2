package com.cos.blog.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.Users;


// DAO

public class ReplyRepository {
	// 싱글톤
	private static ReplyRepository instance = new ReplyRepository();

	private ReplyRepository() {
	}
	private static final String TAG = "ReplyRepository: ";
	public static ReplyRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	


	
	
	// CRUD 만들기
	public int save(Reply reply) {
		final String SQL = "";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save: " + e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1; // 실패
	}
	
	public int update(Reply reply) {
		final String SQL = "";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update: " + e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1; // 실패
	}
	
	public int deleteById(int id) {   //객체 받을 필요가 없다 id만 있으면됨
		final String SQL = "";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteById: " + e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1; // 실패
	}
	//관리자 계정으로 다 찾는 것...
	public List<Reply> findAll() { // 다 찾을거니까 매개변수가 필요없다
		final String SQL = "";
		List<Reply> replies = new ArrayList<>();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// while 돌려서 rs -> 오브젝트에 집어 넣기
			
			
			
			return replies;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll: " + e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}
											//사용자의 select
	public Reply findById(int id) { // id만 찾을거니까 매개변수가 필요없다
		final String SQL = "";
		Reply reply = new Reply();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			//if 돌려서 rs -> 오브젝트에 집어 넣기
			
			
			
			return reply;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById: " + e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}
	
	

}
