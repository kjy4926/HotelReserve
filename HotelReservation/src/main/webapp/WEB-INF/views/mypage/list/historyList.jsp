<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약 내역</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.list-field{width: 90%; margin: auto;}
		.review-btn-field{text-align: center;}
		.cancle-btn-field{text-align: center;}
		td{vertical-align: middle;}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/delete.js"></script>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div class="list-field">
		<ul class="nav nav-tabs" role="tablist">
		  <li class="nav-item" role="presentation">
		    <a class="nav-link active" data-bs-toggle="tab" href="#reserved" aria-selected="true" role="tab">예약 현황</a>
		  </li>
		  <li class="nav-item" role="presentation">
		    <a class="nav-link" data-bs-toggle="tab" href="#progressed" aria-selected="false" role="tab" tabindex="-1">이용 내역</a>
		  </li>
		</ul>
		<div id="myTabContent" class="tab-content">
		  <div class="tab-pane fade active show" id="reserved" role="tabpanel">
				<table class="table">
		        	<thead>
						<tr>
							<th style="width: 8%">예약번호</th>
							<th style="width: 15%">호텔명</th>
							<th style="width: 26%">방이름</th>
							<th style="width: 10%">예약자</th>
							<th style="width: 5%">인원</th>
							<th style="width: 10%">예약일</th>
							<th style="width: 10%">종료일</th>
							<th style="width: 8%">가격</th>
							<th class="cancle-btn-field" style="width: 8%"></th>
						<tr>
					</thead>
					<tbody>
					<c:forEach var="rbooking" items="${reserveBookingList}" varStatus="status">
							<tr>
								<td>${rbooking.seq}</td>
								<td>${rbooking.hotel}</td>
								<td>${rbooking.room}</td>
								<td>${rbooking.reserver}</td>
								<td>${rbooking.people}인</td>
								<td>${rbooking.reserveDate}</td>
								<td>${rbooking.reserveEndDate}</td>
								<td><fmt:formatNumber value="${rbooking.price}" pattern="#,###원"></fmt:formatNumber></td>
								<td class="cancle-btn-field"><button class="btn btn-secondary btn-sm" type="button" onclick="reserveDelete('${rbooking.reserveDate}', ${rbooking.seq})">예약취소</button></td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
		  </div>
		  <div class="tab-pane fade" id="progressed" role="tabpanel">
		        <table class="table">
		        	<thead>
						<tr>
							<th style="width: 8%">예약번호</th>
							<th style="width: 15%">호텔명</th>
							<th style="width: 26%">방이름</th>
							<th style="width: 10%">예약자</th>
							<th style="width: 5%">인원</th>
							<th style="width: 10%">예약일</th>
							<th style="width: 10%">종료일</th>
							<th style="width: 8%">가격</th>
							<th class="review-btn-field" style="width: 8%"></th>
						<tr>
					</thead>
					<tbody>
					<c:forEach var="pbooking" items="${progressedBookingList}" varStatus="status">
							<tr>
								<td>${pbooking.seq}</td>
								<td>${pbooking.hotel}</td>
								<td>${pbooking.room}</td>
								<td>${pbooking.reserver}</td>
								<td>${pbooking.people}인</td>
								<td>${pbooking.reserveDate}</td>
								<td>${pbooking.reserveEndDate}</td>
								<td><fmt:formatNumber value="${pbooking.price}" pattern="#,###원"></fmt:formatNumber></td>
								<td class="cancle-btn-field"><button class="btn btn-secondary btn-sm" type="button" 
									onclick="location.href='${pageContext.request.contextPath}/mypage/review/hotel/write/${pbooking.hotelSeq}'">리뷰작성</button></td>
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