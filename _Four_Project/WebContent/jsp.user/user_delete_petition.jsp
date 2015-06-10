<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="test" uri="/WEB-INF/tld/tables.tld"%>
<html>
<head>
<title>Delete petition</title>
</head>
<body>
	<form action="../controller" method="post">
			<input type="hidden" name="command" value="userStartPage" />
		<H3>
			<test:user_delete_petition/>
		</H3>	
	</form>
</body>
</html>