<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<<<<<<< Updated upstream
=======
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
	<form action="/inquire" class="form-inline d-flex justify-center-center" method="GET">
		<div class="form-group">	
			<select class="form-select" id="cat" name="cat" style="width:100px"> <%-- 검색조건 --%>
				<option value="title">제목</option>
				<option value="description">내용</option>
				<option value="writer">작성자</option>
			</select>
			<%-- 검색어 입력 --%>
			<input type="text" class="form-control" placeholder="검색어를 입력하세요" id="keyword" name="keyword" style="width:400px" >&nbsp&nbsp
			<%-- 검색 버튼 --%>
			<button class="btn btn-primary" type="submit"> 검색 </button>
		</div>
    </form>
	
		

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
>>>>>>> Stashed changes

</body>
</html>