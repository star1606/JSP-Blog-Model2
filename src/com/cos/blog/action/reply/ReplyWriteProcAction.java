package com.cos.blog.action.reply;

import java.io.BufferedReader;


import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

public class ReplyWriteProcAction implements Action {
	
	// request.getParameter 못 받음
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// application /json
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		while((input =br.readLine())!= null) {
			sb.append(input);
			
		}
		System.out.println(sb.toString());
		Gson gson = new Gson();
		Reply reply = gson.fromJson(sb.toString(), Reply.class);
		
		ReplyRepository replyRepository =
				ReplyRepository.getInstance();
		
		int result = replyRepository.save(reply);
		System.out.println(result);
		
		if(result == 1) {
			List<ReplyResponseDto> replyDtos = replyRepository.findAll(reply.getBoardId());
			String replyDtosJson = gson.toJson(replyDtos);
			Script.outJson(replyDtosJson, response);
			
			
			
		} else {
			Script.outJson(result+"", response);
			
		}
		// 이거 설명  reply List로 만들어서 
		// 맨처음 reply boardId를 가져와서 
		// findAll 메소드 실행 select 문을 실행한다
		//댓글이 여러개니까 list로 담는것임
		// 그것을 replyDtos에 담고 / 배열의 내용들을 제이슨으로 바꾸고 
		// JSON의 내용을 스트링으로 받고 그걸 outJSON메소드 실행
		// 스크립트 메소드로인하여 response.getWriter함
		// 객체를 가지고 나옴 이 다음에 추가된 내용을 댓글에 붙이는거고
		// 나중에 ajax로 확인된것과 
		
		// DB에 밀어 넣기만 하면 된다.
		// ReplyRepository에 연결해서 save(reply) 성공하면 1 응답
		// 실패하면 0, -1  result값 응답 
		// script.outText() 응답.
		// detail.jsp에서 받음
		// reply__list를 찾아서 내부를 비우기
	}
}
