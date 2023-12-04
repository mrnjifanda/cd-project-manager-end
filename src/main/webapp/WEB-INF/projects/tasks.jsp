<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
		<title>Props Page</title>
	</head>
	<body class="bg-danger">
		<div class="container mt-5">
			<div class="card p-5">
				<div class="d-flex align-items-end justify-content-between">
					<h1>Project Details</h1>
			
					<a href="/dashboard">Back to Dashboard</a>
				</div>
				
				<h4>Project Lead: <c:out value="${ project.user.firstName }"></c:out></h4>
				
				<div>
					<form:form action="/projects/${ project.id }/tasks" method="post" modelAttribute="task">
		
						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="description">Add task ticket for this team :</form:label>
							<div class="col-sm-9">
			                	<form:textarea class="form-control" path="description"/>
			                </div>
			                <form:errors class="col-12 text-danger" path="description"/>
						</div>
								
						<div class="d-flex justify-content-end mt-5">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="submit" class="btn btn-primary" value="Submit" />
						</div>
					</form:form>					
				</div>
			</div>
			
			<div class="card p-5 mt-5">
				<div>
					<c:forEach var="taskProject" items="${ project.tasks }">
						<div class="mt-2">
							<h6>Added by <c:out value="${ taskProject.user.firstName }"></c:out> at <c:out value="${ taskProject.createdAt }"></c:out>: </h6>
							<p><c:out value="${ taskProject.description }"></c:out></p>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
</html>