package com.cos.blog.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인해야지 글 쓸 수 있다.
		
		HttpSession session = request.getSession();
		if(session.getAttribute("principal")==null) {   //principal 인증주체
			Script.getMessage("잘못된 접근입니다.", response);
			// 글쓰기 버튼 안보이지만 주소로 들어왔을 때 못들어오게 막아야한다.
			
		} else {
		RequestDispatcher dis = 
				request.getRequestDispatcher("board/write.jsp");
		dis.forward(request, response);
	}
		
		
	}

}