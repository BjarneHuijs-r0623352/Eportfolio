<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta charset="UTF-8">
	<title>Sign Up</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
	<jsp:include page="header.jsp">  
			<jsp:param name="title" value="Sign Up"/>
			<jsp:param name="current" value="SignUp"/>
	</jsp:include> 
	<main>
		<c:if test="${errors.size() > 0}">
			<div class="alert-danger">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		
	    <form method="post" action="Controller?action=SignUp" novalidate="novalidate">
	    	<!-- novalidate in order to be able to run tests correctly -->
	        <p>
		        <label for="userid">User id</label>
		        <input type="text" id="userid" name="userid" required value="<c:out value="${ID}"/>"> 
	        </p>
	        <p>
		        <label for="firstName">First Name</label>
		        <input type="text" id="firstName" name="firstName" required value="<c:out value="${FirstName}"/>"> 
	        </p>
	        <p>
	        	<label for="lastName">Last Name</label>
	       		<input type="text" id="lastName" name="lastName" required value="<c:out value="${LastName}"/>"> 
	        </p>
	        <p>
	        	<label for="length">Length</label>
	        	<input type="text" id="length" name="length" required value="<c:out value="${Length}"/>">
	        </p>
	        <p>
	        	<label for="email">Email</label>
	        	<input type="email" id="email" name="email" required value="<c:out value="${Email}"/>">
	        </p>
	        <p>
	        	<label for="password">Password</label>
	        	<input type="password" id="password"  name="password" required> 
	        </p>
	        <p>
	        	<input type="submit" id="signUp" value="Sign Up">
	        </p>
	        
	    </form>
	</main>
	<footer>
		&copy; Webontwikkeling 3, UC Leuven-Limburg
	</footer>
	</div>
</body>
</html>
