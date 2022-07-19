<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>맛집 리스트</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link href="${pageContext.request.contextPath}/resources/css/restaurant.css" rel="stylesheet" type="text/css">
<style>
	img {
		width:100px;
		height:100px;
		object-fit:contain;
	}
	div {
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 상단메뉴 -->
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<section id="restaurantList-container">
        <!--배경사진-->
        <div id="restaurantList-img">
            <img src="${pageContext.request.contextPath}/resources/img/default.png" width="1500" height="550px">
        </div>
		
	<!-- 검색창 -->
	<form action="">
		<div class="mx-auto mt-5 search-bar input-group mb-3">
  			<input type="text" class="form-control rounded-pill" placeholder="검색바" aria-label="Recipient's username" aria-describedby="button-addon2">
       		<button class="btn btn-primary" type="button" id="button-addon2">검색</button>
    	</div>
    </form>
	
	<!--
	<div id="restaurantList-nav">
		<div id="restaurantList-nav__search">
			<form action="${pageContext.request.contextPath}/restaurant" method="get">
				<input type="text" id="restaurantList-nav__search__input" placeholder="맛집 검색" name="searchKeyword" value="${searchKeyword }">
				<button id="restaurantList-nav__search__btn" type="submit" value="검색">
                    </button>
                </form>
            </div>
        </div>
     -->
    <!-- 맛집 목록 -->
    <table class="table">
    	<thead class="thead-dark">
   		<tr>
        	<th scope="col">사진</th>
        	<th scope="col">상호명</th>
        	<th scope="col">주소</th>
        	<th scope="col">연락처</th>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="restaurant" items="${restaurantList}" varStatus="Loop">
        	<tr>
        	<th scope="row"><img alt="" src="<c:url value="/resources/img/restaurantImg/${restaurant.img}"/>"/></th>
        	<td><a href="<c:url value="restaurant/restaurantDetail/${restaurant.seq}"/>">${restaurant.name}</a></td>
        	<td>${restaurant.address}</td>
        	<td>${restaurant.phone}</td>
    		</tr>
    	</c:forEach>
	</tbody>
</table>
</section>
</body>
</html>