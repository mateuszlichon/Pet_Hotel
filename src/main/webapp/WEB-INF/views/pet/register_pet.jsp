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
<form:form method="post" modelAttribute="pet">
	<p>Pet Name<form:input path="name" /><form:errors path="name"></form:errors></p>
	<p>Age <input type="number" name="age"></p>
	<p>Category<form:input path="category" /><form:errors path="category"></form:errors></p>
	<p>Breed<form:input path="breed" /><form:errors path="breed"></form:errors></p>
	<p>Diet<form:textarea path="diet" /><form:errors path="diet"></form:errors></p>
	<p>Description<form:textarea path="description" /><form:errors path="description"></form:errors></p>
	<p>Additional requirements<form:textarea path="requirements" /><form:errors path="requirements"></form:errors></p>
<p><input type="submit" /></p>
</form:form>
</body>
</html>