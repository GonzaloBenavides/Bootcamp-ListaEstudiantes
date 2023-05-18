<%@page import="org.springframework.ui.Model"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<title>Students - ALL</title>
</head>
<body>
	<h1 style="text-align: center;">
		<c:out value="${dorm.name}" />
	</h1>
	<a href="/">Home</a>

	<form:form action="/dorm/${dorm.id}" modelAttribute="freeStudent"
		method="post">
		<form:select class="form-select" path="id" id="stuId">
			<form:option class="form-option" value="0">-Select student-</form:option>
			<c:forEach items="${dormStudents}" var="newStu">
				<form:option class="form-option" value="${newStu.id}"> ${newStu.firstName} ${newStu.lastName} </form:option>
			</c:forEach>
		</form:select>
		<input type="submit" class="btn btn-primary" value="Add" />
	</form:form>


	
	<table class="table table-striped mt-5 border border-1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${students}">
				<tr>
					<td><c:out value=" ${s.firstName} ${s.lastName} " /></td>
					<td><a href="/dorm/${dorm.id}/remove?student=${s.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>