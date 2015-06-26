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
	<select name="user_id" id="user_id">
		<c:forEach var="user" items="${users}">
		   <option value="${user.id}">${user.username}</option>
		</c:forEach>
	</select>
	<br/>
	<input type="text" name="password"></input> 
	<br/>		
	<input type="submit">
</form>

<a href="#" id="forgetPwd">Forget Password?</a>

<script type="text/javascript">
    document.getElementById("forgetPwd").onclick = function (e) {
    	e.preventDefault();
    	var selector = document.getElementById("user_id");
    	var userId = selector.options[selector.selectedIndex].value;
    	alert(userId);
        location.href = "/SoboHp/user/forgetpwd?user_id=" + userId;
    };
</script>

</body>
</html>
