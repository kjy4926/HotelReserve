<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> -->
<!-- <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> -->
<c:set var="dateYMD" value="${DateTimeFormatUtil.changeToYMD(review.WDate)}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의상세</title>

<%-- 메인 스타일 sheet --%>

<%-- jQuery --%>


</head>
<body>
	<%-- nav --%>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<%-- 섹션 --%>
	<section id="inquireD-container">
		<div class="inquire-container-top">
			<%-- 제목 --%>
			<div class="common-top__title" style="color: #F6CECE;">
			문의 하기		
			</div>
			<%-- 드릴다운 --%>
			<span class="common-top__drilldownbox">
				<a href="#" style="color: #F78181;">문의 하기</a>
				<span> > </span>
				<a href="#">문의글 보기</a>
			</span>
		</div>
		
	<%-- 문의글 본문 --%>
		<table class="table table-hover">
			<tr>
				<td>글 번호</td>
				<td>
					<span id="in_seq">${inquire.seq}</span>
				</td>
				<td>작성일</td>
				<td>
					<span id="in_day">${inquire.regDate}</span>
				</td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
					<span id="in_cat">${inquire.category}</span>
				</td>
				<td>
					<span id="in_hotel">${inquire.hotelcode}</span>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<span id="in_title">${inquire.title}</span>
				</td>
			</tr>
			<tr>
				<td>문의내용</td>
				<td>
					<p id="in_desc">
						${inquire.description}
					</p>
				</td>
			</tr>
		</table>	
		
	</section>
		<button type="button" class = "btn btn-outline-info" onclick="location.href='/inquire'">목록</button>
		<button type="button" class = "btn btn-outline-success" onclick="location.href='/inquire/edit'">수정</button>
		<button type="button" class = "onclick-outline-danger=" onclick="location.href='/inquire/delete'">삭제</button>
		<button type="button" class = "btn btn-primary" onclick="location.href='/inquire/reply'">답변등록하기</button>
	
	<%-- 풋터 --%>
	
</html>