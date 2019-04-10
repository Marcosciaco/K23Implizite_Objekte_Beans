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
	<jsp:setProperty name="meldungsliste" property="pfad"
		value="E:/Info/Work/K23Implizite_Objekte_Beans/WebContent/" />
	<table>
		<tr>
			<td><h1>Last Messages</h1></td>
			<td style="text-align: right;">Number of messages: <%=meldungsliste.getAnzahlMeldungen()%><br>
				You are not logged in!<br> <a
				href="http://localhost:8081/K23Implizite_Objekte_Beans/sec2/index.jsp">Login</a></td>
		</tr>
	</table>
	<hr>
	<table>
		<tr>
			<td>Look at the newest Messages</td>
			<td style="text-align: right;">
				<%
					if (request.getParameter("zaehler") != null && request.getParameter("zaehler").length() != 0) {
						int zaehler = Integer.parseInt(request.getParameter("zaehler"));
						if (zaehler + meldungsliste.getAnzahlZuLiefernderMeldungen()-1 <= meldungsliste.getAnzahlMeldungen()) {
				%> <a
				href="http://localhost:8081/K23Implizite_Objekte_Beans/Nr4.jsp?zaehler=<%=zaehler + meldungsliste.getAnzahlZuLiefernderMeldungen()%>">&lt&ltVorige&nbsp</a>
				<%
					}
						if (zaehler - meldungsliste.getAnzahlZuLiefernderMeldungen()+1 > 0) {
				%> <a
				href="http://localhost:8081/K23Implizite_Objekte_Beans/Nr4.jsp?zaehler=<%=zaehler - meldungsliste.getAnzahlZuLiefernderMeldungen()%>">&nbspWeitere&gt&gt</a>
				<%
					}
					} else {
				%> <a href="http://localhost:8081/K23Implizite_Objekte_Beans/Nr4.jsp?zaehler=<%=meldungsliste.getAnzahlMeldungen()-1%>">&nbspWeitere&gt&gt</a>
				<%
					}
				%>
			</td>
		</tr>
	</table>
	<table border="5pt solid ">
		<tr>
			<th width="30%">Date</th>
			<th>Message</th>
		</tr>
		<%
			if (meldungsliste.getAnzahlMeldungen() != 0) {
				int zaehler = meldungsliste.getAnzahlMeldungen()-1;
				if (request.getParameter("zaehler") != null && request.getParameter("zaehler").length() != 0) {
					zaehler = Integer.parseInt(request.getParameter("zaehler"));
				}
				for (int i = 0; i < meldungsliste.getAnzahlZuLiefernderMeldungen(); i++) {
					if (meldungsliste.getMeldung(zaehler-i) != null) {
		%>
		<tr>
			<td><%=meldungsliste.getMeldung(zaehler-i).getDatum()%></td>
			<td><a
				href="http://localhost:8081/K23Implizite_Objekte_Beans/OldMessage.jsp?id=<%=zaehler-i%>"><%=meldungsliste.getMeldung(zaehler-i).getKurzTitel()%>(von
					<%=meldungsliste.getMeldung(zaehler-i).getVerfasser()%>)</a><br><%=meldungsliste.getMeldung(zaehler-i).getKurzText()%></td>
		</tr>
		<%
			}
				}
			}
		%>
	</table>
</body>
</html>