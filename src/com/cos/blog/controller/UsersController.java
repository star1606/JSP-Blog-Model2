package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;
import com.cos.blog.action.user.UsersLoginProcAction;
import com.cos.blog.action.user.UsersLogoutAction;
import com.cos.blog.action.user.UsersUsernameCheckAction;

// http://localhost:8000/blog/user

@WebServlet("/user")
public class UsersController extends HttpServlet {
	private final static String TAG = "UsersController: ";
	private static final long serialVersionUID = 1L;

	public UsersController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// select doGet
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DML은 dopost
		doProcess(request, response);
	}

	// doProcess로 메소드 몰아주기

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DML은 dopost
		// http://localhost:8000/blog/user?cmd=join

		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "doProcess: " + cmd);
		Action action = router(cmd);
		action.execute(request, response);

	}

	public Action router(String cmd) {
		if (cmd.equals("join")) {
			// 회원가입 페이지로 이동
			return new UsersJoinAction(); // 실행된게 아니라 new해서 메모리에 뛰우는 상태임
		} else if (cmd.equals("joinProc")) {
			// 회원가입을 진행 한 후 -> index.jsp로 이동
			return new UsersJoinProcAction();

		} else if (cmd.equals("update")) {
			// 회원수정 페이지로 이동. (세션에 User 오브젝트를 가지고 있을 예정)
			// 유저에 대한 정보를 세션이 들고 있을거다
			// 쿠키는 클라이언트가 가지고 있는 정보
			// 세션은 서버가 저장하고 있는정보

		} else if (cmd.equals("updateProc")) {
			// 회원 수정을 진행 한 후 -> index.jsp로 이동

		} else if (cmd.equals("delete")) {

			// 회원 삭제를 진행한 후 -> 로그아웃을 하고 -> index.jsp로 이동

		} else if (cmd.equals("login")) {
			return new UsersLoginAction(); // 주소노출안되고 포워딩된다
			// 회원 로그인 페이지로 이동
		} else if (cmd.equals("LoginProc")) {
			// 회원 로그인을 수행한후 -> 세션에 등록하고 -> index.jsp로 이동
			return new UsersLoginProcAction();
		} else if (cmd.equals("logout")) {
		
			return new UsersLogoutAction();
		} else if (cmd.equals("usernameCheck")) {
		
			return new UsersUsernameCheckAction();
		}
		return null;
	}

}
