<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>리뷰 목록</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.list-field{width: 90%; margin: auto;}
		.del-btn-field{text-align: center;}
		td{vertical-align: middle;}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/delete.js"></script>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div class="list-field">
		<ul class="nav nav-tabs" role="tablist">
		  <li class="nav-item" role="presentation">
		    <a class="nav-link active" data-bs-toggle="tab" href="#hotel" aria-selected="true" role="tab">호텔 리뷰</a>
		  </li>
		  <li class="nav-item" role="presentation">
		    <a class="nav-link" data-bs-toggle="tab" href="#restaurant" aria-selected="false" role="tab" tabindex="-1">맛집 리뷰</a>
		  </li>
		</ul>
		<div id="myTabContent" class="tab-content">
		  <div class="tab-pane fade active show" id="hotel" role="tabpanel">
				<table class="table">
		        	<thead>
						<tr>
							<th style="width: 20%">호텔명</th>
							<th style="width: 10%">별점</th>
							<th style="width: 40%">내용</th>
							<th style="width: 20%">작성일</th>
							<th style="width: 10%"></th>
						<tr>
					</thead>
					<tbody>
					<c:forEach var="hotelScore" items="${hotelScoreList}" varStatus="status">
							<tr>
								<td>${hotelScore.hotelName}</td>
								<td>${hotelScore.scoreString}</td>
								<td>${hotelScore.desc}</td>
								<td>${hotelScore.day}</td>
								<td class="del-btn-field"><button class="btn btn-secondary btn-sm" type="button" 
									onclick="reviewDelete(1, ${hotelScore.seq})">리뷰삭제</button></td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
		  </div>
		  <div class="tab-pane fade" id="restaurant" role="tabpanel">
		        <table class="table">
		        	<thead>
						<tr>
							<th style="width: 20%">점포명</th>
							<th style="width: 10%">별점</th>
							<th style="width: 40%">내용</th>
							<th style="width: 20%">작성일</th>
							<th style="width: 10%"></th>
						<tr>
					</thead>
					<tbody>
					<c:forEach var="restaurantScore" items="${restaurantScoreList}" varStatus="status">
							<tr>
								<td>${restaurantScore.restaurantName}</td>
								<td>${restaurantScore.scoreString}</td>
								<td>${restaurantScore.desc}</td>
								<td>${restaurantScore.day}</td>
								<td class="del-btn-field"><button class="btn btn-secondary btn-sm" type="button" 
									onclick="reviewDelete(2, ${restaurantScore.seq})">리뷰삭제</button></td>
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