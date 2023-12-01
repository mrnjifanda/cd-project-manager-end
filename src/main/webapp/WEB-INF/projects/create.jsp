<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
		<title>New Project</title>
	</head>
	<body>
		<div class="container mt-5">
			<div class="card p-5">
				<h1 class="mb-5">Create a project</h1>
			
				<form:form action="/projects/new" method="post" modelAttribute="project">
					<div class="row mb-2">
						<form:label class="col-sm-3 col-form-label" path="title">Project Title :</form:label>
						<div class="col-sm-9">
		                	<form:input class="form-control" path="title"/>
		                </div>
		                <form:errors class="col-12 text-danger" path="title"/>
					</div>
	
					<div class="row mb-2">
						<form:label class="col-sm-3 col-form-label" path="description">Project Description :</form:label>
						<div class="col-sm-9">
		                	<form:input class="form-control" path="description"/>
		                </div>
		                <form:errors class="col-12 text-danger" path="description"/>
					</div>
					
					<div class="row mb-2">
						<form:label class="col-sm-3 col-form-label" path="dueDate">Due Date </form:label>
						<div class="col-sm-9">
		                	<form:input type="date" class="form-control" path="dueDate"/>
		                </div>
		                <form:errors class="col-12 text-danger" path="dueDate"/>
					</div>
							
					<div class="d-flex justify-content-around mt-5">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="reset" class="btn btn-secondary" value="Cancel" />
						<input type="submit" class="btn btn-primary" value="Submit" />
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>