<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    if (name != null && !name.trim().isEmpty()) {
%>
    <h1>Hello, <%= name %>!</h1>
<%
    } else {
%>
    <h1>No name provided.</h1>
<%
    }
%>
</body>
</html>