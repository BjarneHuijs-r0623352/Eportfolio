<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header>
	<h1><span>Web shop</span></h1>
	<nav>
		<ul>
			<c:choose>					
				<c:when test="${param.current.equals('UserOverview')}">
					<li><a href="Controller">Home</a></li>
					<li id="actual"><a href="Controller?action=overview">Overview</a></li>
					<li><a href="Controller?action=products">Products</a></li>
					<li><a href="addProduct.jsp">Add Product</a>
					<li><a href="signUp.jsp">Sign up</a></li>
				</c:when>
				
				<c:when test="${param.current.equals('ProductOverview')}">
					<li><a href="Controller">Home</a></li>
					<li><a href="Controller?action=overview">Overview</a></li>						
					<li id="actual"><a href="Controller?action=products">Products</a></li>
					<li><a href="addProduct.jsp">Add Product</a>
					<li><a href="signUp.jsp">Sign up</a></li>
				</c:when>
				
				<c:when test="${param.current.equals('AddProduct')}">
					<li><a href="Controller">Home</a></li>
					<li><a href="Controller?action=overview">Overview</a></li>
					<li><a href="Controller?action=products">Products</a></li>	
					<li id="actual"><a href="addProduct.jsp">Add Product</a>
					<li><a href="signUp.jsp">Sign up</a></li>				
				</c:when>
				
				<c:when test="${param.current.equals('SignUp')}">
					<li><a href="Controller">Home</a></li>
					<li><a href="Controller?action=overview">Overview</a></li>
					<li><a href="Controller?action=products">Products</a></li>
					<li><a href="addProduct.jsp">Add Product</a>
					<li id="actual"><a href="Controller?action=signUp">Sign up</a></li>
				</c:when>
				<c:otherwise>
					<li id="actual"><a href="Controller">Home</a></li>
					<li><a href="Controller?action=overview">Overview</a></li>
					<li><a href="Controller?action=products">Products</a></li>
					<li><a href="addProduct.jsp">Add Product</a>
					<li><a href="signUp.jsp">Sign up</a></li>
				</c:otherwise>
			</c:choose>
			
		</ul>
	</nav>
	<h2>${param.title}</h2>
</header>