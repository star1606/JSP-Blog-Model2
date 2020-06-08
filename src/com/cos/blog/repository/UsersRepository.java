package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Users;

// DAO

public class UsersRepository {
	// 싱글톤
	private static UsersRepository instance = new UsersRepository();

	private UsersRepository() {
	}

	private static final String TAG = "UserRepository: ";

	public static UsersRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public int findByUsername(String username) {   // count값을 리턴해서 해결한다
		// 아이디 정확하게 넣으면 되고
		final String SQL = "SELECT count(*) FROM users WHERE username = ?";
		Users user = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setString(1, username);
	

			// if 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery(); // 실행을 하고 (업데이트가 아니라서)
			//커서로 한칸씩 내려가는것

			if (rs.next()) {
				return rs.getInt(1); // 첫번째 컬럼	0아니면 1 return 
			}							// 무조건 true나옴 0아니면 1

			//user null이면 로그인실패 user가 null이 아니면 로그인된거
			//rs.next() null이면 false로 반환하나? yes

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsername: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return -1;

	}

	public Users findByUsernameAndPassword(String username, String password) {
		// 아이디 정확하게 넣으면 되고
		final String SQL = "SELECT id, username, email, address, userProfile, userRole, createDate FROM users WHERE username = ? AND password = ?";
		Users user = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			// if 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery(); // 실행을 하고 (업데이트가 아니라서)
			// 커서로 한칸씩 내려가는것

			if (rs.next()) {
				user = new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setUserRole(rs.getString("userRole"));
				user.setCreateDate(rs.getTimestamp("createDate"));
			}

			// user null이면 로그인실패 user가 null이 아니면 로그인된거
			// rs.next() null이면 false로 반환하나? if

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsernameAndPassword: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;

	}

	// CRUD 만들기
	public int save(Users user) {
		final String SQL = "INSERT INTO users(id, username, password, email, address, userRole, createDate) VALUES(USERS_SEQ.nextval, ?, ?, ?, ?,?, sysdate)";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserRole());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1; // 실패
	}

	public int update(Users user) {
		final String SQL = "UPDATE users SET password =?, email=?, WHERE id=?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getAddress());
			pstmt.setInt(4, user.getId());
			
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
	public List<Users> findAll() { // 다 찾을거니까 매개변수가 필요없다
		final String SQL = "";
		List<Users> users = new ArrayList<>();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// while 돌려서 rs -> 오브젝트에 집어 넣기

			return users;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}

	// 사용자의 select
	public Users findById(int id) { // id만 찾을거니까 매개변수가 필요없다
		final String SQL = "SELECT * FROM users WHERE id =?";
		Users user = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
					user = Users.builder()
								.id(rs.getInt("id"))
								.username(rs.getString("username"))
								.email(rs.getString("email"))
								.address(rs.getString("address"))
								.createDate(rs.getTimestamp("createDate"))
								.build();
				
				
			}
			
			
			

			return user;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById: " + e.getMessage());

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}

}
