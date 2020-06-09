package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.DetailResponseDto;
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
	
	
	// 1. 어떻게 쿼리를 짜면 1씩 readCount가 증가할까?, 시퀀스가 맞나? , 다른 필드가 필요할까
	// 2. 상세보기를 눌러서 1을증가하게 만들 수 있는 로직은뭘까?
	// 3. 
	public int readCountUpdate(Board board) {
		final String SQL = "INSERT INTO board readCount VALUES(board_seq.nextval)";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, board.getReadCount());
		
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "readCountUpdate: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1; // 실패
	}

	
	
	
	
	
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

	public int update(Board board) {
		final String SQL = "UPDATE board SET title = ?, content = ? WHERE id = ?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			
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
		System.out.println("BoardRepository: id : " + id);
		final String SQL = "DELETE FROM board WHERE id= ?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			pstmt.setInt(1, id);
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
	
	public List<Board> findAll(int page) { // 다 찾을거니까 매개변수가 필요없다
		StringBuilder sb = new StringBuilder();
		sb.append("select /*+ INDEX_DESC(BOARD SYS_C008308)*/id, ");
		sb.append("userId, title, content, readCount, createDate ");
		sb.append("from board ");
		sb.append("OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY; ");
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
	public DetailResponseDto findById(int id) { // id만 찾을거니까 매개변수가 필요없다
		StringBuilder sb = new StringBuilder();
		sb.append("select b.id, b.userId, b.title, b.content, b.readCount, b.createDate, u.username ");
		sb.append("FROM board b INNER JOIN users u ");
		sb.append("ON b.userId = u.id ");
		sb.append("WHERE b.id = ? ");
		
		// 데이터 트랜스퍼 오브젝트
		
		final String SQL = sb.toString();
		DetailResponseDto dto = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, id);
		
			// if 돌려서 rs -> 오브젝트에 집어 넣기
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new DetailResponseDto();  // null을 위에 미리 해주고 new 늦게 해준다
				
				//Board board = new Board();
				//board.setId(rs.getInt("b.id"));
				Board board = Board.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.title(rs.getString(3))
						.content(rs.getString(4))
						.readCount(rs.getInt(5))
						.createDate(rs.getTimestamp(6))
						.build();
				dto.setBoard(board);
				dto.setUsername(rs.getString(7));
				
			}	
			return dto;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}

}
