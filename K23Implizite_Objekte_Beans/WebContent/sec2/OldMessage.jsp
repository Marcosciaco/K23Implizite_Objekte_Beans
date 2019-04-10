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
p{
	word-wrap:break-all;}
</style>
</head>
<body>
	<jsp:useBean id="meldungsliste" class="Rohdateien.MeldungslisteBean"
		scope="application" />
	<table>
	<%
	
		int nummer = Integer.parseInt(request.getParameter("id"));
	%>
		<tr>
			<td><h1>Die letzten Meldungen</h1></td>
			<td style="text-align: right;">Anzahl vorhandene Meldungen: <%=meldungsliste.getAnzahlMeldungen()%><br>
				Sie sind als <b><%=request.getRemoteUser()%></b> eingeloggt<br>
				<a href="http://localhost:8081/K23Implizite_Objekte_Beans/sec2/NewMessage.jsp">Neue
					Meldung&nbsp</a><a
				href="http://localhost:8081/K23Implizite_Objekte_Beans/sec2/Logout.jsp?id=<%=nummer%>">Logout</a></td>
		</tr>
	</table>
	<hr>
	<h2><%=meldungsliste.getMeldung(nummer).getTitel()%></h2>
	<h3>
		Verfasser:
		<%=meldungsliste.getMeldung(nummer).getVerfasser()%></h3>
		<label style="width:100%; word-wrap:break-word;"><%=meldungsliste.getMeldung(nummer).getText()%></label><br>
	<a href="http://localhost:8081/K23Implizite_Objekte_Beans/sec2/index.jsp">Zurück</a>
</body>
</html>