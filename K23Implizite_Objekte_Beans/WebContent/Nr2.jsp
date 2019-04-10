<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ATTRIBUTES</title>
</head>
<body>

	<%!public String getException(Nr2.AttributeBean attribute, String name) {
		String ret = "";
		if (attribute.getFehler() != null) {
			ret = attribute.getFehler().get(name);
		}
		return ret != null ? ret : "";
	}%>

	<jsp:useBean id="attribute" class="Nr2.AttributeBean" scope="session" />
	<h1>ATTRIBUTES</h1>
	<p>Please input name, value and scope of the Attribute:</p>
	<form method="post" action="Nr2Aktion.jsp">
		<table>
			<tr>
				<td>Name:</td>
				<td colspan="2"><input name="name" type="text"
					value="<jsp:getProperty name="attribute" property="name"/>" /></td>
				<td><span style="color: red"><%=getException(attribute, "name")%></span></td>
			</tr>

			<tr>
				<td>Value:</td>
				<td colspan="2"><input name="wert" type="text"
					value="<jsp:getProperty name="attribute" property="wert"/>" /></td>
				<td><span style="color: red"><%=getException(attribute, "wert")%></span></td>
			</tr>

			<tr>
				<td>Scope:</td>
				<td colspan="2">
				<input name="scope" value="session"type="radio" <%if(attribute.getScope() != null && attribute.getScope().equals("session")){%> checked="checked" <%} %>>Session</input> 
				<br> 
				<input name="scope" value="application" type="radio" <%if(attribute.getScope() != null && attribute.getScope().equals("application")){%> checked="checked" <%} %>>Application</input>
				</td>
				<td><span style="color: red"><%=getException(attribute, "scope")%></span></td>

			</tr>
			<tr>
				<td><input name="eintragen" value="PUT" type="submit" /></td>
				<td><input name="ausgeben" value="PRINT" type="submit" /></td>
				<td><input name="loeschen" value="DELETE" type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>