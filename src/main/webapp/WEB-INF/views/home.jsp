<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Index Page</title>
<%@ include file="/common/head.html" %>
</head>
<body>

<%@ include file="/common/navigator.html" %>

<h2>
	Hello! ${user.username} 
</h2>

<div class="clear"></div>
<div class="clear"></div>
<h2>home page!</h2>
<%@ include file="/common/foot.html" %>
</body>
</html>