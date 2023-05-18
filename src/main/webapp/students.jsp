<%@page import="org.springframework.ui.Model"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/4f7f009016.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/css/main.css">
<title>Students - ALL</title>
</head>
<body>
	<h1 style="text-align: center;">Students</h1>
	<a href="/">Home</a> <a href="/students/new">New Student</a> <a href="/contact/new">Add Contact Info</a>
	<table class="table table-striped mt-5 border border-1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Age</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${students}">
				<tr> 
					<td><c:out value="${s.student.firstName} ${s.student.lastName} " /></td>
					<td><c:out value="${s.student.age}" /></td>
					<td><c:out value="${s.address }" /></td>
					<td><c:out value="${s.city}" /></td>
					<td><c:out value="${s.state}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>