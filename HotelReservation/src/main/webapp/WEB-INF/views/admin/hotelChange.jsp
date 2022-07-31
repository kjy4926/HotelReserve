<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>호텔 수정</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style type="text/css">
		.img-field{
			max-width: 200px;
			max-height: 200px;
			text-align: left;
		}
		.del-btn-field{float: left;}
		.btn-field{float: right;}
		img{width: 100%}
	</style>
</head>
	<body>
		<c:import url="${pageContext.request.contextPath}/nav"></c:import>
		
		<form onsubmit="return hotelChangeConfirm()" class="container" action="/admin/hotel/change/${hotel.seq}" method="post" enctype="multipart/form-data">
		  <fieldset>
		    <legend>호텔 수정</legend>
		    <small class="fomr-text text-muted">
		    	수정할 부분만 입력해 주시면 됩니다.<br>
		    	주소, 사진의 경우 입력값이 없을 경우 기존 값으로 유지됩니다.
		    </small>
		    <div class="form-group">
		      <label for="name" class="form-label mt-4">호텔명</label>
		      <input type="text" class="form-control" id="name" name="name" placeholder="상호명을 입력하세요" value="${hotel.name}">
		    </div>
		    <div class="form-group">
		      <label for="address" class="form-label mt-4">주소</label>
		      <div class="input-group">
				<input type="text" class="form-control" id="address" name="address" placeholder="주소 변경 시에만 검색해 주세요" readonly>
				<input type="button" class="btn btn-outline-dark" value="검색" onclick="searchAddress()">
		    </div>
		    	<input type="text" class="form-control" id="addressDetail" name="addressDetail" placeholder="주소 변경 시에만 입력해 주세요." value="">
		    </div>
		    <div class="form-group">
		      <label for="phone" class="form-label mt-4">연락처</label>
		      <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처를 입력하세요" value="${hotel.phone}">
		    </div>
		    <div class="form-group">
		      <label for="description" class="form-label mt-4">소개</label>
		      <input type="text" class="form-control" id="description" name="description" placeholder="호텔에 대한 정보를 입력하세요" value="${hotel.description}">
		    </div>
		    <div class="form-group">
		      <label for="img" class="form-label mt-4">호텔 사진</label><small class="text-muted">(사진 변경 시에만 선택해 주세요.)</small>
		      <input type="file" class="form-control" name="uploadFile" accept="image/*" onchange="setHotelImg(this)"/>
		    </div>
		    	<label for="preview" class="form-label mt-4">이미지 미리보기</label>
			<div class="img-field">
	        	<img id="img" name="img" src="">
			</div>
				<div class="del-btn-field">
					<button type="button" class="btn btn-outline-secondary" onclick="hotelDeleteConfirm(${hotel.seq})">호텔삭제</button>
				</div>
				<div class="btn-field">
		        	<button type="submit" class="btn btn-outline-secondary">수정완료</button>
		        	<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/admin/hotel">취소하기</a>
		        </div>
			</fieldset>
		</form>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	</body>
</html>