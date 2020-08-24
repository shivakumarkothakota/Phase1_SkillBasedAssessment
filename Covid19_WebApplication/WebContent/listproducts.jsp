<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Covid19 Safety Kit: All Products [Administrator]</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<h3>Products</h3>

	<form action="logout" method="post">
		<div>
			<input type="submit" value="Logout" style="float: right;">
		</div>
	</form>

	<c:choose>
		<c:when test="${items==null || items.isEmpty() }">
			<p>No Items Found</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Cost</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.id }</td>
						<td>${item.productName }</td>
						<td>${item.cost }</td>
						<td>${item.productDescription }</td>
						<td><a href="deleteproduct?id=${item.id }">DELETE</a> <span>|</span>
							<a href="editproduct?id=${item.id }">EDIT</a> <span>|</span></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>

	<hr />
	<h3 />
	<nav>
		<a href="newproduct">Add New Product</a>
	</nav>
	<h3 />
	<jsp:include page="footer.jsp" />
</body>
</html>