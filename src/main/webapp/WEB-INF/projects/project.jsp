<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
		<title>Project Details</title>
	</head>
	<body>
		<div class="container mt-5">
			<div class="d-flex align-items-end justify-content-between">
				<h1>Project Details</h1>
		
				<a href="/dashboard">Back to Dashboard</a>
			</div>
			
			<div class="mt-5">
				<div class="d-flex align-items-end mb-5">
					<p style="min-width: 200px;">Title: </p>
					<h2><c:out value="${ project.title }" /></h2>
				</div>
				
				<div class="d-flex align-items-end mb-5">
					<p style="min-width: 200px;">Description: </p>
					<p><c:out value="${ project.description }" /></p>
				</div>
				
				<div class="d-flex align-items-end">
					<p style="min-width: 200px;">Due Date: </p>
					<p><c:out value="${ project.dueDate }" /></p>
				</div>
			</div>
			
			<div class="d-flex align-items-end justify-content-between mt-5">
				<a href="#" class="btn-link">See Tasks</a>
				
				<form action="/projects/remove/${ project.id }" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" class="btn btn-danger" value="Delete Project" />
				</form>
			</div>
		</div>
	</body>
</html>