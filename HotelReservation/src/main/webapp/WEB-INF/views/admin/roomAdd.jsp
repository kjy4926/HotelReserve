<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>방 등록</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style type="text/css">
		.img-field{
			max-width: 300px;
			max-height: 300px;
			text-align: left;
		}
		.btn-field{float: right;}
		img{width: 100%}
	</style>
</head>
	<body>
		<c:import url="${pageContext.request.contextPath}/nav"></c:import>
		
		<form onsubmit="return roomAddConfirm()" class="container" action="/admin/hotel/room/new/${hotelSeq}" method="post" enctype="multipart/form-data">
		  <fieldset>
		    <legend>방 등록</legend>
		    <input type="hidden" name="hotelSeq" id="hotelSeq" value="${hotelSeq}">
		    <div class="form-group">
		      <label for="name" class="form-label mt-4">방이름</label>
		      <input type="text" class="form-control" id="name" name="name" placeholder="방이름을 입력하세요">
		    </div>
		    <div>
		    	<label for="description" class="form-label mt-4">수용 인원</label>
		    	<select class="form-select" id="people" name="people">
		    		<c:forEach var="i" begin="1" end="20" varStatus="status">
		    			<option value="${i}">${i}인</option>
		    		</c:forEach>
		    	</select>
		    </div>
		    <div class="form-group">
		      <label for="price" class="form-label mt-4">가격</label>
		      <input type="number" class="form-control" id="price" name="price" placeholder="가격을 입력하세요">
		    </div>
		    <div class="form-group">
		      <label for="description" class="form-label mt-4">소개</label>
		      <input type="text" class="form-control" id="description" name="description" placeholder="방에 대한 정보를 입력하세요">
		    </div>
		    <div class="form-group">
		      <label for="img" class="form-label mt-4">방 사진</label>
		      <input type="file" class="form-control" name="uploadFile" accept="image/*" onchange="setImg(this)"/>
		    </div>
		    	<label for="preview" class="form-label mt-4">이미지 미리보기</label>
			<div class="img-field">
	        	<img id="img" name="img" src="">
			</div>
				<div class="btn-field">
		        	<button type="submit" class="btn btn-outline-secondary">방등록</button>
		        	<a class="btn btn-outline-secondary" href="location.href='${pageContext.request.contextPath}/admin'">취소하기</a>
		        </div>
			</fieldset>
		</form>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	</body>
</html>