<jsp:useBean id="meldungsliste" class="Rohdateien.MeldungslisteBean"
	scope="application" />
<jsp:useBean id="meldung" class="Rohdateien.MeldungsBean"
	scope="request" />
<jsp:setProperty name="meldung" property="titel"
	value='<%=request.getParameter("titel")%>' />
<jsp:setProperty name="meldung" property="text"
	value='<%=request.getParameter("text")%>' />
<jsp:setProperty name="meldung" property="verfasser"
	value='<%=request.getRemoteUser()%>' />

<%
	String datei = null;
	meldung.validiere();
	if (meldung.getFehler() != null) {
		//System.out.println("titel: " + request.getParameter("titel") + ", text: " + request.getParameter("text") + ", verfasser: " + request.getRemoteUser());
		datei = "NewMessage.jsp";
	} else {
		int i = meldungsliste.meldungEinfuegen(meldung);
		System.out.println(i);
		datei = "index.jsp";
		System.out.println(meldungsliste.getAnzahlMeldungen());
		
	}
%>

<jsp:forward page="<%=datei%>" />