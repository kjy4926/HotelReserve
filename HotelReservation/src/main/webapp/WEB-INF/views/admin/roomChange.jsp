<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>roomChange</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
	<style type="text/css">
		.img-field{
			max-width: 200px;
			max-height: 200px;
			text-align: left;
		}
		.btn-field{float: right;}
		img{width: 100%}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	<form onsubmit="return roomChangeConfirm()" class="container" action="/admin/room/change/${hotelSeq}/${room.seq}" method="post" enctype="multipart/form-data">
		  <fieldset>
		    <legend>방 등록</legend>
		    <small class="fomr-text text-muted">
				수정할 부분만 입력해 주시면 됩니다.<br>
				주소, 사진의 경우 입력값이 없을 경우 기존 값으로 유지됩니다.
			</small>
			<input type="hidden" name="hotelSeq" id="hotelSeq" value="${hotelSeq}">
		    <div class="form-group">
		      <label for="name" class="form-label mt-4">방이름</label>
		      <input type="text" class="form-control" id="name" name="name" placeholder="방이름을 입력하세요" value="${room.name}">
		    </div>
		    <div>
		    	<label for="description" class="form-label mt-4">수용 인원</label>
		    	<select class="form-select" id="people" name="people">
		    		<c:forEach var="i" begin="1" end="20" varStatus="status">
		    			<c:choose>
			    			<c:when test="${room.people eq i}">
			    				<option value="${i}" selected>${i}인</option>
			    			</c:when>
			    			<c:otherwise>
			    				<option value="${i}">${i}인</option>
			    			</c:otherwise>
		    			</c:choose>
		    		</c:forEach>
		    	</select>
		    </div>
		    <div class="form-group">
		      <label for="price" class="form-label mt-4">가격</label>
		      <input type="number" class="form-control" id="price" name="price" placeholder="가격을 입력하세요" value="${room.price}">
		    </div>
		    <div class="form-group">
		      <label for="description" class="form-label mt-4">소개</label>
		      <input type="text" class="form-control" id="description" name="description" placeholder="방에 대한 정보를 입력하세요" value="${room.description}">
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
		        	<button type="submit" class="btn btn-outline-secondary">방 등록</button>
		        	<a class="btn btn-outline-secondary" href="location.href='${pageContext.request.contextPath}/admin'">취소하기</a>
		        </div>
			</fieldset>
		</form>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>