<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<div class="clear"></div>
<table class="table-blog">
  <tbody>
	<tr>
		<th>Date</th>
		<th>Lender</th>
		<th>Borrower</th>
		<th>Location</th>
		<th>Amount</th>
		<th>Type</th>
		<th>Comment</th>
		<th>Status</th>
		
		<th>Generator</th>
		<th>Generate Date</th>
	</tr>
	
	<c:forEach var="tran" items="${trans}">
	   	<tr>
		   	<td>${tran.tran_date}</td>
			<td>${tran.lender.username}</td>
			<td>${tran.borrower.username}</td>
			<td>${tran.location}</td>
			
			<td>${tran.amount}</td>
			<td>${tran.type}</td>
			<td>${tran.comment}</td>
			<td>${tran.status}</td>
			<td>${tran.generator.username}</td>
			<td>${tran.created_when}</td>
		</tr>
	</c:forEach>
  </tbody>
</table>
<div class="clear"></div>
<div>Balance: ${balance}</div>
<div>Transaction Number: ${size}</div>
<c:if test="${not empty targetUser}">
	<div>If you clear the balance to ${targetUser.username} (e.g. pay cash), 
		click <a href="./clear?target=${targetUser.id}&balance=${balance}">CLEAR</a> 
		to perform clear operation!</div>
</c:if>