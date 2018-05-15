<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta charset="UTF-8">
	<title>Add Product</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
	<jsp:include page="header.jsp">  
			<jsp:param name="title" value="Add product"/>
			<jsp:param name="current" value="AddProduct"/>
	</jsp:include> 
	<main>
		<c:if test="${errors.size()>0}">
			<div class="alert-danger">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<form method="post" action="Controller?action=AddProduct" novalidate="novalidate">
	    	<!-- novalidate in order to be able to run tests correctly -->
	        <p>
		        <label for="productid">Product id</label>
		        <input type="text" id="productid" name="productid" required value="<c:out value="${ID}"/>"> 
	        </p>

	        <p>
		        <label for="description">Description</label>
		        <input type="text" id="description" name="description" required value="<c:out value="${Description}"/>"> 
	        </p>

	        <p>
	        	<label for="price">Price</label>
	       		<input type="text" id="price" name="price" required value="<c:out value="${Price}"/>"> 
	        </p>
	        <p>
	        	<input type="submit" id="addProduct" value="Add Product">
	        </p>

	    </form>
	</main>
	<footer>
		&copy; Webontwikkeling 3, UC Leuven-Limburg
	</footer>
	</div>
</body>
</html>
