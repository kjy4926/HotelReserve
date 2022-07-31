<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나의 맛집</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.list-field{width: 90%; margin: auto;}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/delete.js"></script>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div class="list-field">
		<ul class="nav nav-tabs" role="tablist">
		  <li class="nav-item" role="presentation">
		    <a class="nav-link active" data-bs-toggle="tab" href="#hotel" aria-selected="true" role="tab">맛집 목록</a>
		  </li>
		</ul>
		<div id="myTabContent" class="tab-content">
		  <div class="tab-pane fade active show" id="hotel" role="tabpanel">
		        <table class="table">
		        	<thead>
						<tr>
							<th>점포명</th>
							<th>점포 전화번호</th>
							<th>점포 설명</th>
							<th class="review-btn-field"></th>
						<tr>
					</thead>
					<tbody>
					<c:forEach var="stars" items="${starsDtoList}" varStatus="status">
							<tr>
								<td>${stars.restaurantName}</td>
								<td>${stars.restaurantPhone}</td>
								<td>${stars.restaurantDesc}</td>
								<td>
									<button class="btn btn-secondary btn-sm" type="button" 
										onclick="location.href='${pageContext.request.contextPath}/mypage/review/restaurant/write/${stars.restaurantSeq}'">리뷰작성</button>
									<button class="btn btn-outline-secondary btn-sm" type="button" 
										onclick="starsDelete(${stars.seq})">찜 취소</button>
								</td>			
							</tr>
					</c:forEach>
					</tbody>
				</table>
		  </div>
		</div>	
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>