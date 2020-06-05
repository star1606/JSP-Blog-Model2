package com.cos.blog.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createDate;
	
	
	// for문돌려서 꺼낼 필요없이 getTitle에서 해결할 수 있다
	public String getTitle() {
		title = title.replace("<", "&lt;").replace(">", "&gt;");
		return title;
	}
	
}
