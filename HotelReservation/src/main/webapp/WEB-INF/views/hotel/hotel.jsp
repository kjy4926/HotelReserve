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
<style>
	div {
		justify-content: center;
	}
</style>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<!-- 검색바 -->
	<form action="hotel" method="get" class="d-flex">
        <input class="form-control me-sm-2" type="text" name="keyword" placeholder="주소 또는 호텔명으로 검색">
        <button class="btn btn-secondary my-2 my-sm-0" type="submit">검색</button>
    </form>
	
	<form action="hotelDetail" method="get">
		<c:forEach var="hotelMainFormDto" items="${hotelMainFormDtoList}" > 
			<div class="card mb-3" style="float: left; width: 19%;">
				<h3 class="card-header"><c:out value="${hotelMainFormDto.name}" /></h3>
				<div class="card-body">
					<h5 class="card-title">Special title treatment</h5>
					<h6 class="card-subtitle text-muted">Support card subtitle</h6>
				</div>
				<svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
				<rect width="100%" height="100%" fill="#868e96"></rect>
				<text x="50%" y="50%" fill="#dee2e6" dy=".3em">호텔 이미지 : <img src="<c:out value="${hotelMainFormDto.img}"/>"></text>
				</svg>
				<div class="card-body">
					<p class="card-text">호텔 설명 : <c:out value="${hotelMainFormDto.description}"/></p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">호텔 전화번호 : <c:out value="${hotelMainFormDto.phone}" /></li>
					<li class="list-group-item">호텔 주소 : <c:out value="${hotelMainFormDto.address}" /></li>
					<li class="list-group-item">호텔 평점 : <c:out value="${hotelMainFormDto.avg}" /></li>
				</ul>
				<div class="card-body">
					<a href="#" class="card-link">Card link</a>
					<a href="#" class="card-link">Another link</a>
				</div>
			</div>   
		</c:forEach>
	</form>
	
	<div style="float:left; width: 20%;">
		<ul class="pagination pagination-lg">
			<li class="page-item disabled">
			    <a class="page-link" href="#">&laquo;</a>
			</li>
			<li class="page-item active">
					<a class="page-link" href="#">&laquo;</a>
			</li>
			<c:forEach var="page" begin="0" end="${maxPage}">
				<li class="page-item">
					<a class="page-link" href="hotel?keyword=${keyword}&page=${page}"><c:out value="${page+1}"/></a>
				</li>
			</c:forEach>
			<li class="page-item">
				<a class="page-link" href="#">&raquo;</a>
			</li>
		</ul>
	</div>
	
	<div style="float:left; width: 20%;">
		<form action="hotelDetail" method="get">
					<button>hotelDetail 확인용</button>
			
		</form>
		
		<form action="roomReservation" method="get">
					<button>roomReservation 확인용</button>
			
		</form>
	</div>
</body>
</html>