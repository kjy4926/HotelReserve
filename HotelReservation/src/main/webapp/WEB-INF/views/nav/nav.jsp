<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>navigation</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Kingsman</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarColor02">
	      <ul class="navbar-nav container-fluid justify-content-around">
	        <li class="nav-item">
	          <a class="nav-link" href="#">Home
	            <span class="visually-hidden">(current)</span>
	          </a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Hotel</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Restaurant</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Inquire</a>
	        </li>
	      </ul>
	      <div class="d-flex">
	      	<sec:authorize access="isAuthenticated()">
		        <div class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" 
		          	role="button" aria-haspopup="true" aria-expanded="false" style="min-width: 180px;">Account님</a>
		          <div class="dropdown-menu">
		            <a class="dropdown-item" href="#">마이 페이지</a>
		            <a class="dropdown-item" href="#">관리자 페이지</a>
		            <div class="dropdown-divider"></div>
		            <a class="dropdown-item" href="#">Logout</a>
		          </div>
		        </div>
	        </sec:authorize>
	        <sec:authorize access="!isAuthenticated()">
	        	<div class="nav-item" style="margin: auto 0;">
		        	<a class="nav-link" href="#">Login</a>
	        	</div>
	        	<div class="nav-item">
		        	<a class="nav-link" href="#">
	        			<button class="btn btn-outline-info" type="button">SignUp</button>
	        		</a>
        		</div>
	        </sec:authorize>
	      </div>
	    </div>
	  </div>
	</nav>
</body>
</html>