<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Covid19 Safety Kit: Add New Product [Administrator]</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<form action="insertproduct" method="POST">
		<div>
			<label>Product ID: </label> <input type="number" value="${item.id }"
				name="pid" required />
		</div>
		<div>
			<label>Product Name: </label> <input type="text"
				value="${item.productName }" name="pname" minlength="3"
				maxlength="20" required />
		</div>
		<div>
			<label>Cost: </label> <input type="text" value="${item.cost }"
				name="pcost" required />
		</div>
		<div>
			<label>Product Description: </label> <input type="text"
				value="${item.productDescription }" name="pdescription" required />
		</div>

		<button>Save</button>
	</form>


	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>