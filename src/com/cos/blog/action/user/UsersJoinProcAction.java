	package com.cos.blog.action.user;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;


public class UsersJoinProcAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//0. 유효성 검사
		if
		(
				request.getParameter("username").equals("")||
				request.getParameter("username") == null ||
				request.getParameter("password").equals("")||
				request.getParameter("password") == null ||
				request.getParameter("email").equals("")||
				request.getParameter("email") == null ||
				request.getParameter("address").equals("")||
				request.getParameter("address") == null
				//이메일도 유효성검사해야한다 
				
		) 
		{
			return;	 // void 지면 return하면 실행안되고 튕겨나감, 로그남겨서 공격받은거
					 //저장한다
		}
			
		
		//1. 파라미터 받기. ( 폼태그데이터-> x-www-form-urlencoded 라는 MIME타입 key= value)
		//2. 클라이언트가 쓴 내용들을 get으로 받아오는것
		String username = request.getParameter("username"); // join화면에 ,required론 부족하다 왜냐하면 포스트맨이 공격할 수 있다 null값을 
		String rawpassword = request.getParameter("password");
		String password = SHA256.encodeSha256(rawpassword);
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String userRole = RoleType.USER.toString();
		//enum으로해야한다 마음대로 수정할 수 있으니까 toString으로 문자열화
		
		//2. User 오브젝트 변환
		Users user = Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.address(address)
				.userRole(userRole)
				.build();
		
		//3. DB연결 -UsersRepository의 save() 호출
		UsersRepository usersRepository =
				UsersRepository.getInstance();
		int result = usersRepository.save(user);
		
		//4. index.jsp 페이지로 이동
		if(result == 1) {
			
			// response.sendRedirect("/blog/user?cmd=login"); 
			//이건 잘 안씀 스크립트 못넣으니깐 회원가입완료 창띄울수도없다
			
			
			
			
			Script.href("회원가입에 성공하였습니다.", "/blog/user?cmd=login", response);
		}else {
			Script.back("회원가입에 실패하였습니다.", response);
			
			
			
		}
		
		
	}
	 
	
	
	
}
