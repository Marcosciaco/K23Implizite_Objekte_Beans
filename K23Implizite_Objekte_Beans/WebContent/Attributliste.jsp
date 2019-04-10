<%@ page import="java.util.*"%>
<h1 style="color: black;">Alle Attribute</h1>
<table border="3px">
	<tr>
		<td><h3>PAGE:</h3></td>
		<td></td>
	</tr>
	<%
			for (Enumeration page2 = pageContext.getAttributeNamesInScope(pageContext.PAGE_SCOPE); page2.hasMoreElements();) {
				String obj = (String)page2.nextElement();
		%>
	<tr>
		<td style="color: navy"><%=obj%></td>
		<td style="color: navy"><%=pageContext.getAttribute(obj)%></td>
	</tr>
	<%
			}
			%>
	<tr>
		<td><h3>REQUEST:</h3></td>
		<td></td>
	</tr>
	<%
			for (Enumeration req = pageContext.getAttributeNamesInScope(pageContext.REQUEST_SCOPE); req.hasMoreElements();) {
				String obj = (String)req.nextElement();
		%>
	<tr>
		<td style="color: navy"><%=obj%></td>
		<td style="color: navy"><%=request.getAttribute(obj)%></td>
	</tr>
	<%
			}
			%>
	<tr>
		<td><h3>SESSION:</h3></td>
		<td></td>
	</tr>
	<%
			for (Enumeration ses = pageContext.getAttributeNamesInScope(pageContext.SESSION_SCOPE); ses.hasMoreElements();) {
				String obj = (String)ses.nextElement();
		%>
	<tr>
		<td style="color: navy"><%=obj%></td>
		<td style="color: navy"><%=session.getAttribute(obj)%></td>
	</tr>
	<%
			}
			%>
	<tr>
		<td><h3>APPLICATION:</h3></td>
		<td></td>
	</tr>
	<%
			for (Enumeration app = pageContext.getAttributeNamesInScope(pageContext.APPLICATION_SCOPE); app.hasMoreElements();) {
				String obj = (String)app.nextElement();
		%>
	<tr>
		<td style="color: navy;"><%=obj%></td>
		<td style="color: navy"><%=application.getAttribute(obj)%></td>
	</tr>
	<%
			}
			%>
</table>
<br>
<a href="http://localhost:8081/K23Implizite_Objekte_Beans/Nr2.jsp">back</a>