<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta charset="UTF-8">
	<title>Check password</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
	<jsp:include page="header.jsp">  
			<jsp:param name="title" value="Check Password"/>
			<jsp:param name="current" value=""/>
	</jsp:include> 
	<main>
		<form method="post" action="Controller?action=PasswordConfirmed" novalidate="novalidate">
	    	<!-- novalidate in order to be able to run tests correctly -->
	        
	        <p>
	        	<label for="password">Password</label>
	        	<input type="password" id="password"  name="password" required> 
	        	<input type="hidden" id="personID" name="personID" value="${personID}">
	        </p>
	        <p>
	        	<input type="submit" id="Check" value="Check">
	        </p>
	        
	    </form>
	</main>
	<footer>
		&copy; Webontwikkeling 3, UC Leuven-Limburg
	</footer>
	</div>
</body>
</html>