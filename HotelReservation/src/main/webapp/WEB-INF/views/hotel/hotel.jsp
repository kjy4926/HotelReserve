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
<script type="text/javascript">

</script>
<body onload="setSearchType()">
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<!-- 검색바 -->

	
	<div class="form-group" style="width:100%; margin-top:5%; display: inline-flex;">
		<div>
			<a href="hotel?type=${type}&keyword=${keyword}&sortAvg=1"><button class="btn btn-secondary my-2 my-sm-0">평점순으로 정렬</button></a>
		</div>
		<div style="width: 30%">
			<form action="hotel" method="get">
				<div style="display: inline-flex;">
					<div style="width: 80%">
						<select class="form-select" name="type">
						       <option value="1" id="op1">호텔명으로 검색</option>
						       <option value="2" id="op2">지역명으로 검색</option>
						</select>
					</div>
					<div class="input-group">
						<input type="hidden" name="type" value="0"/>
						<input class="form-control me-sm-2" type="text" name="keyword" placeholder="주소 또는 호텔명으로 검색">
						<button class="btn btn-secondary my-2 my-sm-0" type="submit">검색</button>
					</div>
					<br>
				</div>
			</form>
		</div>
	</div>
	
	<div class="container-fluid" style="text-align: center; margin-top: 5%">
		<c:forEach var="hotelMainFormDto" items="${hotelMainFormDtoList}" > 
			<div class="card mb-3" style="width: 18%; display: inline-flex;"
				onclick="location.href='${pageContext.request.contextPath}/hotel/detail/${hotelMainFormDto.seq}'">
				<div style="height: 40%">
					<h3 class="card-header"><c:out value="${hotelMainFormDto.name}" /></h3>
				</div>
				<div class="card-body">
					<h5 class="card-title">
						<div class="card-body">
							<p class="card-text"><c:out value="${hotelMainFormDto.description}"/></p>
						</div>
					</h5>
					<h6 class="card-subtitle text-muted">부가설명 : (없을 시 빼기) </h6>
				</div>
				<svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
				<image href="${pageContext.request.contextPath}/resources/img/hotel/${hotelMainFormDto.img}" width="100%" height="100%"/>
				<text x="50%" y="50%" fill="#dee2e6" dy=".3em"></text>
				</svg>
				
				<ul class="list-group list-group-flush">
					<li class="list-group-item">호텔 전화번호 : <c:out value="${hotelMainFormDto.phone}" /></li>
					<li class="list-group-item">호텔 주소 : <c:out value="${hotelMainFormDto.address}" /></li>
					<li class="list-group-item">호텔 평점 : <c:out value="${hotelMainFormDto.avg}"/>(<c:out value="${hotelMainFormDto.scoreString}"/>)</li>
					
				</ul>
				<div class="card-body">
					<a href="/hotel/detail/${hotelMainFormDto.seq}" class="card-link">예약하기</a>
					<a href="/hotel/detail/${hotelMainFormDto.seq}" class="card-link">지금 바로!</a>
				</div>
			</div>   
		</c:forEach>
	</div>
	
		<ul class="pagination pagination-lg container-fluid justify-content-center">


			<c:if test="${firstPage == -1 }">
				<li class="page-item disabled">
			    	<a class="page-link" href="#">&laquo;</a>
				</li>
			</c:if>
			<c:if test="${firstPage != -1 }">
				<li class="page-item">
			    	<a class="page-link" href="hotel?type=${type}&keyword=${keyword}&sortAvg=${sortAvg}&page=${firstPage}">&laquo;</a>
				</li>
			</c:if>
			
			<c:if test="${prevPage == -1 }">
				<li class="page-item disabled">
			    	<a class="page-link" href="#">&lt;</a>
				</li>
			</c:if>
			<c:if test="${prevPage != -1 }">
				<li class="page-item">
			    	<a class="page-link" href="hotel?type=${type}&keyword=${keyword}&sortAvg=${sortAvg}&page=${prevPage}">&lt;</a>
				</li>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${lastPage}">
				<c:if test="${page == i-1}">
					<li class="page-item disabled">
						<a class="page-link" href="hotel?type=${type}&keyword=${keyword}&sortAvg=${sortAvg}&page=${i}"><c:out value="${i}"/></a>
					</li>
				</c:if>
				<c:if test="${page != i-1}">
					<li class="page-item">
						<a class="page-link" href="hotel?type=${type}&keyword=${keyword}&sortAvg=${sortAvg}&page=${i}"><c:out value="${i}"/></a>
					</li>
				</c:if>
			</c:forEach>
			
			
			<c:if test="${nextPage == -1}">
				<li class="page-item disabled">
					<a class="page-link" href="#">&gt;</a>
				</li>
			</c:if>
			<c:if test="${nextPage != -1}">
				<li class="page-item">
					<a class="page-link" href="hotel?type=${type}&keyword=${keyword}&sortAvg=${sortAvg}&page=${nextPage}">&gt;</a>
				</li>
			</c:if>
			<c:if test="${nextPage == -1}">
				<li class="page-item disabled">
					<a class="page-link" href="#">&raquo;</a>
				</li>
			</c:if>
			<c:if test="${nextPage != -1}">
				<li class="page-item">
					<a class="page-link" href="hotel?type=${type}&keyword=${keyword}&sortAvg=${sortAvg}&page=${maxPage}">&raquo;</a>
				</li>
			</c:if>
		</ul>
	<script type="text/javascript">
		function setSearchType(type){
			option = document.getElementById(`op${type}`).selected = 'selected'
		}
	</script>
</body>
</html>