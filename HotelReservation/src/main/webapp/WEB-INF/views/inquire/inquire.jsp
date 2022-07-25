<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기</title>

<%-- Main Style Sheet --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.form-group{
		    width: 100%;
		}		
		.form-select {
		    float:left;
		    width:30%;
		    box-sizing: border-box;
		}		
		.form-control{
		    float:left;
		    width:50%;
		    box-sizing: border-box;
		}		
		.btn btn-primary{
		    float:left;
		    width:10%;
		    box-sizing: border-box;
		}
		.pagination{
			justify-content : center;
		}
	</style>
</head>
<body>
	<%-- Header --%>
	
	
	<%-- Navigation --%>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<%-- 상단 제목 --%>
	<div class="crew-topContainer" style="width: 1100px">
	
		<%-- 제목 --%>
		<div class="common-top_title" style="font-size: 30px; color: #FAAC58">
		문의하기</div><hr><br>
	</div>


	<%-- 검색바 --%>
	<div class="form-group">
      <select class="form-select" id="search" style="width:100px;height:40px;"> <%-- 검색조건 --%>
        <option value="제목">제목</option>
        <option value="내용">내용</option>
        <option value="작성자">작성자</option>
      </select>
      <input type="text" class="form-control" style="width:300px;height:40px;" placeholder="검색어를 입력하세요" id="keyword">
		 <%-- 검색어 입력 --%>
		<button class="btn btn-primary"> 검색  <%-- 검색 버튼 --%>	
		</button>
  		
    </div>
	
		

	<%-- 글쓰기 버튼 --%>
	<div id="inquire-top-buttonbox" align="right">
		<button class="btn btn-outline-danger"  onclick="location.href='/inquire/write'">글쓰기</button>

	</div>

	<%-- 글 목록 --%>	
	<div id="inquire-list">
		<table class="table table-hover">
		  <thead>
		    <tr>
		      <th scope="col">글번호</th>
		      <th scope="col">카테고리</th>
		      <th scope="col">제목</th>
		      <th scope="col">등록일</th>
		      <th scope="col">작성자</th>
		      <th scope="col">답변상태</th>	      
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${list.content}" var="inquire" begin="0" end="40">
		  		<tr class="list-item, status: ${items.getContent()}" class="table-active">
			      <td>th:text="${inquire.seq}"></td>
			      <td>${inquire.category}</td>
			      <td>${inquire.title}</td>
			      <td>${inquire.regDate}</td>
			      <td>${inquire.writer}</td>
			      <td>${inquire.status}</td>
			    </tr>		  	
		  	</c:forEach>		    
		  </tbody>
		</table>
	</div>
	
	<%--Page Nation --%>
	<ul class="pagination" style="align-center">
		<!-- 첫 페이지로 이동 -->
	<li>
		<a class="page-link" href="/review?page=0"> << </a>
	</li>
	
		<!-- 이전 페이지로 이동 : 첫 페이지 제외 -->
		<c:if test="${startBlockPage ne 1}">
		    <li>
		        <a class="page-link" href="/review?page=${startBlockPage-2}">
		            < </a>
		    </li>
		</c:if>
		
		<!-- 페이징 블록 1 ~ 10 -->
		<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="idx">
		    <li>
		        <a class="page-link" href="/review?page=${idx-1}">${idx}</a>
		    </li>
		</c:forEach>
	
	    <!-- 다음 페이지로 이동 : 마지막 페이지 제외 -->
		<c:if test="${endBlockPage ne reviewList.totalPages}">
			<li>
			    <a class="page-link" href="/review?page=${endBlockPage}">
			        > </a>
			</li>
		</c:if>
	
		<!-- 마지막 페이지로 이동 -->
		<li>
		    <a class="page-link" href="/review?page=${reviewList.totalPages-1}">
		        >> </a>
		</li>
	</ul>					
	<hr><br><br>


<!-- 
	{{! Page }}
	<div class="pagination justify-content-center">
		{{#hasPrev}}
			<a href="?page={{previous}}" role="button" class=""></a>
		{{/hasPrev}}
		{{^hasPrev}}
			<a href="?page={{previous}}" role="button" class=""></a>        
		{{/hasPrev}}         
		{{#hasNext}}            
			<a href="?page={{next}}" role="button" class=""></a>        
		{{/hasNext}}        
		{{^hasNext}}            
			<a href="?page={{next}}" role="button" class=""></a>        
		{{/hasNext}}    
	</div>
 -->
	
	<%-- footer --%>

</body>
</html>