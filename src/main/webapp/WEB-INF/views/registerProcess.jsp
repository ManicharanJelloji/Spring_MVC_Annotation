<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Account Details</title>
</head>

<body>
	<h1> Register Account details</h1>
	<c:if test="${! empty register}">
		<table border="2">
			<tr>
				<th>User ID</th>
				<th>User FirstName</th>
				<th>User LastName</th>
				<th>User Email</th>
				<th>User Profile Image</th>
			</tr>
			<tr>
				<td> ${register.id}</td>
				<td> ${register.firstName}</td>
				<td> ${register.lastName}</td>
				<td> ${register.email}</td>
				<td><img width="200" height="200" src="data:image/jpeg;base64,${register.base64}"></td>
			</tr>
		</table>
	</c:if>

</body>
</html>