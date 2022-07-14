<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
	<!-- Kakao daum postcode -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style type="text/css">
		.signup-form{margin:auto; width:700px;}
		.submit-reset{float:right;}
	</style>
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	
	<form method="post">
	  <fieldset class="signup-form">
		<br><legend>Sign Up</legend>
			<div class="form-group has-danger has-success">
				<label class="form-label mt-4" for="inputID">ID</label>
				<input type="text" placeholder="ID input" class="form-control" id="userId" name="userId"
				 	pattern="^[a-z][a-z0-9]{7,15}$" oninput="" required>
				<div class="invalid-feedback"><span id="idResultInValid"></span></div>
				<div class="valid-feedback"><span id="idResultValid"></span></div>
				<small id="idHelp" class="form-text text-muted">ID는 8자부터 16자까지 영어소문자 및 숫자만 사용 가능합니다.</small>
				<button type="button" class="btn btn-dark btn-sm disabled" name="idcheck"
					onclick="" style="float:right;" >Duplicate check</button>
				<input type="hidden" id="dupflag" name="dupck" value="0"> 
			</div>
			<div class="form-group">
				<label for="password" class="form-label mt-4">Password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Password" 
			    	pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{12,20}$$" required>
				<small id="pwdHelp" class="form-text text-muted">비밀번호는 12자부터 20자까지 알파벳, 숫자, 특수문자만 사용 가능합니다.</small><br>
				<small id="pwdHelp2" class="form-text text-muted">적어도 각각 하나의 알파벳, 숫자, 특수문자가 입력되어야 합니다.</small>
			</div>
			<div class="form-group">
				<label for="passwordCheck" class="form-label mt-4">Password Check</label>
				<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="Password Check" 
					pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{12,20}$$" required>
			</div>
			<div class="form-group">
				<label for="email" class="form-label mt-4">Email Address</label>
				<input type="email" class="form-control" id="email" name="email" 
					pattern="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$" aria-describedby="emailHelp" placeholder="Enter email" required>
				<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="phone" class="form-label mt-4">Phone Number</label>
				<input type="text" class="form-control" id="phone" name="phone" placeholder="010-1234-5678" maxlength="13" 
					oninput="setPhoneForm(this)" required>
			</div>
			<div class="form-group">
				<label for="address" class="form-label mt-4">Address</label>
				<div class="input-group">
					<input type="text" class="form-control" id="address" name="address" placeholder="" readonly required>
					<input type="button" class="btn btn-outline-dark" value="검색" onclick="searchAddress()">
				</div>
				<input type="text" class="form-control" id="addressDetail" name="addressDetail" placeholder="상세 주소" required>
			</div>
			<div class="form-group">
				<label for="birth" class="form-label mt-4">Birth</label>
				<input type="number" class="form-control" id="birth" name="birth" placeholder="ex) 20220101" oninput="setPhoneForm(this)" required>
			</div>
			<br><div class="submit-reset">
				<button type="reset" class="btn btn-primary">Reset</button>
				<button type="submit" class="btn btn-primary">Sign up</button>
			</div>
			<br><br><br><br>
		</fieldset>
	</form>
	
	
	

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>