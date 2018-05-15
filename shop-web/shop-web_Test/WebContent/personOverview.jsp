<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta charset="UTF-8">
	<title>Overview</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">  
			<jsp:param name="title" value="User Overview"/> 
			<jsp:param name="current" value="UserOverview"/>
		</jsp:include> 
		<main>
			<a href="Controller?action=SortPersons">Sorteer</a>
			<table>
				<tr>
					<th>E-mail</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Length</th>
					<th>Delete</th>
					<th>Check</th>
				</tr>
				<c:forEach var="person" items="${allPersons}">
					<tr>
						<td>${person.email}</td>
						<td>${person.firstName}</td>
						<td>${person.lastName}</td>
						<td>${person.length}</td>
						<td><a href="Controller?action=DeletePerson&personID=${person.userid}">Delete</a></td>
						<td><a href="Controller?action=CheckPassword&personID=${person.userid}">Check</a></td>
					</tr>
				</c:forEach>
				<caption>Users Overview</caption>
			</table>
		</main>
		<footer>
			&copy; Webontwikkeling 3, UC Leuven-Limburg
		</footer>
	</div>
</body>
</html>