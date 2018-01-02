<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login/register page</title>
</head>
<body>
<h5>Please log in and let's find your pet a home:</h5>
<form:form method="post" modelAttribute="loginData">
	<p>Email<form:input path="email" /></p>
	<p>Password<form:password path="password" /></p>
	<p><input type="submit" /></p>
	<form:errors></form:errors>
</form:form>
<br />
<h5>New to the site? Register here:</h5>
<form:form method="post" modelAttribute="user">
	<p>User Name<form:input path="name" /><form:errors path="name"></form:errors></p>
	<p>Email<form:input path="email" /><form:errors path="email"></form:errors></p>
	<p>Password<form:password path="password" /><form:errors path="password"></form:errors></p>
<p><input type="submit" /></p>
</form:form>
</body>
</html>