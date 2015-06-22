<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/head.html" %>

	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script>
		$(function() {
			$( "#datepicker" ).datepicker();
		});
	</script>
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Transaction</title>
</head>
<body>
	<%@ include file="/common/navigator.html" %>
	
 	
	<form action="create" method="POST">
	  	<table border=1><tbody>
	  		<tr>
		  		<td>
					Transaction Type: 
				</td>
				<td>
					<input type="radio" name="type" value="lend" checked>Lend  
					<input type="radio" name="type" value="borrow">Borrow
				</td>
			</tr>
			
			<tr>
				<td>
					Target: 
				</td>
				<td>
					<select name="target">
						<c:forEach var="user" items="${others}">
						   <option value="${user.id}">${user.username}</option>
						</c:forEach>
					</select>	 
				</td>
			</tr>
			
			<tr>
				<td>Location:</td><td><input type="text" name="location" size="30"></td>
			</tr>
			
			<tr>
			<td>Amount:</td><td> <input type="text" name="amount" pattern="^\d+(\.(\d{2}|\d{1}))?$">(float only, e.g. 6.25)</td>
			</tr>
			
			<tr>
			<td>Date:</td><td> <input type="text" name="date" id="datepicker"></td>
			</tr>
			
			<tr>
			<td>Comment:</td><td> <input type="text" name="comment" size="50"></td>
			</tr>
			
			<tr><td colspan=2><input type="submit" name="submit"></td></tr>
		</tbody></table>
		
	</form>
	
	<%@ include file="/common/foot.html" %>
</body>
</html>