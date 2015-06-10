<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="test" uri="/WEB-INF/tld/tables.tld"%>
<html>
<head>
<title>Change user</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="../controller" method="POST">
		<input type="hidden" name="command" value="user_change_semself" />
			<b>Change user parameters: </b>
			<test:user_change_semself/>
			<INPUT type="submit" value="Save changes">
	</form>
</body>
</html>