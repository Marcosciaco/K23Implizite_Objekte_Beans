<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alle Attribute</title>
<style type="text/css">
table, tr, td {
	border: 2pt solid;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h1>Alle Attribute</h1>
	<h2>pageContext</h2>
	<table>
		<tr>
			<td><h5>PAGE:</h5></td>
			<td></td>
		</tr>
		<%
			for (Enumeration page2 = pageContext.getAttributeNamesInScope(pageContext.PAGE_SCOPE); page2.hasMoreElements();) {
				String obj = (String)page2.nextElement();
		%>
		<tr>
			<td><%=obj%></td>
			<td><%=pageContext.getAttribute(obj)%></td>
		</tr>
		<%
			}
			%>
		<tr>
			<td><h5>REQUEST:</h5></td>
			<td></td>
		</tr>
		<%
			for (Enumeration req = pageContext.getAttributeNamesInScope(pageContext.REQUEST_SCOPE); req.hasMoreElements();) {
				String obj = (String)req.nextElement();
		%>
		<tr>
			<td><%=obj%></td>
			<td><%=request.getAttribute(obj)%></td>
		</tr>
		<%
			}
			%>
		<tr>
			<td><h5>SESSION:</h5></td>
			<td></td>
		</tr>
		<%
			for (Enumeration ses = pageContext.getAttributeNamesInScope(pageContext.SESSION_SCOPE); ses.hasMoreElements();) {
				String obj = (String)ses.nextElement();
		%>
		<tr>
			<td><%=obj%></td>
			<td><%=session.getAttribute(obj)%></td>
		</tr>
		<%
			}
			%>
		<tr>
			<td><h5>APPLICATION:</h5></td>
			<td></td>
		</tr>
		<%
			for (Enumeration app = pageContext.getAttributeNamesInScope(pageContext.APPLICATION_SCOPE); app.hasMoreElements();) {
				String obj = (String)app.nextElement();
		%>
		<tr>
			<td><%=obj%></td>
			<td><%=application.getAttribute(obj)%></td>
		</tr>
		<%
			}
			%>
	</table>
</body>
</html>