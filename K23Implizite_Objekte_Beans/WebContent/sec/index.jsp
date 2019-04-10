<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>you are safe</title>
</head>
<body>
<h1>you are safe</h1>
<p>Username: <%=request.getRemoteUser() %> <br> You are a writer: <%=request.isUserInRole("verfasser") %></p>
<a href="http://localhost:8081/K23Implizite_Objekte_beans/sec/Logout.jsp">Logout</a>
</body>
</html>