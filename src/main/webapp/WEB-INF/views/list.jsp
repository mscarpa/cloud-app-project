<%@ page import="ie.cit.appdev.Movies"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies Collection</title>
</head>
<body>
	<div id="container">
		<div id="right">
			<div class="header">
				<h1>MOVIES To WATCH</h1>
			</div>
		</div>
		<br /> <br />
		<div class="products">
			<h2>Current List</h2>

			<table>
				<tr>
					<th>TITLE</th>
					<th>Type</th>
					<th>Year</th>
				</tr>
			</table>
			<c:forEach items="${list}" var="mov" varStatus="row">
				<c:choose>
					<c:when test="${mov.done}">
						<table>
							<tr>
								<td><del>${mov.text}</del></td>
								<td><del>${mov.tipe}</del></td>
								<td><del>${mov.year}</del></td>
							</tr>
						</table>
					</c:when>
					<c:otherwise>
						<table>
							<tr>
								<td>${mov.text}</td>
								<td>${mov.tipe}</td>
								<td>${mov.year}</td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>

				<form method="post">
					<input name="_method" type="hidden" value="put"> <input
						name="mid" type="hidden" value="${mov.id}"> <input
						type="submit" value="Mark Watched">
				</form>
				<form method="post">
					<input name="_method" type="hidden" value="delete"> <input
						name="mid" type="hidden" value="${mov.id}"> <input
						type="submit" value="Remove">
				</form>
				<br />
				<br />
			</c:forEach>

			<br /> <br />

			<h2>Add a MOVIE To The Collection</h2>

			<form method="post">
				<table>
					<tr>
						<td>Title:*</td>
						<td><input name="text" size="35"></td>
						<td><input type="submit" value="ADD"></td>
						<td><input type="reset" value="Clear"></td>
					</tr>
					<tr>
						<td>Type:*</td>
						<td><input type="text" name="tipe" size="12" />
					</tr>
					<tr>
						<td>Year:*</td>
						<td><input type="text" name="year" size="4" /></td>
					</tr>
				</table>
			</form>
			<br />
			<h3>* All the fields are mandatory</h3>
			<br /> <br /> <br /> <br /> <a href="j_spring_security_logout">Logout:
				<security:authentication property="principal.username" /><br>
			</a>
			<p>- 2012 - R00091603 - Cloud Computing -</p>
		</div>
	</div>
</body>
</html>