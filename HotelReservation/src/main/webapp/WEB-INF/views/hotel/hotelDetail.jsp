<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>HotelDetail</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
	<c:import url="${pageContext.request.contextPath}/nav"></c:import>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<div class="card mb-3" style="width: 18%; display: inline-flex;">
		<h3 class="card-header">Card header</h3>
		<div class="card-body">
			<h5 class="card-title">Special title treatment</h5>
			<h6 class="card-subtitle text-muted">Support card subtitle</h6>
		</div>
		<svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
			<rect width="100%" height="100%" fill="#868e96"></rect>
			<text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
		</svg>
		<div class="card-body">
			<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		</div>
		<ul class="list-group list-group-flush">
			<li class="list-group-item">Cras justo odio</li>
			<li class="list-group-item">Dapibus ac facilisis in</li>
			<li class="list-group-item">Vestibulum at eros</li>
		</ul>
		<div class="card-body">
			<a href="#" class="card-link">Card link</a>
			<a href="#" class="card-link">Another link</a>
		</div>
		<div class="card-footer text-muted">
		  2 days ago
		</div>
	</div>
	<div style="display: inline-flex;">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Type</th>
					<th scope="col">Column heading</th>
					<th scope="col">Column heading</th>
					<th scope="col">Column heading</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-active">
					<th scope="row">Active</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr>
					<th scope="row">Default</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr class="table-primary">
					<th scope="row">Primary</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr class="table-secondary">
					<th scope="row">Secondary</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
			</tbody>
		</table>
	</div>
		<div class="card" style="width: 18%; display: inline-flex;">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
				<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
				<a href="#" class="card-link">Card link</a>
				<a href="#" class="card-link">Another link</a>
			</div>
		</div>
		<div class="card bg-light mb-3" style="max-width: 20rem;">
			<div class="card-header">Header</div>
			<div class="card-body">
				<h4 class="card-title">Light card title</h4>
				<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
			</div>
		</div>
</body>
</html>