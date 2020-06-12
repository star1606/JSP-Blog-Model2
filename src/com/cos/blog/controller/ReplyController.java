package com.cos.blog.controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;

import com.cos.blog.action.reply.ReplyWriteProcAction;



// http://localhost:8000/blog/board로 오면 이쪽으로 도달한다 !!!!

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private final static String TAG = "BoardController: ";
	private static final long serialVersionUID = 1L;
       
   
    public ReplyController() {
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
		if(cmd.equals("writeProc")) {
			//회원가입 페이지로 이동
			return new ReplyWriteProcAction();  // 홈액션의 목적 보드의 목록을 다 뿌린다.
		
		}
		return null;
	}
	
	

}
