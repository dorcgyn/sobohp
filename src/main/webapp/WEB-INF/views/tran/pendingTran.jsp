<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/head.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Pending Transaction</title>
</head>
<body>
<%@ include file="/common/navigator.html" %>
	<h2>View Pending Transaction</h2>
	<div class="clear"></div>
	<table  class="table-blog">
	  <tbody>
		<tr>
			<th>Date</th>
			<th>Lender</th>
			<th>Borrower</th>
			<th>Location</th>
			<th>Amount</th>
			<th>Comment</th>
			<th>Status</th>
			
			<th>Generator</th>
			<th>Generate Date</th>
			
			<th>Confirm</th>
			<th>Refuse</th>
		</tr>
		
		<c:forEach var="tran" items="${pending}">
		   	<tr>
			   	<td>${tran.tran_date}</td>
				<td>${tran.lender.username}</td>
				<td>${tran.borrower.username}</td>
				<td>${tran.location}</td>
				
				<td>${tran.amount}</td>
				<td>${tran.comment}</td>
				<td>${tran.status}</td>
				<td>${tran.generator.username}</td>
				<td>${tran.created_when}</td>
				
				<td><a href="confirm?tran_id=${tran.id}">Confirm</a></td>
				<td><a href="refuse?tran_id=${tran.id}">Refuse</a></td>
			</tr>
		</c:forEach>
	  </tbody>
	</table>
	<div class="clear"></div>
	
	<br/><br/><br/>
	<h2>View Confirmed/Refused Transaction</h2>
	<div class="clear"></div>
	<table  class="table-blog">
	  <tbody>
		<tr>
			<th>Date</th>
			<th>Lender</th>
			<th>Borrower</th>
			<th>Location</th>
			<th>Amount</th>
			<th>Comment</th>
			<th>Status</th>
			
			<th>Generator</th>
			<th>Generate Date</th>
		</tr>
		
		<c:forEach var="tran" items="${confirmedOrRefused}">
		   	<tr>
			   	<td>${tran.tran_date}</td>
				<td>${tran.lender.username}</td>
				<td>${tran.borrower.username}</td>
				<td>${tran.location}</td>
				
				<td>${tran.amount}</td>
				<td>${tran.comment}</td>
				<td>${tran.status}</td>
				<td>${tran.generator.username}</td>
				<td>${tran.created_when}</td>
			</tr>
		</c:forEach>
	  </tbody>
	</table>
	<div class="clear"></div>
	
	<%@ include file="/common/foot.html" %>

</body>
</html>