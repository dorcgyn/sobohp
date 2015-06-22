<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/head.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>

<script>
$(document).ready(function(){
    $("#target").change(function(){
        
        $.ajax({
        	url: "/SoboHp/data/tran?target_id="+$(this).val(),
       	}).done(function(data) {
       		$("#data").empty();
       		$("#data").append(data);
       	});
    });
    // trigger it programmaticly after page loading
    $("#target").change();
}); 
</script>

</head>
<body>
	<%@ include file="/common/navigator.html" %>
	
	<h2>Report</h2>
	<br/>
	
	<div>Select Target:
		<select name="user_id" id="target">
			<option value="all">All</option>
			<c:forEach var="user" items="${others}">
			   <option value="${user.id}">${user.username}</option>
			</c:forEach>
		</select>
	</div>
	
	<br/><br/>
	<div id="data"></div>

	<%@ include file="/common/foot.html" %>
</body>
</html>