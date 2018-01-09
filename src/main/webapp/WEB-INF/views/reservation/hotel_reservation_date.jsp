<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file = "../jspf/head_config.jspf" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login/register page</title>
</head>
<body>
<%@ include file = "../jspf/main_menu.jspf" %>
<%@ include file = "../jspf/reservation_progress.jspf" %>
<h5>Register hotel ${hotel.name}:</h5>
January:<br>
<c:forEach items="${datesJanuary}" var="date">
<c:choose>
	<c:when test="${date.placesLeft > 3}">
		<a href="/Pet_Hotel/reservationDate/${date.id}/date"><button type="button" class="btn btn-success">${date.day}</button></a>
	</c:when>

	<c:otherwise>
		<a href="/Pet_Hotel/reservationDate/${date.id}/date"><button type="button" class="btn btn-danger">${date.day}</button></a>
	</c:otherwise>
</c:choose>
</c:forEach>
<br>
February:<br>
<c:forEach items="${datesFebruary}" var="date">
<c:choose>
	<c:when test="${date.placesLeft > 3}">
		<a href="/Pet_Hotel/reservationDate/${date.id}/date"><button type="button" class="btn btn-success">${date.day}</button></a>
	</c:when>

	<c:otherwise>
		<a href="/Pet_Hotel/reservationDate/${date.id}/date"><button type="button" class="btn btn-danger">${date.day}</button></a>
	</c:otherwise>
</c:choose>
</c:forEach>
</body>
</html>