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


	<c:choose>
		<c:when test="${user.pet == null}">
			<h1>You have not pet registered</h1>
		</c:when>
		<c:otherwise>
		<h4 align="center">Your registered pets are:</h4>
					<p align="center"><c:forEach items="${logedUser.pet}" var="p">
					<c:choose>
					<c:when test="${pet.id == p.id}">
						<a href="/Pet_Hotel/pet/show/${p.id}"><button type="button" class="btn btn-default" style="background-color: #F0E68C;">${p.name}, ${p.category}</button></a>    
					</c:when>
					<c:otherwise>
						<a href="/Pet_Hotel/pet/show/${p.id}"><button type="button" class="btn btn-default">${p.name}, ${p.category}</button></a>    					
					</c:otherwise>
					</c:choose>
					</c:forEach></p>

				<br>
			<c:choose>
				<c:when test="${pet == null}">
		Please choose pet in the menu above
	</c:when>

				<c:otherwise>


					<table class="table">
						<tr>
							<th>Name</th>
							<td>${pet.name}</td>
						</tr>
						<tr>
							<th>Category</th>
							<td>${pet.category}</td>
						</tr>
						<tr>
							<th>Breed</th>
							<td>${pet.breed}</td>
						</tr>
						<tr>
							<th>Diet</th>
							<td>${pet.diet}</td>
						</tr>
						<tr>
							<th>Description</th>
							<td>${pet.description}</td>
						</tr>
						<tr>
							<th>Requirements</th>
							<td>${pet.requirements}</td>
						</tr>
						<tr>
							<th>Vaccinated</th>
							<td>${pet.vaccinated}</td>
						</tr>

					</table>
					<p align="right"><a href="/Pet_Hotel/pet/edit"><button
								type="button" class="btn btn-default"><h1 align="right">Edit pet details</h1></button></a></p>
					
					<p align="center"><a href="/Pet_Hotel/reservationDate/${pet.id}/newPetReservation"><button
								type="button" class="btn btn-warning"><h1 align="right">Make reservation</h1></button></a></p>

					<table align="center">
						<tr align="center">
							<th colspan="2">Previous reservations</th>
						</tr>
						<tr>
							<th>Hotel</th>
							<th>Date</th>
						</tr>
						<c:forEach items="${petDates}" var="date">
							<tr>
								<td>${date.hotel.name}</td>
								<td>${date.day} ${date.month.name} ${date.year} <a href="/Pet_Hotel/reservationDate/removePet/${date.id}/${pet.id}"><button type="button" class="btn btn-danger">erase reservation</button></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

</body>
</html>