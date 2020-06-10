<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "include/nav.jsp" %>    <!-- 같은 위치에 있으니까 -->

<div class = "container">

	<div class="col-md-12 m-2">
	  <form class="form-inline justify-content-end" action="/blog/board">
	  	<input type ="hidden" name="cmd" value="search" />
	  	<input type="hidden" name="page" value="0" />
	    <input type="text" class="form-control mr-sm-2" name="keyword" placeholder="Search">
	    <button class="btn btn-primary" type="submit">검색</button>
	  </form>
	
	</div>
</div>


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
	<c:choose> 
		 
	  <c:when test="${empty param.keyword}">	  		
	  		<c:set var ="pageNext" value ="/blog/board?cmd=home&page=${param.page+1}" /> <%-- 닫아줘야함 --%> 			  			  
	  </c:when>
	  
	  <c:otherwise>
	  		<c:set var = "pageNext" value ="/blog/board?cmd=home&page=${param.page+1}&keyword=${param.keyword }" />
	  </c:otherwise>	
	
	</c:choose>
	
	
	
	<c:choose> 
		 
	  <c:when test="${empty param.keyword}">	  		
	  		<c:set var ="pagePre" value ="/blog/board?cmd=home&page=${param.page-1}" /> <%-- 닫아줘야함 --%> 			  			  
	  </c:when>
	  
	  <c:otherwise>
	  		<c:set var = "pagePre" value ="/blog/board?cmd=home&page=${param.page-1}&keyword=${param.keyword }" />
	  </c:otherwise>	
	
	</c:choose>
	
	
	
	
	
	<ul class="pagination justify-content-center" >
	
	<c:choose> 
	 
	  <c:when test="${param.page == 0}">
	  	<li class="page-item disabled"><a class="page-link" href="${pageScope.pagePre}">Previous</a></li>	  			  
	  </c:when>
	  
	  <c:otherwise>
	  	<li class="page-item"><a class="page-link" href="${pageScope.pagePre}">Previous</a></li>
	  
	  </c:otherwise>	
	</c:choose>
	
	<c:choose>
		<c:when test="${lastPage == param.page}">
			<li class="page-item disabled"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
		</c:when>
	  	<c:otherwise>
	  		<li class="page-item"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
	  	</c:otherwise>	
	</c:choose>
	
	</ul>
	
  <!--  123 1  456 2 789 3   10 11 12 4 -->

	
<!--  표현식 문법은 제대로 했는가 if문은 제대로 썼을까? -->

<script src="/blog/js/dontnew.js"></script>
<%@ include file = "include/footer.jsp" %>