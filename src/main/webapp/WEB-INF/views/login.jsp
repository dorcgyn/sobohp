<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello! Please Choose WHO YOU ARE  
</h1>

<form action="login" method="POST">
	<select name="user_id">
		<c:forEach var="user" items="${users}">
		   <option value="${user.id}">${user.username}</option>
		</c:forEach>
	</select>
	<input type="submit">
</form>
</body>
</html>
