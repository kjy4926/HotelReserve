<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>맛집 등록</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<style>
.image_container {
	width:200px;
	height:200px;
	overflow:hidden;
	margin:0 auto;
}
img {
	width:200px;
	height:200px;
	object-fit:container;
}
</style>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div class="container">

    <div style="margin-bottom: 100px;"></div>
    <h1> 맛집 등록 </h1>
    <div style="margin-bottom: 10px;"></div>

    <form class="container" action="/restaurant/admin/new" method="post" enctype="multipart/form-data">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">상호명</span>
            </div>
            <input type="text" class="form-control" placeholder="상호명 입력" name="name">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">주소</span>
            </div>
            <input type="text" class="form-control" placeholder="주소 입력" name="address">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">연락처</span>
            </div>
            <input type="text" class="form-control" placeholder="연락처 입력" name="phone">
        </div>

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">소개</span>
            </div>
            <textarea class="form-control" placeholder="맛집 소개" name="description"></textarea>
        </div>
        <div style="margin-bottom: 10px;"></div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">맛집 사진</span>
            </div>
            <input type="file" class="form-control" name="uploadFile" accept="image/*" onchange="setThumbnail(event);"/>
        </div>
        	<table>
        		<tr>
        			<td rowspan="4" width="200" height="200">이미지 미리보기<div id="image_container"></div></td>
        		</tr>
        	</table>
        <button type="submit" class="btn btn-outline-secondary">확인</button>
        <a class="btn btn-outline-secondary" href="/restaurant">back</a>
    </form>
</div>
<div style="margin-bottom: 100px;"></div>
	<script>
		function setThumbnail(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = document.createElement("img");
				img.setAttribute("src", event.target.result);
				document.querySelector("div#image_container").appendChild(img);
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
</body>
</html>