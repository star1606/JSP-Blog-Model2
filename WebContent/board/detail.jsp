
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>

<%-- 
<%
	DetailResponseDto dto=
	(DetailResponseDto)request.getAttribute("dto");
%>
--%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>



<div class="container">
	<!-- get 하면 body에 데이터 못들고감 -->
	<a class = "btn btn-secondary" href ="javascript:history.back();">뒤로가기</a>
	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
	
	<c:if test ="${sessionScope.principal.id == dto.board.userId }">
		<%-- 로그인한사람하고 board id로 직접 쓴사람이 수정할 수 있게 설정--%>
		<a href="/blog/board?cmd=update&id=${dto.board.id}" class="btn btn-warning">수정</a>
		
		<button class="btn btn-danger" onclick="deleteById(${dto.board.id} )">삭제</button>
		
	</c:if>
	<br />
	<br />
	<h6>
		작성자 : <i>${dto.username}</i>
	</h6>
	<br />
	<h3>
		<b>${dto.board.title}</b>
	</h3>

	<div class="form-group">
		<div class="container p-3 my-3 border">${dto.board.content}</div>


	</div>

	<!-- </div> 네임이 없으면 전송을 못한다 .text() , val, html -->


</div>

<script>
	function deleteById(boardId){

		$.ajax({
					type: "POST",
					url: "/blog/board?cmd=delete&id=" + boardId,
					dataType: "text"
				}).done(function(result){
					console.log(result);
					if(result == 1){
							alert("삭제 성공");
							loaction.herf="/blog/index.jsp";
						}else{
								alert("삭제 실패");
						}
				}).fail(function(error){
						console.log(error);
						console.log(error.responseText);
						console.log(error.status);
						alert("서버 오류");

					});

		

		}

</script>







<%@ include file="../include/footer.jsp"%>