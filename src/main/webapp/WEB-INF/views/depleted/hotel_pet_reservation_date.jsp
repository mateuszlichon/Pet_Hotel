<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/head_config.jspf"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login/register page</title>
</head>
<body>
	<%@ include file="../jspf/main_menu.jspf"%>
	<h5>Register hotel ${hotel.name}:</h5>

<c:choose>
	<c:when test="${sessionScope.user.pet != null}">
			<c:forEach items="${sessionScope.user.pet}" var="pet">
				<a href="pet/${pet.id}"><button type="button"
						class="btn btn-success">${pet.name}</button></a>
			</c:forEach>	
	</c:when>
	<c:otherwise>
		<form:form method="post" modelAttribute="newPet">
				<p>Pet Name<form:input path="name" /><form:errors path="name"></form:errors></p>
				<p>Age <input type="number" name="age"></p>
				<p>Category<form:input path="category" /><form:errors path="category"></form:errors></p>
				<p>Breed<form:input path="breed" /><form:errors path="breed"></form:errors></p>
				<p>Diet<form:textarea path="diet" /><form:errors path="diet"></form:errors></p>
				<p>Description<form:textarea path="description" /><form:errors path="description"></form:errors></p>
				<p>Additional requirements<form:textarea path="requirements" /><form:errors path="requirements"></form:errors></p>
				<p>Vaccinated</p>
				Yes: <form:radiobutton path="vaccinated" value="true"/>
				No: <form:radiobutton path="vaccinated" value="false"/>
			<p><input type="submit" /></p>
		</form:form>
	</c:otherwise>
		</c:choose>
</body>
</html>