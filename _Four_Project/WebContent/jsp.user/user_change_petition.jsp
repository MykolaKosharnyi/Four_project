<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="test" uri="/WEB-INF/tld/tables.tld"%>
<html>
<head>
<title>Change petition</title>
</head>
<body>
<form action="../controller" method="POST">
		<input type="hidden" name="command" value="user_change_petition" />
			<b>Change petition: </b>
			<test:user_change_petition/>
			<INPUT type="submit" value="Зберегти зміни">
	</form>
</body>
</html>