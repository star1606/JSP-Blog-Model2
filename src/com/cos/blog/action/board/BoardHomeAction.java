package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;

public class BoardHomeAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// 1.DB연결해서 Board 목록 다 불러와서
		// 2.Request에 담고 
		// 3.이동 home.jsp
		
		BoardRepository boardRepository =
				BoardRepository.getInstance();
		List<Board> boards = boardRepository.findAll();
		
		for(Board board : boards) {
				String preview = board.getContent();
				preview = preview.substring(0, 2) + "...";
				board.setContent(preview);
 			
		}
		
		for (Board board : boards) {
			
		}
		
		
		
		request.setAttribute("boards", boards);
		
		
		RequestDispatcher dis = 
				request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
		
		
		//본문 짧게 가공하기
		
		
	}
}
