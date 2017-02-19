<!DOCTYPE html>
<%@ page pageEncoding="utf-8"%>

<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Login</title>
 
<script src="https://www.google.com/recaptcha/api.js"></script>

</head>
<body onload='document.f.username.focus();'>
	<h1>User Login</h1>

	<form name='f' method="post">
		<table>
			<tr>
				<td>User Name：</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password：</td>
				<td><input type='password' name='password' /></td>
			</tr>		
			<tr>
				<td><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
		
		<div class="g-recaptcha" data-sitekey="${recaptchaSiteKey}"></div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
	</form>

</body>
</html>