<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/head.html" %>
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Transaction</title>
</head>
<body>
	<%@ include file="/common/navigator.html" %>
	
 	<h2>Change Password:</h2>
	<form action="/SoboHp/user/changepwd" method="POST">
		<div>Old Password: <input type="text" name="oldPwd"></input></div>
	  	<div>New Password: <input type="text" name="newPwd"></input></div>
	  	<div>Confirm Password: <input type="text" name="confirmPwd"></input></div>
	  	<div><input type="submit"></input></div>
	</form>
	
	<%@ include file="/common/foot.html" %>
</body>
</html>