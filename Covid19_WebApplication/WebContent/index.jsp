<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Covid19 Safety Kit: Homepage</title>
</head>
<body>
	<div>
		<jsp:include page="header.jsp" />
		<hr />
		<div>
			<a href="user?action=newuser"><button>Order Covid19 Safety Kit</button></a>
		</div>
		<hr />

		<div></div>
		<div></div>
		<div></div>

		<h2>Administrator Login</h2>
		<form action="admin" method="post">
			<div>
				<div>
					<label for="loginid">User Name: </label>
				</div>
				<div>
					<input type="text" id="loginid" name="loginid" required>
				</div>
			</div>
			<div>
				<div>
					<label for="password">Password: </label>
				</div>
				<div>
					<input type="password" id="password" name="password" required>
				</div>
			</div>
			<div>
				<div>
					<input type="submit" value="Login">
				</div>
			</div>
		</form>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>