<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="test" uri="/WEB-INF/tld/tables.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="en-EN" scope="session"/>
<fmt:setBundle basename="Message" var="rb"></fmt:setBundle>
<html>
<head>
<title>Оформлення заявки</title>
</head>
<body>
	<h3>
		<c:out value="Welcome, ${userName}!" />
	</h3>
	<a href='jsp.user/change_user.jsp?id=${userId}' > Change personal information</a>
		<br />
	<a href='jsp.user/user_create_petition.jsp?id=${userId}' > Add new petition</a>
		<hr />
	<H3><test:all_user_petition id="${userId}"/></H3>
		<hr />	
	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>