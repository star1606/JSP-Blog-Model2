<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../include/nav.jsp" %> <!--  상대경로.. -->
<% 
	/*
	String remember = (String) request.getAttribute("remember");
	if(remember ==null){
		remember = "";
	}
	*/
%>
<div class = "container">
	
		<form action="/blog/user?cmd=LoginProc" method="POST" class="was-validated">
  <div class="form-group">
    <label for="username">Username:</label>
    <input type="text" value = "${cookie.remember.value}" class="form-control" id="username" placeholder="Enter username" name="username" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  
  
 

  <div class="form-group form-check">
    <label class="form-check-label">
      <input class="form-check-input" type="checkbox" name="remember" value ="${board.userId}"> 아이디 기억하기

    </label>
  </div>
  <button type="submit" class="btn btn-primary">로그인</button>
</form>
</div>

<%
	String remember ="remember";
	
	String cookie = request.getHeader("Cookie");
if(cookie != null){
	Cookie cookies[] = request.getCookies();
	
// 	<c:if test="${empty sessionScope.principal }">
// 	<c:redirect url = "/index.jsp"></c:redirect>
// </c:if>	
	
}

%>
	
<!--  체크박스에 체크를 누르면 쿠키가 저장되고  -->
<!--   -->

<%@ include file = "../include/footer.jsp" %>