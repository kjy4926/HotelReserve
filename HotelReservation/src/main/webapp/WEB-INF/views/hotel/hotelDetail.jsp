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
		<div class="card mb-3" style="width: 35%; height: 500px; margin-left: 3.5%;">
			<div style="height :10%;">
				<h3 class="card-header"><c:out value="${hotelDetailFormDto.name}"/></h3>
			</div>
			<div style="height: 50%;">
				<svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
					<rect width="100%" height="100%" fill="#868e96"></rect>
					<img src="${pageContext.request.contextPath}/resources/img/hotel/${hotelDetailFormDto.img}" width="100%" height="100%">
					<text x="50%" y="50%" fill="#dee2e6" dy=".3em"></text>
				</svg>
			</div>
			<div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">평점</li>
					<li class="list-group-item"><c:out value="${hotelDetailFormDto.avg}"/></li>
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
						<tr class="table-active">
							<th scope="row">호텔명</th>
							<td colspan="3">${hotelDetailFormDto.name}</td>
						</tr>
						<tr>
							<th scope="row">주소</th>
							<td colspan="3">${hotelDetailFormDto.address }</td>
						</tr>
						<tr class="table-primary">
							<th scope="row">연락처</th>
							<td colspan="3">${hotelDetailFormDto.phone}</td>
						</tr>
						<tr class="table-secondary" height="300">
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
									<img src="${pageContext.request.contextPath}/resources/img/room/${room.seq}.jpg"/>
								</div>
							</div>
						</div>
						<div class="card" style="width: 70%;">
							<div class="card-body">
								<h4 class="card-title">${room.name}</h4>
								<h6 class="card-subtitle mb-2 text-muted">${room.people}명(인원수)</h6>
								<p class="card-text">방 설명</p>
								<a href="#" class="card-link">이 방으로</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>
	</div>
	

		
		
</body>
</html>