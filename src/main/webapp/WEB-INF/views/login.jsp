<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring Register</title>
</head>

<body>
	<h1> Login</h1>
	<h4><c:if test="${! empty error }">${error}</c:if></h4>
	<form:form method="POST" commandName="login" action="loginProcess">
		<table>
		<tr>
			<td><form:label path="userName">UserName</form:label> </td>
			<td><form:input path="userName"/> </td>
		</tr>
		
		<tr>
			<td><form:label path="password">password</form:label> </td>
			<td><form:password path="password"/> </td>
		</tr>
		
		<tr>
			<td><input type="submit" value="Submit"> </td>
		</tr>
		
		</table>
	
	</form:form>

</body>
</html>