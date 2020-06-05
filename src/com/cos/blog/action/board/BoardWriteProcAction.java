package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardWriteProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0번 인증확인
		// 1번 request에 title 값과 content 값 null 인지 공백인지 확인
		// 2번 request에 title 값과 content 값 받기
		// 3번 title값과 content, principal.getId()값을 Board 오브젝트에 담기,
		// 4번 BoardRepository 연결해서 save(title, content)함수 호출
		// 5번 result == 1이면 성공로직 (index.jsp)로 이동
		// 6번 result !== 1이면 실패로직(history.back())
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) { // principal 인증주체
			Script.getMessage("잘못된 접근입니다.", response);
			// 글쓰기 버튼 안보이지만 주소로 들어왔을 때 못들어오게 막아야한다.
			return;
		}

		Users principal = (Users) session.getAttribute("principal");

		if (
				request.getParameter("title") == null ||
				request.getParameter("title").equals("") ||
				request.getParameter("content") == null||
				request.getParameter("content").equals("") 
					
		

		)

		{
			return;
		}

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// principal getId()값을 어떻게 넣지?
		Board board = Board.builder()
				.userId(principal.getId())
				.title(title)
				.content(content)
				.readCount(0)
				.build();

		BoardRepository boardRepository =
				BoardRepository.getInstance();

		// save(title, content)함수 호출???
		int result = boardRepository.save(board);

		if (result == 1) {
			RequestDispatcher dis =
					request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
			
		

			
		} else {
			Script.back("글쓰기에 실패하였습니다.", response);

		}

	}

}
