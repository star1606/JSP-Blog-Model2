function replyWrite(boardId, userId){
			var data = {
					boardId: boardId,
					userId: userId,
					content: $("#reply__write__form").val()
					};

					$.ajax({
						type: "post",
						url: "/blog/reply?cmd=writeProc",
						data: JSON.stringify(data),
						contentType: "application/json; charset=utf-8",
						dataType: "text"
					}).done(function(result){
						if(result == -1 || result == 0){
							alert("댓글 작성 실패");
							
							
							
						} else {
							alert("댓글 작성 성공");
							$("#reply__list").empty();
							cosole.log(result);
							renderReplyList(result);
							#("#reply__write__form").val("");
							
						}
						
					
						
						// 정상 응답
						// 1. reply__list를 찾아서 내부를 비우기
						// 2. ajax 재호출 findAll()
						// 3. replt__list를 찾아서 내부에 채워주기 ( 다날리고 다시 select해서 다시 넣을것)
						
					}).fail(function(error){
						alert("댓글 작성 실패");
					});

function renderReplyList(replyDtos){
	for(var replyDto of replyDtos){
		$("#reply__list").append(makeReplyItem(replyDto));		
	}
	
}
					

function makeReplyItem(replyDto){
	var replyItem = `<li class="media">`;
	if(replyDto.userProfile == null){
		replyItem += `<img src="/blog/image/userProfile.png" class="img-circle">`;	
	}else{
		replyItem += `<img src="${replyDto.userProfile}" class="">`;
	}

	replyItem += `<div class="media-body">`;
	replyItem += `<strong class="text-primary">${replyDto.username}</strong>`;
	replyItem += `<p>${replyDto.reply.content}</p>`;
	replyItem += `</div>`;
	replyItem += `</li>`;
	return replyItem;


				
}