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
			<jsp:param name="title" value="Product Overview"/> 
			<jsp:param name="current" value="ProductOverview"/>
		</jsp:include> 
		<main>
			<table>
				<tr>
					<th>Product ID</th>
					<th>Description</th>
					<th>Price</th>
				</tr>
				<c:forEach var="Product" items="${allProducts}">
					<tr>
						<td><a href="Controller?action=UpdateProduct&ID=${Product.productId}&Description=${Product.description}&Price=${Product.price}">${Product.productId}</a></td>
						<td>${Product.description}</td>
						<td>${Product.price}</td>
						<td><a href="Controller?action=DeleteProduct&productID=${Product.productId}">Delete</a></td>
					</tr>
				</c:forEach>
				<caption>Products Overview</caption>
			</table>
		</main>
		<footer>
			&copy; Webontwikkeling 3, UC Leuven-Limburg
		</footer>
	</div>
</body>
</html>