<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>호텔 관리</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		td{vertical-align: middle;}
		.search-field{width: 60%}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<div style="width: 90%; margin: auto;">
		<div class="search-field">
			<form class="d-flex" method="post" action="${pageContext.request.contextPath}/admin/hotel/search">
				<select class="form-select" id="searchType" name="searchType" style="width: 20%">
						<option value="name">호텔명</option>
						<option value="addr">지역</option>
				</select>
		        <input class="form-control me-sm-2" type="text" placeholder="Search" id="search" name="search">
		        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
		<table class="table">
		    <thead>
				<tr>
					<th style="width: 10%">호텔번호</th>
					<th style="width: 15%">호텔명</th>
					<th style="width: 25%">주소</th>
					<th style="width: 10%">전화번호</th>
					<th style="width: 25%">설명</th>
					<th style="width: 15%"></th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="hotel" items="${hotelListDto}" varStatus="status">
					<tr onclick="location.href='${pageContext.request.contextPath}/admin/room/${hotel.seq}'">
						<td>${hotel.seq}</td>
						<td>${hotel.name}</td>
						<td>${hotel.address}</td>
						<td>${hotel.phone}</td>
						<td>${hotel.description}</td>
						<td>
							<button class="btn btn-secondary btn-sm" type="button" 
								onclick="location.href='${pageContext.request.contextPath}/admin/hotel/change/${hotel.seq}'">호텔수정</button>
							<button class="btn btn-outline-secondary btn-sm" type="button" 
								onclick="location.href='${pageContext.request.contextPath}/admin/hotel/room/new/${hotel.seq}'">룸 등록</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<c:choose>
				<c:when test="${maxP <= 5}">
				  <ul class="pagination container-fluid justify-content-center">
				  	<li class="page-item">
				      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP}">&laquo;</a>
				    </li>
				  	<c:forEach var="i" begin="1" end="${maxP}" varStatus="vs">
					  	<li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP-vs.index+1}">${maxP-vs.index+1}</a>
					    </li>
				  	</c:forEach>
				    <li class="page-item">
				      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel">&raquo;</a>
				    </li>
				  </ul>
				</c:when>
				<c:when test="${p<=3}">
					<ul class="pagination container-fluid justify-content-center">
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP}">&laquo;</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/5">5</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/4">4</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/3">3</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/2">2</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/1">1</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/list">&raquo;</a>
					    </li>
					</ul>
				</c:when>
				<c:when test="${maxP-p <= 2}">
					<ul class="pagination container-fluid justify-content-center">
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP}">&laquo;</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP}">${maxP}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP-1}">${maxP-1}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP-2}">${maxP-2}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP-3}">${maxP-3}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP-4}">${maxP-4}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel">&raquo;</a>
					    </li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="pagination container-fluid justify-content-center">
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${maxP}">&laquo;</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${p+2}">${p+2}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${p+1}">${p+1}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${p}">${p}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${p-1}">${p-1}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel/${p-2}">${p-2}</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/admin/hotel">&raquo;</a>
					    </li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	<script type="text/javascript">
		function getSearch(){
		    var urlParams = new URL(location.href).searchParams
		    var search = urlParams.get('search')
		    return search
		}
		function getSearchType(){
		    var urlParams = new URL(location.href).searchParams
		    var searchType = urlParams.get('searchType')
		    return searchType
		}
		$('a').bind('click', function(){ 
			//현재 이벤트가 적용된 index와 html 그 자체를 인자값으로 넣을 수 있음.
			var search = getSearch()
			var searchType = getSearchType()
			if(search != null){
				this.setAttribute('href', this.getAttribute('href')+'?search='+search+'&searchType='+searchType)
			}
		});
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>