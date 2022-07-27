<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.table{table-layout: fixed;}
		.title{vertical-align: middle;}
		.button-area{float: right;}
		.img-field{display: flex; align-items: center; justify-content: center;}
		.option-title{border-bottom: none;}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div class="container">
		<fieldset class="form-group">
			<legend>예약</legend>
			<form method="post">
			    <table class="table">
				  <thead>
				    <tr>
				      <th>Room</th>
				      <th>Info</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th rowspan="4">
				      	<div style="imgfield">
							<img src="${pageContext.request.contextPath}/resources/img/room/66.jpg" alt="review-target-image" id="img" style="width: 100%">
				      	</div>
				      </th>
				      <th class="title">방이름 출력</th>
				    </tr>
				    <tr>
				      <th class="title">수용인원 : n</th>
				    </tr>
				    <tr>
				      <th class="title">1박당 가격 : n</th>
				    </tr>
				    <tr>
				      <th class="title">방 설명 출력 : ex) 침대 1개</th>
				    </tr>
				    <tr>
				    	<th rowspan="8">
				    		<p>
				    			유의사항
				    		</p>
				    	</th>
				    	<th class="title option-title">예약일자 선택</th>
				    </tr>
				    <tr>
				    	<th class="input-group">
				    		<input type="date" class="form-control" min="2022-08-01">
				    		<input type="date" class="form-control" min="2022-08-01">
				    	</th>
				    </tr>
				    <tr>
				    	<th class="title option-title">인원수</th>
				    </tr>
				    <tr>
				    	<th>
				  			<select class="form-select form-control" id="score" name="score">
				  				<!-- 이부분 forEach 사용하기 -->
						        <option value="1">1</option>
						        <option value="2">2</option>
						        <option value="3">3</option>
						        <option value="4">4</option>
						    </select>
				    	</th>
				    </tr>
				    <tr>
				    	<th class="title option-title">가격</th>
				    </tr>
				    <tr>
				    	<th style="text-align: right;">3000000원</th>
				    </tr>
				  </tbody>
				</table>
				<section class="button-area">
				  	<button type="submit" class="btn btn-outline-secondary">예약 등록</button>
				  	<button type="button" class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/mypage'">예약 취소</button>
			  	</section>
			</form>
		</fieldset>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>