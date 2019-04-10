<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alle Attribute</title>
</head>
<body>
	<h1>Alle Attribute</h1>
	<hr>
	<p>pageContext</p>
	<%
			Enumeration<String> rq = pageContext.getAttributeNamesInScope(PageContext.REQUEST_SCOPE); 
			Enumeration<String> pa = pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE); 
			Enumeration<String> se = pageContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE); 
			Enumeration<String> ap = pageContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE); 
			
	%>
	<h6>PAGE SCOPE</h6>
	<table width="100%" border="2px">
		<%
			while(pa.hasMoreElements()){
				String elem = pa.nextElement();
				out.write("<tr><td>" + elem + "</td><td>" + pageContext.getAttribute(elem,PageContext.PAGE_SCOPE) + "</td></tr>");
			}
		%>
	</table>
	
	<h6>REQUEST SCOPE</h6>
	<table width="100%" border="2px">
		<%
			while(rq.hasMoreElements()){
				String elem = rq.nextElement();
				out.write("<tr><td>" + elem + "</td><td>" + pageContext.getAttribute(elem,PageContext.REQUEST_SCOPE) + "</td></tr>");
			}
		%>
	</table>
	
	<h6>SESSION SCOPE</h6>
	<table width="100%" border="2px">
		<%
			while(se.hasMoreElements()){
				String elem = se.nextElement();
				out.write("<tr><td>" + elem + "</td><td>" + pageContext.getAttribute(elem,PageContext.SESSION_SCOPE) + "</td></tr>");
			}
		%>
	</table>
	<h6>APPLICATION SCOPE</h6>
	<table width="100%" border="2px">
		<%
			while(se.hasMoreElements()){
				String elem = ap.nextElement();
				out.write("<tr><td>" + elem + "</td><td>" + pageContext.getAttribute(elem,PageContext.APPLICATION_SCOPE) + "</td></tr>");
			}
		%>
	</table>
</body>
</html>