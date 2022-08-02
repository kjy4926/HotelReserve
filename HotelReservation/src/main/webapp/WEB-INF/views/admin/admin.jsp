<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>admin</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.menu-div-field{
			width: 90%;
			margin: 3% auto;
			display: flex;
		}
		.menu-div{
			display: flex;
			align-items: center;
			justify-content: center;
			width: 50%;
		}
		.menu-img{
			width: 100%;
		}
		.menu-text-field{text-align: center; width: 50%}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	<!-- 나중에 사진 수정 -->
	<div class="menu-div-field">
		<div class="menu-div" onclick="location.href='${pageContext.request.contextPath}/admin/hotel'">
			<img class="menu-img" src="${pageContext.request.contextPath}/resources/img/adminMenu/hotelManage.jpg">
		</div>
		<div class="menu-div" onclick="location.href='${pageContext.request.contextPath}/admin/restaurant'">
			<img class="menu-img" src="${pageContext.request.contextPath}/resources/img/adminMenu/restaurantManage.jpg">
		</div>
	</div>
	<div class="menu-div-field">
		<div class="menu-div">
			<h3>Hotel Management</h3>
		</div>
		<div class="menu-div">
			<h3>Restaurant Management</h3>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>