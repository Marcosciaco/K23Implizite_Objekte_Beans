<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Die letzten Meldungen</title>
<style type="text/css">
table {
	width: 100%;
}
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
			<td><h1>Last Messages</h1></td>
			<td style="text-align: right;">Number of Messages: <%=meldungsliste.getAnzahlMeldungen()%><br>
				You are not logged in<br> <a
				href="http://localhost:8081/K23Implizite_Objekte_Beans/sec2/OldMessage.jsp?id=<%=nummer%>">Login</a></td>
		</tr>
	</table>
	<hr>
	<h2><%=meldungsliste.getMeldung(nummer).getTitel()%></h2>
	<h3>
		Writer:
		<%=meldungsliste.getMeldung(nummer).getVerfasser()%></h3>
	<label style="width:100%; word-wrap:break-word;"><%=meldungsliste.getMeldung(nummer).getText()%></label><br>
	<a href="http://localhost:8081/K23Implizite_Objekte_Beans/Nr4.jsp">back</a>
</body>
</html>