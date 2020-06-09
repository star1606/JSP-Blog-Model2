<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "include/nav.jsp" %>    <!-- 같은 위치에 있으니까 -->

<c:forEach var="board" items="${boards}">
	<div class="container">	
		<div class="card m-2" style="width:100%">
  	
<!-- 	  	<div class="card-body"> -->
<!-- 	       <h4 class="card-title">제목이 들어가는 자리</h4> -->
<!-- 	       <p class="card-text">본문 미리보기</p> -->
<!-- 	 	   <a href="#" class="btn btn-primary">상세보기</a> -->
<!-- 	 	</div> -->
<!--     </div> -->

<!-- 	<div class="card m-2" style="width:100%"> -->
<!-- 	 	   <div class="card-body"> -->
<!-- 	 	   <h4 class="card-title">제목이 들어가는 자리</h4> -->
<!-- 	 	   <p class="card-text">본문 미리보기</p> -->
<!-- 	  	   <a href="#" class="btn btn-primary">상세보기</a> -->
<!-- 	    </div> -->
<!-- 	</div> -->
	
<!-- 	<div class="card m-2" style="width:100%"> -->
  
<!-- 	   	<div class="card-body"> -->
<!-- 	       <h4 class="card-title">제목이 들어가는 자리</h4> -->
<!-- 	       <p class="card-text">본문 미리보기</p> -->

 		   <h4 class ="card-title">${board.title}</h4>  <%-- 중요 :el표현식은 변수명을 적으면 getter가 호출된다 --%>
		   <p class = "card-text">${board.content}</p>	<%-- gettitle()함수 호출임 --%>
	 	   <a href="/blog/board?cmd=detail&id=${board.id}" class="btn btn-primary">상세보기</a>
	    </div>
	</div>
	
</c:forEach>
<br/>

	<!--  disabled -->
	<ul class="pagination justify-content-center" >
	
	
	  <li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page-1}">Previous</a></li>	
	  <li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page+1}">Next</a></li>
	</ul>
	

</div>
	


<%@ include file = "include/footer.jsp" %>