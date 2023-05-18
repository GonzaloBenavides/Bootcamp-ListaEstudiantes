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
<title>Students API</title>
</head>
<body>
	<div class="container">
		<div class="col-ms-12">
			<a href="/">Home</a>
			<h1>ADD Contact Info</h1>
			<form:form action="/contact/create" method="post" modelAttribute="contact">
				<div class="row mb-3">
					<form:errors class="text-danger" path="student" />
					<form:errors class="text-danger" path="address" />
					<form:errors class="text-danger" path="city" />
					<form:errors class="text-danger" path="state" />
				</div>
				
				<div class="form-outline mb-4">
					<form:select class="form-select" path="student" id="student">
						<form:option class="form-option" value="null" >-Select student-</form:option>
						<c:forEach items="${students}" var="stu">
							<form:option class="form-option" value="${stu}"> ${stu.firstName} ${stu.lastName}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-outline mb-4">
					<form:input class="form-control" type="text" path="address" id="address" />
					<form:label class="form-label" for="address" path="address">Address</form:label>
				</div>
				<div class="form-outline mb-4">
					<form:input class="form-control" type="text" path="city" id="city" />
					<form:label class="form-label" path="city">City</form:label>
				</div>
				<div class="form-outline mb-4">
					<form:input class="form-control" type="text" path="state" id="state" />
					<form:label class="form-label" path="state">State</form:label>
				</div>
				<div>
					<input type="submit" class="btn btn-primary" value="Crear" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>