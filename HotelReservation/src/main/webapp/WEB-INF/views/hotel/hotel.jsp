<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hotel</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<!-- 검색바 -->
	<form action="hotel" method="get">
		<input type="text" name="keyword">
	</form>
	
	<!-- 호텔들 목록 -->
	<c:out value="${hotelMainFormDtoList}"/>
	
	<c:forEach var="hotelMainFormDto" items="${hotelMainFormDtoList}">    
		<c:out value="${hotelMainFormDto.name}" />
		<c:out value="${hotelMainFormDto.phone}" />
		<c:out value="${hotelMainFormDto.address}" />
		<c:out value="${hotelMainFormDto.description}" />
		<c:out value="${hotelMainFormDto.img}" />
		<c:out value="${hotelMainFormDto.avg}" />
			<form action="hotelDetail" method="get">
				<button>확인용</button>
		
			</form>
	</c:forEach>
	<form action="hotelDetail" method="get">
				<button>hotelDetail 확인용</button>
		
	</form>
	<form action="roomReservation" method="get">
				<button>roomReservation 확인용</button>
		
	</form>
	
</body>
</html>