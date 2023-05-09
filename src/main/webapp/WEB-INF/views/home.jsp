<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
	<head>
		<title>Spring MVC Application</title>
	</head>
	<body>
		<h1>Welcome to Spring MVC</h1>
		<h4><c:if test="${! empty registerMessage }">${registerMessage}</c:if></h4>
		<form:form>
		<table align="right">
			<tr>
			<td> <a href="login">Login</a> </td>
			<td></td>
			<td></td>
			<td></td>
			<td> <a href="register">Register</a> </td>
			</tr>
		</table>
		
		
		</form:form>
	</body>
</html>