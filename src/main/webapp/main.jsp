<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ie.cit.appdev.Movies"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="repo" class="ie.cit.appdev.MoviesRepository" scope="session"></jsp:useBean>

<html>
<head>
<link rel="stylesheet" href="styles/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies Collection</title>
</head>
<body>
	<h1>MOVIES Collection MAIN</h1>
	<br/>
	<h2>Your list of films</h2>

	<c:if test="${! empty param.text }">
		<%
			Movies movies = new Movies();
			movies.setText(request.getParameter("text"));
			repo.addMovies(movies);
		%>
	</c:if>

	<c:forEach items="${movies}" var="mov" varStatus="row">
		${mov.text}${mov.done}			
		<form method="post">
			<input name="_method" type="hidden" value="put"> 
			<input name="mId" type="hidden" value="${row.count}"> 
			<input type="submit" value="Update">
		</form>
		<form method="post">
			<input name="_method" type="hidden" value="delete"> 
			<input name="mId" type="hidden" value="${row.count}"> 
			<input type="submit" value="Delete">
		</form>
		<br />
	</c:forEach>
	
		
	<h2>Add a Movie</h2>
	<br/>
	<form>
		Text: <input name="text"><input type="submit">
	</form>
</body>
</html>