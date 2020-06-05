package com.cos.blog.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.board.BoardHomeAction;
import com.cos.blog.action.board.BoardWriteAction;
import com.cos.blog.action.board.BoardWriteProcAction;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;



// http://localhost:8000/blog/board로 오면 이쪽으로 도달한다 !!!!

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private final static String TAG = "BoardController: ";
	private static final long serialVersionUID = 1L;
       
   
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// select doGet
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DML은 dopost
		doProcess(request, response);
	}
	
	//doProcess로 메소드 몰아주기
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DML은 dopost
		// http://localhost:8000/blog/user?cmd=join
		
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "doProcess: " + cmd);
		Action action = router(cmd);
			action.execute(request, response);
		
				
		
	}
	
	public Action router(String cmd) {
		if(cmd.equals("home")) {
			//회원가입 페이지로 이동
			return new BoardHomeAction();  // 홈액션의 목적 보드의 목록을 다 뿌린다.
		
		}else if(cmd.equals("write")) {
			
			return new BoardWriteAction();  // 글쓰기
		
		} else if(cmd.equals("writeProc")) {
			
			return new BoardWriteProcAction();  // 
		
		}
		return null;
	}
	
	

}
