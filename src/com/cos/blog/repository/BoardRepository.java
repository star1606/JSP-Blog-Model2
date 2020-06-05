package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;

// DAO

public class BoardRepository {
	// 싱글톤
	private static BoardRepository instance = new BoardRepository();

	private BoardRepository() {
	}

	private static final String TAG = "BoardRepository: ";

	public static BoardRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// CRUD 만들기
	public int save(Board board) {
		final String SQL = "INSERT INTO board(id, userId, title, content, readCount, createDate) VALUES(board_seq.nextval, ?, ?, ?, ?, sysdate)";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getReadCount());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1; // 실패
	}

	public int update(Board Board) {
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

	public int deleteById(int id) { // 객체 받을 필요가 없다 id만 있으면됨
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

	// 관리자 계정으로 다 찾는 것...
	public List<Board> findAll() { // 다 찾을거니까 매개변수가 필요없다
		final String SQL = "SELECT * FROM board ORDER BY id DESC";
		List<Board> boards = new ArrayList<>();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);

			// while 돌려서 rs -> 오브젝트에 집어 넣기
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")

				);
				boards.add(board);
				//System.out.println(boards.add(board));
			}

			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}

	// 사용자의 select
	public Board findById(int id) { // id만 찾을거니까 매개변수가 필요없다
		final String SQL = "";
		Board board = new Board();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// if 돌려서 rs -> 오브젝트에 집어 넣기

			return board;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}

}
