package com.cos.blog.action.user;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사
		if (request.getParameter("username").equals("") || request.getParameter("username") == null
				|| request.getParameter("password").equals("") || request.getParameter("password") == null
		// 이메일도 유효성검사해야한다

		) {
			return; // void 지면 return하면 실행안되고 튕겨나감, 로그남겨서 공격받은거
					// 저장한다
		}

		// key값오니까 반환가짐
		String username = request.getParameter("username");
		String rawpassword = request.getParameter("password");
		String password = SHA256.encodeSha256(rawpassword);
		
		// 로그인 함수 호출
		UsersRepository usersRepository = UsersRepository.getInstance();
		Users user = usersRepository.findByUsernameAndPassword(username, password); // ID passowrd 넘긴다, 간단한거라 오브젝트로 해서 옮길필요는없다
		
		if (user != null) {
			HttpSession session = request.getSession();  //이미 만들어져있는 세션에 접근 세션이 만들어지는타이밍은 처음서버뜨면
			session.setAttribute("principal", user);   // jseesion아이디로 principal 자기꺼확인
			
			if(request.getParameter("remember") != null) {
			
				// key => Set-Cookie
				// value => remember=ssar
				Cookie cookie = new Cookie("remember", user.getUsername());
				response.addCookie(cookie);
				
				//response.setHeader("Set-Cookie", "remember=ssar");
				
			} else {
				Cookie cookie = new Cookie("remember", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				
			}
			
			Script.href("로그인성공", "/blog/index.jsp", response);
			
		}else {
			Script.back("로그인 실패", response);
		}
	
	}
}
