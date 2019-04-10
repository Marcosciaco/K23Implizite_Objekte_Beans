<%@page import="Rohdateien.MeldungsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Eingeloggt: Die letzten Meldungen</title>
<style type="text/css">
table {
	width: 100%;
}
</style>
</head>
<body>

	<%!public String getException(MeldungsBean meldung, String name) {
		String ret = "";
		if (meldung.getFehler() != null) {
			ret = meldung.getFehler().get(name);
		}
		return ret != null ? ret : "";
	}%>

	<jsp:useBean id="meldungsliste" class="Rohdateien.MeldungslisteBean"
		scope="application" />
	<jsp:useBean id="meldung" class="Rohdateien.MeldungsBean"
		scope="request" />
	<table>
		<tr>
			<td><h1>Die letzten Meldungen</h1></td>
			<td style="text-align: right;">Anzahl vorhandene Meldungen: <%=meldungsliste.getAnzahlMeldungen()%><br>
				Sie sind als <b><%=request.getRemoteUser()%></b> eingeloggt<br>
				<a href="http://localhost:8080/K23Implizite_Objekte_Beans/sec2/NewMessage.jsp">Neue
					Meldung&nbsp</a><a
				href="http://localhost:8081/K23Implizite_Objekte_Beans/sec2/Logout.jsp">Logout</a></td>
		</tr>
	</table>
	<hr>
	<h2>Neue Meldung</h2>
	<form method="post">
		<table>
			<tr>
				<td width="10%"><h3>Verfasser:</h3></td>
				<td style="text-align: left"><p>
						<%=request.getRemoteUser()%></p></td>
			</tr>
			<tr>
				<td width="10%"><h3>Titel:</h3></td>
				<td style="text-align: left"><input type="text" name="titel"
					value='<%if (request.getParameter("verwerfen") == null || request.getParameter("verwerfen").length() == 0) {%><jsp:getProperty name="meldung" property="titel"/><%}%>' /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><span
					style="color: red"><%=getException(meldung, "titel")%></span></td>
			</tr>
			<tr>
				<td width="10%"><h3>Text:</h3></td>
				<td height="100%" style="text-align: left"><textarea rows="100"
						style="width: 100%;" name="text"><%if(request.getParameter("verwerfen") == null || request.getParameter("verwerfen").length() == 0){%><jsp:getProperty
							name="meldung" property="text" /><%}%></textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><span
					style="color: red"><%=getException(meldung, "text")%></span></td>
			</tr>
			<tr>
				<td style="text-align: center"><button type="submit" formaction="NewMessageAction.jsp">Submit</button> <button
					type="submit" name="verwerfen" value="Eingabe Verwerfen" >Entwurf verwerfen</button></td>
				<td><a href="http://localhost:8081/K23Implizite_Objekte_Beans/sec2/index.jsp">Zurück</a></td>
			</tr>
		</table>
	</form>
</body>
</html>