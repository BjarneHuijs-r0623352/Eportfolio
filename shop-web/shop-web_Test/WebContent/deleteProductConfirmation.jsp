<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Confirm</title>
</head>
<body>
	<jsp:include page="header.jsp">  
		<jsp:param name="title" value="Sign Up"/>
		<jsp:param name="current" value="SignUp"/>
	</jsp:include>
	<h1>Do you want to delete the product with ID ${productID} from the list?</h1>
	<main>
		<form method="post" action="Controller?action=DeleteProductConfirmed&productID=${productID}">
			<input type="submit" name="submit" value="Delete">
			<input type="submit" name="submit" value="Cancel">
		</form>
	</main>
</body>
</html>