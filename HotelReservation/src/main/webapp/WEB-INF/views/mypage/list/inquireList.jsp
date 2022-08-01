<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의 내역</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.list-field{width: 90%; margin: auto;}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/delete.js"></script>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div class="list-field">
		<ul class="nav nav-tabs" role="tablist">
		  <li class="nav-item" role="presentation">
		    <a class="nav-link active" data-bs-toggle="tab" href="#hotel" aria-selected="true" role="tab">문의 내역</a>
		  </li>
		</ul>
		<div id="myTabContent" class="tab-content">
		  <div class="tab-pane fade active show" id="hotel" role="tabpanel">
				<table class="table">
		        	<thead>
						<tr>
							<th style="width: 10%">문의 번호</th>
							<th style="width: 10%">카테고리</th>
							<th style="width: 30%">제목</th>
							<th style="width: 25%">작성일</th>
							<th style="width: 25%">작성자</th>
						<tr>
					</thead>
					<tbody>
					<c:forEach var="inquire" items="${inquireList}" varStatus="status">
							<tr onclick="location.href='${pageContext.request.contextPath}/inquire/read/${inquire.seq}'">
								<td>${inquire.seq}</td>
								<td>${inquire.category}</td>
								<td>${inquire.title}</td>
								<td>${inquire.day}</td>
								<td>${inquire.writer}</td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
		  </div>
		</div>	
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>