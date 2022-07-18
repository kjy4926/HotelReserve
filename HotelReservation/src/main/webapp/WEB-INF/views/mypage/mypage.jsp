<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이 페이지</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.mypage{width: 40%; margin: auto;}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import><br>

	<fieldset class="mypage">
		<legend>마이 페이지</legend><br><br>
		<table class="table table-hover">
		  <thead>
		    <tr class="table-light">
		      <th scope="col"><sec:authentication property="principal.username"></sec:authentication>님 어서오세요.</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr onclick="location.href='${pageContext.request.contextPath}/'">
		      <th scope="row">예약 현황</th>
		    </tr>
		    <tr>
		      <th scope="row">이용 내역</th>
		    </tr>
		    <tr>
		      <th scope="row">리뷰 관리</th>
		    </tr>
		    <tr>
		      <th scope="row">문의 관리</th>
		    </tr>
		    <tr>
		      <th scope="row">나의 맛집</th>
		    </tr>
		    <tr>
		      <th scope="row">계정 관리</th>
		    </tr>
		  </tbody>
		</table>
	</fieldset>
	
	<fieldset class="mypage">
		<div class="accordion" id="accordionExample">
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingOne">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				예약 현황
		      </button>
		    </h2>
		    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="">
		      <div class="accordion-body">
				<table class="table">
		        	<thead>
						<tr>
							<th>호텔명</th>
							<th>예약자</th>
							<th>예약일</th>
							<th>종료일</th>
						<tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${accounts}" varStatus="a">
							<tr>
								<td>호텔</td>
								<td>${item.userId}</td>
								<td>today</td>
								<td>end</td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
				<div><a href="${pageContext.request.contextPath}/" style="float:right;">상세 보기</a></div>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingTwo">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				이용 내역
		      </button>
		    </h2>
		    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample" style="">
		      <div class="accordion-body">
		        <strong>Two</strong>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingThree">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
				리뷰 관리
		      </button>
		    </h2>
		    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample" style="">
		      <div class="accordion-body">
		        <strong>Three</strong>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingFour">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
				문의 관리
		      </button>
		    </h2>
		    <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample" style="">
		      <div class="accordion-body">
		        <strong>Four</strong>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingFive">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
				나의 맛집
		      </button>
		    </h2>
		    <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample" style="">
		      <div class="accordion-body">
		        <strong>Five</strong>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingSix">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
				계정 관리
		      </button>
		    </h2>
		    <div id="collapseSix" class="accordion-collapse collapse" aria-labelledby="headingSix" data-bs-parent="#accordionExample" style="">
		      <div class="accordion-body">
		        <table class="table table-hover">
					<tr>
						<td onclick="location.href='${pageContext.request.contextPath}/'">개인정보 변경</td>
					</tr>
					<tr>
						<td onclick="location.href='${pageContext.request.contextPath}/login'">비밀번호 변경</td>
					</tr>
					<tr>
						<td onclick="location.href='${pageContext.request.contextPath}/signup'">회원 탈퇴</td>
					</tr>
				</table>
		      </div>
		    </div>
		  </div>
		</div>
	</fieldset>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>