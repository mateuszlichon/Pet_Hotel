<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="jspf/head_config.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="jspf/main_menu.jspf"%>
	
	<p>Pet To Delete</p>
	${petToDelete.id} ${petToDelete.day} ${petToDelete.month.name}
	
	<p>New Data</p>
	Pets:
	<c:forEach items="${pets}" var="p">
	${p.name}
	</c:forEach>
	
	<br>
	
	Dates:
	<c:forEach items="${date}" var="d">
	${d.day} ${d.month.name}
	</c:forEach>
	
	<br>
	<p>Data Oryginal</p>
	Pets2:
	<c:forEach items="${pets2}" var="d">
	${d.name}
	</c:forEach>
	
	<br>
	
	
	RS:
	<c:forEach items="${rs}" var="p">
	${p.day} ${p.month.name}
	</c:forEach>
	
	<br>
	

	
	
</body>
</html>