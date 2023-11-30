<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
		<title>Project Manager</title>
	</head>
	<body>
		<div class="container">
			
			<div class="text-center">
				<h1 class="mt-5 text-primary">Project Manager</h1>
				<p>A place for teams to manage projects.</p>
			</div>
		
			<div class="row">
				<div class="col-6 p-3">
					<h2>Register</h2>
					<form:form action="/register" method="post" modelAttribute="register">
						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="firstName">first Name :</form:label>
							<div class="col-sm-9">
			                	<form:input class="form-control" path="firstName"/>
			                </div>
			                <form:errors class="col-12 text-danger" path="firstName"/>
						</div>

						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="lastName">Last Name :</form:label>
							<div class="col-sm-9">
			                	<form:input class="form-control" path="lastName"/>
			                </div>
			                <form:errors class="col-12 text-danger" path="lastName"/>
						</div>
						
						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="email">Email :</form:label>
			                <div class="col-sm-9">
			                	<form:input class="form-control" type="email" path="email"/>
			                </div>
			                <form:errors class="text-danger" path="email"/>
						</div>
						
						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="password">Password :</form:label>
			                <div class="col-sm-9">
			                	<form:input class="form-control" type="password" path="password"/>
			                </div>
			                <form:errors class="text-danger" path="password"/>
						</div>
					
						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="confirm">Confirm PW :</form:label>
			                <div class="col-sm-9">
			                	<form:input class="form-control" type="password" path="confirm"/>
			                </div>
			                <form:errors class="text-danger" path="confirm"/>
						</div>
						
						<div class="d-flex justify-content-end mt-5">
							<input type="submit" class="btn btn-primary" value="Submit" />
						</div>
					</form:form>
				</div>

				<div class="col-6 p-3 ml-5">
					<h2>Log in</h2>
					<form action="/" method="post">
						<div class="row mb-2">
							<label class="col-sm-3 col-form-label" for="email">Email :</label>
			                <div class="col-sm-9">
			                	<input class="form-control" type="email" name="email" id="email"/>
			                </div>
						</div>

						<div class="row mb-2">
							<label class="col-sm-3 col-form-label" for="password">Password :</label>
			                <div class="col-sm-9">
			                	<input class="form-control" type="password" name="password" id="password"/>
			                </div>
						</div>
						
						<div class="d-flex justify-content-end mt-5">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="submit" class="btn btn-primary" value="Submit" />
						</div>
					</form>
				</div>

			</div>
		</div>
	</body>
</html>