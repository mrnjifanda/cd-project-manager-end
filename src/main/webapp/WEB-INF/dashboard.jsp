<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
		<title>Project Manager Dashboard</title>
	</head>
	<body>
		<div class="container mt-5">
			<div class="d-flex align-items-end justify-content-between">
				<h1>Welcome, ${ connectedUser.firstName }!</h1>
		
				<form action="/logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input class="btn btn-link" type="submit" value="Logout" />
				</form>
			</div>
			
			<div class="d-flex align-items-end justify-content-between mt-5">
				<h4>All projects</h4>
				<a class="btn btn-primary" href="/projects/new">+ New Project</a>
			</div>
			
			<div class="mt-5">
				<table class="table text-center table-striped table-hover">
					<thead class="table-dark">
						<tr>
							<th>Project</th>
							<th>Team Lead</th>
							<th>Due Date</th>
							<th>Actions</th>
						</tr>	
					</thead>
					<tbody>
						<c:forEach var="team" items="${ teams }">
							<tr>
								<td>${ team.project.title }</td>
								<td>${ team.project.user.firstName }</td>
								<td>${ team.project.dueDate }</td>
								<td><a href="/teams/join/${ team.id }" class="btn btn-link">Join Team</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="mt-5">
				<h4>Your Project</h4>
				<table class="table text-center table-striped table-hover">
					<thead class="table-dark">
						<tr>
							<th>Project</th>
							<th>Lead</th>
							<th>Due Date</th>
							<th>Actions</th>
						</tr>	
					</thead>
					<tbody>
						<c:forEach var="yourTeam" items="${ connectedUserTeams }">
							<tr>
								<td><a href="/projects/${ yourTeam.project.id }">${ yourTeam.project.title }</a></td>
								<td>${ yourTeam.project.user.firstName }</td>
								<td>${ yourTeam.project.dueDate }</td>
								<td>								
									<c:choose>
									    <c:when test="${ yourTeam.project.user.id == connectedUser.id }">
									        <a href="/projects/edit/${ yourTeam.project.id }" class="btn btn-link">Edit</a>
									    </c:when>    
									    <c:otherwise>
											<a href="/teams/leave/${ yourTeam.id }" class="btn btn-link">Leave Team</a>
									    </c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>