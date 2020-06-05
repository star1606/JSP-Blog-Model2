package com.cos.blog.model;

import java.sql.Timestamp;

import com.cos.blog.model.Board.BoardBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
	private int id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String userProfile;
	private String userRole;
	private Timestamp createDate;
	
	//로컬데이트 타임으로
	
}
