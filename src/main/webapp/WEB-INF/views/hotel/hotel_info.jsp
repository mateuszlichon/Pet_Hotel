<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login/register page</title>
</head>
<body>
<%@ include file = "../jspf/main_menu.jspf" %>

			<table>
				<tr>
					<th>Name</th>
					<td>${hotel.name}</td>
				</tr>
				<tr>
					<th>description</th>
					<td>${hotel.description}</td>
				</tr>
				<tr>
					<th>requirements</th>
					<td>${hotel.requirements}</td>
				</tr>
				<tr>
					<th>addressCity</th>
					<td>${hotel.addressCity}</td>
				</tr>
				<tr>
					<th>addressStreet</th>
					<td>${hotel.addressStreet}</td>
				</tr>

			</table>

</body>
</html>