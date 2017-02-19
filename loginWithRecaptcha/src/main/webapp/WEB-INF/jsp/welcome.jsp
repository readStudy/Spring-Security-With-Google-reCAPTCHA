<!DOCTYPE html>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@ page pageEncoding="utf-8"%>

<html>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<head>
<title>Welcome</title>
</head>
<body>
	<h1>Welcome</h1>
	
	<security:authorize access="isAuthenticated()">
		<h1>Hello </h1>
	    <h1><security:authentication property="principal.username" /></h1>
	</security:authorize>
	
</body>
</html>