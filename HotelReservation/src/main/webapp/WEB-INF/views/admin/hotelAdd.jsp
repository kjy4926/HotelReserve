<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>호텔 추가</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style type="text/css">
		.img-field{
			width: 200px;
			height: 200px;
			text-align: left;
		}
		.btn-field{float: right;}
	</style>
</head>
	<body>
		<c:import url="${pageContext.request.contextPath}/nav"></c:import>
		
		<form class="container" action="/admin/hotel/new" method="post" enctype="multipart/form-data">
		  <fieldset>
		    <legend>호텔 등록</legend>
		    <div class="form-group">
		      <label for="name" class="form-label mt-4">호텔명</label>
		      <input type="text" class="form-control" id="name" name="name" placeholder="상호명을 입력하세요">
		    </div>
		    <div class="form-group">
		      <label for="address" class="form-label mt-4">주소</label>
		      <div class="input-group">
				<input type="text" class="form-control" id="address" name="address" placeholder="검색 버튼을 눌러 주소를 입력하세요" value="" readonly>
				<input type="button" class="btn btn-outline-dark" value="검색" onclick="searchAddress()">
		    </div>
		    	<input type="text" class="form-control" id="addressDetail" name="addressDetail" placeholder="상세 주소" value="">
		    </div>
		    <div class="form-group">
		      <label for="phone" class="form-label mt-4">연락처</label>
		      <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처를 입력하세요">
		    </div>
		    <div class="form-group">
		      <label for="description" class="form-label mt-4">소개</label>
		      <input type="text" class="form-control" id="description" name="description" placeholder="맛집에 대한 정보를 입력하세요">
		    </div>
		    <div class="form-group">
		      <label for="img" class="form-label mt-4">호텔 사진</label>
		      <input type="file" class="form-control" name="uploadFile" accept="image/*" onchange="setHotelImg(this)"/>
		    </div>
		    	<label for="preview" class="form-label mt-4">이미지 미리보기</label>
			<div class="img-field">
	        	<img id="img" name="img" src="">
			</div>
				<div class="btn-field">
		        	<button type="submit" class="btn btn-outline-secondary">호텔등록</button>
		        	<a class="btn btn-outline-secondary" href="location.href='${pageContext.request.contextPath}/admin'">취소하기</a>
		        </div>
			</fieldset>
		</form>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	</body>
</html>