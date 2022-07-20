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
		<input type="submit" value="검색">
	</form>
	

	<form action="hotelDetail" method="get">
		<c:forEach var="hotelMainFormDto" items="${hotelMainFormDtoList}" >    
			<h5>호텔</h5>
			호텔 이름 : <c:out value="${hotelMainFormDto.name}" /><p/>
			호텔 전화번호 : <c:out value="${hotelMainFormDto.phone}" /><p/>
			호텔 주소 : <c:out value="${hotelMainFormDto.address}" /><p/>
			호텔 설명 : <c:out value="${hotelMainFormDto.description}"/><p/>
			호텔 이미지 : <c:out value="${hotelMainFormDto.img}" /><p/>
			호텔 평점 : <c:out value="${hotelMainFormDto.avg}" /><p/>
			<p/>
		</c:forEach>
	</form>
	
	
	<c:forEach var="page" begin="1" end="${maxPage }">
		<form action="hotel?page=${page }&keyword=${keyword}" method="get">
			<button type="submit">${page}</button>
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