<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>HotelDetail</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<style type="text/css">
		.img-field{display: flex; align-items: center; justify-content: center; height: 100%; width: 100%}
		img{width: 100%;}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<div style="display: inline-flex;">
		<div class="card mb-3" style="width: 35%; max-height: 400px; margin-left: 3.5%;">
			<div>
				<h5 class="card-header"><c:out value="${hotelDetailFormDto.name}"/></h5>
			</div>
			<div style="display: flex; align-items: center; justify-content: center;">
				<img src="${pageContext.request.contextPath}/resources/img/hotel/${hotelDetailFormDto.img}" width="100%" height="100%">
			</div>
			<div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">평점</li>
					<li class="list-group-item"><c:out value="${hotelDetailFormDto.avg}"/>(<c:out value="${hotelDetailFormDto.scoreString}"></c:out>)</li>
				</ul>
			</div>
		</div>

		<div style="width: 100%; position: relative; margin-left: 7%">
			<div style="width : 80%; top: 0px; right: 0;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Type</th>
							<th scope="col" colspan="3">호텔정보</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">호텔명</th>
							<td colspan="3">${hotelDetailFormDto.name}</td>
						</tr>
						<tr>
							<th scope="row">주소</th>
							<td colspan="3">${hotelDetailFormDto.address}</td>
						</tr>
						<tr>
							<th scope="row">연락처</th>
							<td colspan="3">${hotelDetailFormDto.phone}</td>
						</tr>
						<tr>
							<th scope="row">호텔 소개</th>
							<td colspan="3">${hotelDetailFormDto.description}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="margin-top: 15%">
				<c:forEach var="room" items="${hotelDetailFormDto.roomList}">
					<div style="width: 80%; position: obsolute; display: inline-flex; right: 0; bottom: 0px; height: 290px" onclick="location.href='${pageContext.request.contextPath}/hotel/reservation/${room.seq}'">
						<div class="card bg-light mb-3" style="width: 30%;">
							<div class="card-header">호텔 사진</div>
							<div class="card-body">
								<div class="container img-field">
									<img src="${pageContext.request.contextPath}/resources/img/room/${room.img}"/>
								</div>
							</div>
						</div>
						<div class="card" style="width: 70%;">
							<div class="card-body">
								<h4 class="card-title">${room.name}</h4>
								<h6 class="card-subtitle mb-2 text-muted">${room.people}명(인원수)</h6>
								<p class="card-text">${room.desc}</p>
								<a href="#" class="card-link">이 방으로</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div style="width: 90%">
		ddddddddddd
	</div>

		
		
</body>
</html>