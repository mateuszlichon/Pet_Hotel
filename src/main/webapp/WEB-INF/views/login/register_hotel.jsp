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
<%@ include file = "../jspf/main_menu.jspf" %>
<h5>Register hotel:</h5>
<form:form action="registerHotel" method="post" modelAttribute="hotel">
	<p>User Name<form:input path="name" /><form:errors path="name"></form:errors></p>
	<p>Email<form:input path="email" /><form:errors path="email"></form:errors></p>
	<p>Password<form:password path="password" /><form:errors path="password"></form:errors></p>
	<p>Description<form:input path="description" /><form:errors path="description"></form:errors></p>
	<p>Requirements<form:input path="requirements" /><form:errors path="requirements"></form:errors></p>
	<p>City<form:input path="addressCity" /><form:errors path="addressCity"></form:errors></p>
	<p>Street<form:input path="addressStreet" /><form:errors path="addressStreet"></form:errors></p>
<p><input type="submit" /></p>
</form:form>
</body>
</html>