<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>호텔 관리</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		td{vertical-align: middle;}
		img{width: 100%;}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div style="width: 90%; margin: auto;">
		<fieldset>
			<legend>호텔 정보</legend>
			<table class="table">
		    <thead>
				<tr>
					<th style="width: 10%">호텔번호</th>
					<th style="width: 15%">이미지</th>
					<th style="width: 20%">호텔명</th>
					<th style="width: 20%">주소</th>
					<th style="width: 10%">전화번호</th>
					<th style="width: 25%">설명</th>
				<tr>
			</thead>
			<tbody>
				<tr>
					<td>${hotel.seq}</td>
					<td><img src="${pageContext.request.contextPath}/resources/img/hotel/${hotel.img}"></td>
					<td>${hotel.name}</td>
					<td>${hotel.address}</td>
					<td>${hotel.phone}</td>
					<td>${hotel.description}</td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<fieldset>
			<legend>방 목록</legend>
			<table class="table">
			    <thead>
					<tr>
						<th style="width: 10%">방 번호</th>
						<th style="width: 15%">이미지</th>
						<th style="width: 20%">방 이름</th>
						<th style="width: 10%">수용 인원</th>
						<th style="width: 10%">가격</th>
						<th style="width: 25%">설명</th>
						<th style="width: 10%"></th>
					<tr>
				</thead>
				<tbody>
					<c:forEach var="room" items="${roomDtoList}" varStatus="status">
						<tr>
							<td>${room.seq}</td>
							<td><img src="${pageContext.request.contextPath}/resources/img/room/${room.img}"></td>
							<td>${room.name}</td>
							<td>${room.people}</td>
							<td>${room.price}</td>
							<td>${room.description}</td>
							<td>
								<button class="btn btn-secondary btn-sm" type="button" 
									onclick="location.href='${pageContext.request.contextPath}/admin/room/change/${hotel.seq}'">방 수정</button>
								<button class="btn btn-outline-secondary btn-sm" type="button" 
									onclick="location.href='${pageContext.request.contextPath}/admin/room/delete/${hotel.seq}'">방 삭제</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>