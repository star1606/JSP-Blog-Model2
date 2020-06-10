<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
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
	
	<h1> 만들어진 변수 확인</h1>
	<h2><c:out value="${pageScope.pageNext}" /></h2>  <%-- 변수확인하려면 c: out 으로 확인하고 문법 지킬것 --%>
	
	
</body>
</html>